package com.gnnt.mybatisgenerator.controller;

import com.gnnt.mybatisgenerator.model.User;
import com.gnnt.mybatisgenerator.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/loginPage")
    public String forwardLogin(){
        log.info("forwardLogin ..........");
        return "login";
    }
    @GetMapping("/logout")
    @ResponseBody
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "sucess";
    }
    @GetMapping("/main")
    public String main(){
        return "main";
    }


    @PostMapping("/logon")
    @ResponseBody
    public BaseResponse executeLogin(@RequestParam("username") String name, @RequestParam("password")String password) {
        log.info("name {},password {} ",name,password);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                name,
                password,
                true
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            subject.getSession().setAttribute("sessionID",name);
            long timeout = SecurityUtils.getSubject().getSession().getTimeout();
            log.info("session timeout {} 毫秒",timeout);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (AuthenticationException e) {
            return BaseResponse.buildFailed("400","账号或密码错误！");
        } catch (AuthorizationException e) {
            return BaseResponse.buildFailed("400","没有权限！");
        }
        return BaseResponse.buildSuccess();
    }
}
