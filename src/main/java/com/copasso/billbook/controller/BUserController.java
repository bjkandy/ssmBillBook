package com.copasso.billbook.controller;

import com.copasso.billbook.bean.BMessage;
import com.copasso.billbook.bean.BUser;
import com.copasso.billbook.service.BUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户User控制类
 */
@Controller
@RequestMapping("user")
public class BUserController {

    @Autowired
    private BUserService bUserService;

    /**
     * 返回用户列表页面
     * @return
     */
    @RequestMapping("")
    public String userlist(){
        return "userlist";
    }

    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public BUser login(@Param("username")String username,@Param("password")String password){

        return bUserService.checkLogin(new BUser(username,password));
    }

    /**
     * 添加用户，返回状态值
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("sign")
    @ResponseBody
    public BUser signup(@Param("username")String username,@Param("password")String password,@Param("mail")String mail){
        return bUserService.register(new BUser(username,password,mail));
    }

    /**
     * 用户注册邮箱验证
     * @param code
     * @return
     */
    @RequestMapping("mail/verify")
    @ResponseBody
    public BUser verifyMail(@Param("code")String code){
        return bUserService.verifyMail(code);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping("id/{id}")
    @ResponseBody
    public BMessage lookupUserById(@PathVariable("id")Integer id ){
        return BMessage.success().add("user",bUserService.findUserById(id));
    }

    /**
     * 根据name查询用户
     * @param name
     * @return
     */
    @RequestMapping("name/{name}")
    @ResponseBody
    public BUser lookupUserByName(@PathVariable("name")String name){
        return  bUserService.findUserByUserName(name);
    }
}
