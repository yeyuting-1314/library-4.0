package library.system.service;

import library.base.Result;
import library.system.dto.BorrowingRecords;
import library.system.dto.Book;
import library.system.dto.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yeyuting
 * @create 2021/2/25
 */
public interface UserService {
    //用户新增一个
    public Boolean insertOneUser(SysUser sysUser) ;

    //用户查询
    public SysUser selectUserByUserId(SysUser sysUser) ;

    //用户注册
    public Object RegisterUser(SysUser sysUser) ;

    //用户登陆
    public Result Login(SysUser user) ;

    //查询所有书籍
    public List<Book> selectAllBooks() ;

    //插入一本书
    public Boolean insertOneBook (Book book) ;

    //插入若干本书
    public Boolean insertManyBooks(List<Book> books) ;

    //查询所有借阅记录
    public List<BorrowingRecords> selectAllBorrowingRecords() ;

    //图书借阅操作
    public Boolean borrowBooks(String bookCode , HttpServletRequest request) ;

    //还书操作
    public Boolean returnBooks(String bookCode , HttpServletRequest request) ;



}
