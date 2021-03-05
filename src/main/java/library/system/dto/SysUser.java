package library.system.dto;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 3529219554011221820L;

    Integer id ;

    String userName ;

    String userId ;

    String userPassword ;

    String token ;

    SysRole sysRole ;

    //拿到对应的角色，并放到redis中
    List<GrantedAuthority> grantedAuthorities;

    List<SysPrivilege> privilegeList ;

    String extendedField2 ;

    String extendedField3 ;

    //redis的这些序列化方式,使用的是无参构造函数进行创建对象set方法进行赋值,方法中存在有参的构造函数,默认存在的无参构造函数是不存在的(继承自object),必须显示的去重写.
    public SysUser(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public List<SysPrivilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<SysPrivilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }
}
