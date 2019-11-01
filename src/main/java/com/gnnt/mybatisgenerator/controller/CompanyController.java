package com.gnnt.mybatisgenerator.controller;

import com.gnnt.mybatisgenerator.model.Company;
import com.gnnt.mybatisgenerator.response.BaseResponse;
import com.gnnt.mybatisgenerator.response.DataResponse;
import com.gnnt.mybatisgenerator.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService companyService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public DataResponse queryAll(){
        List<Company> list = companyService.queryAll();
       return  DataResponse.buildSuccess(list);
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "success";
    }
}
