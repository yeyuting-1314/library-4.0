package library.system.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author yeyuting
 * @create 2021/3/3
 */
@Repository
@Mapper
public interface RolePrivilegeMapper {
    public Boolean insertOne (Integer roleId , Integer privilegeId) ;
}
