package library.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import library.base.Constant;
import library.config.SecurityConfiguration;
import library.system.dto.SysUser;
import library.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author yeyuting
 * @create 2021/3/4
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TokenUtil tokenUtil;

    //redisTemplate注入
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager , RedisTemplate redisTemplate , TokenUtil tokenUtil){
        super(authenticationManager) ;
        this.redisTemplate = redisTemplate ;
        this.tokenUtil = tokenUtil ;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        //如果请求头中没有token就放行了
        String tokenHeader =request.getHeader(Constant.TOKEN_HEADER) ;
        if(StringUtils.isEmpty(tokenHeader)){
            super.doFilterInternal(request , response , chain);
            return ;
        }
        //有的话进行解析
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request , response , chain);

    }

    // 从token中获取用户信息
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){
        //解析token  拿到username
        DecodedJWT jwt = tokenUtil.deToken(tokenHeader) ;
        String userName = jwt.getClaim("userName").asString() ;
        if(userName.equals(null)){
            return null ;
        }
        ValueOperations valueOperations =redisTemplate.opsForValue() ;
        SysUser sysUser = (SysUser) valueOperations.get(userName) ;
        List<GrantedAuthority> grantedAuthorities = sysUser.getGrantedAuthorities() ;
        if((sysUser.getToken()).equals(tokenHeader)){
            String userName1 = sysUser.getUserName() ;
            return new UsernamePasswordAuthenticationToken(userName1 , null , grantedAuthorities);
        }
        return null ;
    }
}
