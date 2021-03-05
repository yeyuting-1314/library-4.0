package library.base;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author yeyuting
 * @create 2021/3/1
 */
public class Constant {
    public final static String BORROWBOOKS = "借书" ;
    public final static String RETURNBOOKS = "还书" ;

    public final static Integer ROLE_USER =  1 ;
    public final static Integer ROLE_ADMIN = 2 ;
    public final static Integer ROLE_SUPERADMIN = 3 ;

    public final static Integer USERPRIVILEGENUM = 5 ;
    public final static Integer ADMINPRIVILEGENUM = 8 ;

    public static final String TOKEN_HEADER = "token";

}
