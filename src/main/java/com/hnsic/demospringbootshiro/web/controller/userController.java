package com.hnsic.demospringbootshiro.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * ClassName userController
 * Description too
 * Aouthor   Admin_LG
 * Date      2019/12/10  15:10
 * Version   1.0
 **/
@RequestMapping("users")
@Controller
public class userController {
    @Autowired
    com.hnsic.demospringbootshiro.service.userService userService;
    @Autowired
    com.hnsic.demospringbootshiro.service.shiroService shiroService;

    @PostMapping("login")  // users/login
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map) {

        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) { //当前Subject是否进行认证（登录）
            //前台用户传入的用户名和密码 (将用户名和密码封装到UsernamePasswordToken对象中）
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(true);//记住我
            try {
                //进行认证（登录）功能
                currentUser.login(token);
            } catch (UnknownAccountException uae) {//未知帐户异常
                System.out.println(uae.getMessage());
                map.put("msg", "用户名不存在");
                return "login";
            } catch (IncorrectCredentialsException ice) { //凭证匹配器异常 不正确的凭据异常
                System.out.println(ice.getMessage());
                map.put("msg", "密码输入错误");
                return "login";
            } catch (LockedAccountException lae) { //帐户锁定异常 锁定帐户例外  (将来要在业务逻辑中进行判断）
                System.out.println(lae.getMessage());
                map.put("msg", "该用户因违规，被锁定");
                return "login";
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) { // 认证异常 身份验证异常
                map.put("msg", "认证异常 身份验证异常");
                return "login";
            }
        }
        System.out.println();
        //templates   .html
        return "main";
    }

    /**
     * 登出功能 （退出登录）
     */
    @RequestMapping("logout") // users/logout
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        //all done - log out!   登出
        currentUser.logout();
        return "login";
    }

    @RequestMapping("test")
    public String test() {
        System.out.println(1);
        return "login";
    }

    @RequestMapping("add")
    public String testadd() {
        System.out.println("test add");
        return "main";
    }
}
