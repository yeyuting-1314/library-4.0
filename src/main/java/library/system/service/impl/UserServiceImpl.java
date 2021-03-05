package library.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.base.Constant;
import library.base.Result;
import library.system.dao.*;
import library.system.dto.*;
import library.system.service.UserService;
import library.util.Results;
import library.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserMapper userMapper ;

    @Autowired
    RedisTemplate redisTemplate ;

    @Autowired
    BookMapper bookMapper ;

    @Autowired
    TokenUtil tokenUtil ;

    @Autowired
    BorrowingRecordsMapper borrowingRecordsMapper ;

    @Autowired
    UserRoleMapper userRoleMapper ;

    @Autowired
    RoleMapper roleMapper ;

    @Autowired
    RolePrivilegeMapper rolePrivilegeMapper ;

    public Boolean insertOneUser(SysUser sysUser){
        return  userMapper.insertOneUser(sysUser) ;
    }

    public SysUser selectUserByUserId(SysUser sysUser){
       return userMapper.selectUserByUserId(sysUser) ;
    }

    //用户注册
    @Transactional
    public Object RegisterUser(SysUser sysUser){
        SysUser sysUser1 = userMapper.selectUserByUserId(sysUser) ;
        if(sysUser1 != null && !sysUser1.equals(null)){
            return "用户已存在，请登陆！" ;
        }
        //用户表插入数据
        Boolean result = userMapper.insertOneUser(sysUser) ;
        //用户角色关联表插入数据
        userRoleMapper.insertOne(sysUser.getUserId() , Constant.ROLE_USER) ;
        //角色权限关联表插入数据
        for(int i = 0 ; i < Constant.USERPRIVILEGENUM ; i++){
            rolePrivilegeMapper.insertOne(Constant.ROLE_USER , i+1) ;
        }
        return result ;
    }

    //用户登陆
    public Result Login(SysUser sysUser) {

        //SysUser sysUser1 = userMapper.selectUserByUserId(sysUser) ;
        SysUser sysUser1 = userMapper.selectUserAndRoleAndPrivilegeByUserName(sysUser.getUserName()) ;
        if(sysUser1.equals(null)){
            return Results.failure("用户不存在，请先登陆！") ;
        }
        ValueOperations valueOperations = redisTemplate.opsForValue() ;

        SysUser sysUser2 = (SysUser) valueOperations.get(sysUser.getUserId()) ;
        if((sysUser2 != null)&&(!sysUser2.equals(null))){
            redisTemplate.delete(sysUser.getUserId()) ;
        }
        String token = tokenUtil.getToken(sysUser) ;
        sysUser1.setToken(token);
        valueOperations.set(sysUser1.getUserId() , sysUser1);
        if (sysUser.getUserPassword().equals(sysUser1.getUserPassword())){
            return Results.successWithData(sysUser1) ;
        }
        return Results.failure("密码错误，请重新输入密码") ;
    }

    public List<Book> selectAllBooks(){
        return bookMapper.selectAllBooks() ;
    }

    public Boolean insertOneBook (Book book){
        return bookMapper.insertOneBook(book) ;
    }

    public Boolean insertManyBooks(List<Book> books) {
        return bookMapper.insertManyBooks(books) ;
    }

    public List<BorrowingRecords> selectAllBorrowingRecords(){
        return borrowingRecordsMapper.selectAllBorrowingRecords() ;
    }

    public Boolean borrowBooks(String bookCode , HttpServletRequest request){
        String token = request.getHeader("token") ;
        DecodedJWT jwt = tokenUtil.deToken(token) ;
        BorrowingRecords borrowingRecords = new BorrowingRecords() ;
        borrowingRecords.setUserId(jwt.getClaim("userId").asString());
        borrowingRecords.setBookCode(bookCode);
        borrowingRecords.setBorrowingStatus(Constant.BORROWBOOKS);
        return borrowingRecordsMapper.borrowBooks(borrowingRecords) ;
    }

    public Boolean returnBooks(String bookCode , HttpServletRequest request){
        String token = request.getHeader("token") ;
        DecodedJWT jwt = tokenUtil.deToken(token) ;
        BorrowingRecords borrowingRecords = new BorrowingRecords() ;
        borrowingRecords.setUserId(jwt.getClaim("userId").asString());
        borrowingRecords.setBookCode(bookCode);
        borrowingRecords.setBorrowingStatus(Constant.RETURNBOOKS);
        return borrowingRecordsMapper.returnBooks(borrowingRecords) ;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userMapper.selectUserAndRoleAndPrivilegeByUserName(username) ;
        if(user.equals(null)){
            throw new UsernameNotFoundException("用户不存在") ;
        }
        ValueOperations valueOperations = redisTemplate.opsForValue() ;
        //String userJson = (String) valueOperations.get(username) ;
        //SysUser sysUser2 = (SysUser) JSONArray.parseArray(userJson , SysUser.class) ;
        SysUser sysUser2 = (SysUser) valueOperations.get(user.getUserName()) ;
        if((sysUser2 != null)&&(!sysUser2.equals(null))){
            redisTemplate.delete(user.getUserId()) ;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>() ;
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getSysRole().getRole()) ;
        grantedAuthorities.add(grantedAuthority) ;
        user.setGrantedAuthorities(grantedAuthorities);
        String token = tokenUtil.getToken(user) ;
        user.setToken(token);
        valueOperations.set(user.getUserName() ,user);
        return new org.springframework.security.core.userdetails.User(user.getUserName() ,
                new BCryptPasswordEncoder().encode(user.getUserPassword()) , grantedAuthorities) ;
    }
}
