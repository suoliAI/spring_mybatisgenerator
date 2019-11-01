package com.gnnt.mybatisgenerator;


import com.gnnt.mybatisgenerator.mapper.*;
import com.gnnt.mybatisgenerator.model.*;
import com.gnnt.mybatisgenerator.service.ICompanyService;
import com.gnnt.mybatisgenerator.service.IUserService;
import com.gnnt.mybatisgenerator.untils.ShiroEncryption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class MybatisGeneratorApplicationTests {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyMapperExt companyMapperExt;

    @Autowired
    private UserMapperExt userMapperExt;

    @Autowired
    private RoleMapperExt roleMapperExt;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICompanyService companyService;
    @Test
    void contextLoads() {
    }
    @Test
    void test_insert(){
        Company company = new Company();
        company.setCode("004");
        company.setName("上海控股吖");
        company.setAddress("上海市开发区");
        company.setOwner("明生");
        company.setMoney(7000000.00);
        int result = companyMapper.insert(company);
        System.out.println("运行结果："+result);
    }
    
    @Test
    void test_select(){
        CompanyCriteria criteria = new CompanyCriteria();
        CompanyCriteria.Criteria criteria1 = criteria.createCriteria();
        criteria1.andMoneyLessThanOrEqualTo(7000000.00);
        List<Company> companies = companyMapper.selectByExample(criteria);
        System.out.println("查询结果："+companies.toString());
    }
    @Test
    void test_pageHelper(){
        List<Company> list= companyMapperExt.queryCompanyList();


    }

    @Test
    void test_ManytoMany(){
        UserExt userExt = userMapperExt.selectById(1);
        System.out.println(userExt.toString());
        System.out.println(userExt.getRoles().toString());
    }
    @Test
    void test_ManytoMany2(){
        RoleExt roleExt = roleMapperExt.selectById(1);
        System.out.println(roleExt.toString());
        System.out.println(roleExt.getPermissions().toString());
    }
    @Test
    void test_shiro(){
        System.out.println(ShiroEncryption.shiroEncryption("123456", UUID.randomUUID().toString()));
        System.out.println( UUID.randomUUID().toString());
    }
    @Test
    void test_transaction(){
        User user = new User();
        user.setId(1);
        user.setName("admin20");
        user.setSalt(UUID.randomUUID().toString());
        user.setPassword("123456789");
        userService.update(user);
    }


}
