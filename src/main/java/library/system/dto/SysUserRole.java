package library.system.dto;

/**
 * @author yeyuting
 * @create 2021/3/1
 */
public class SysUserRole {

    private String userId ;

    private Integer roleId ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
