package library.system.dao;

import library.system.dto.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yeyuting
 * @create 2021/3/3
 */
@Repository
@Mapper
public interface RoleMapper {

    public SysRole selectRoleByRoleId(Integer roleId) ;

    public SysRole selectByPrivilegeUrl(String requestUrl) ;
}
