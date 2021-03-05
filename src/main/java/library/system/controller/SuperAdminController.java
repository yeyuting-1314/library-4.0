package library.system.controller;

import library.base.Result;
import library.system.dto.Book;
import library.system.dto.SysUser;
import library.system.service.UserService;
import library.util.BaseEnums;
import library.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yeyuting
 * @create 2021/3/2
 */
@RestController
@RequestMapping("sys/superAdmin")
public class SuperAdminController {

    @Autowired
    UserService userService ;

    //用户登陆
    @RequestMapping("/login")
    public Result Login(@RequestBody SysUser sysUser){
        Result result = Results.successWithData
                (userService.Login(sysUser), BaseEnums.SUCCESS.code() , BaseEnums.SUCCESS.desc()) ;
        return  result ;
    }

    //超级管理员权限  新增一本书本
    @PostMapping("/insertOneBook")
    public Result insertOneBook(@RequestBody Book book){
        Result result = Results.successWithData(userService.insertOneBook(book));
        return  result ;
    }

    //超级管理员  新增若干本书本
    @PostMapping("insertManyBooks")
    public  Result insertManyBooks(@RequestBody List<Book> books){
        Result result = Results.successWithData(userService.insertManyBooks(books)) ;
        return result ;
    }



}
