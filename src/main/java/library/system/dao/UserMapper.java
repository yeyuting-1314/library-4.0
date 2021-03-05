package library.system.dao;

import library.system.dto.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
@Repository
@Mapper
public interface UserMapper {
    public Boolean insertOneUser(SysUser sysUser) ;

    public SysUser selectUserByUserId(SysUser sysUser) ;

    public SysUser selectUserAndRoleByUserName(String userName) ;

    public SysUser selectUserByUserName(String userName) ;

    public SysUser selectUserAndRoleAndPrivilegeByUserName(String userName) ;

    public String selectUserIdByUserName(String userName ) ;


}
