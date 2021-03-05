package library.system.dto;

/**
 * @author yeyuting
 * @create 2021/3/3
 */
public class RolePrivilege {

    Integer roleId ;
    Integer privilegeId ;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}
