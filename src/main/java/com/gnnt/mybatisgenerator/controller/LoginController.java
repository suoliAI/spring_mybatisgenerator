package com.gnnt.mybatisgenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping("/logon")
    public String login(){
        return "login";
    }
}
