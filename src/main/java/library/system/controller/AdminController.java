package library.system.controller;

import library.base.Result;
import library.system.dto.SysUser;
import library.system.service.UserService;
import library.util.BaseEnums;
import library.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yeyuting
 * @create 2021/3/2
 */
@RestController
@RequestMapping("sys/admin")
public class AdminController {
    @Autowired
    UserService userService ;

    //管理员登陆
    @RequestMapping("/login")
    public Result Login(@RequestBody SysUser sysUser){
        Result result = Results.successWithData
                (userService.Login(sysUser), BaseEnums.SUCCESS.code() , BaseEnums.SUCCESS.desc()) ;
        return  result ;
    }

    //管理员权限  查看借阅记录
    @GetMapping("/selectAllBorrowingRecords")
    public  Result selectAllBorrowingRecords(){
        Result result = Results.successWithData(userService.selectAllBorrowingRecords())  ;
        return  result ;
    }


}
