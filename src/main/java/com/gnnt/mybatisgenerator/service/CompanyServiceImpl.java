package com.gnnt.mybatisgenerator.service;

import com.gnnt.mybatisgenerator.mapper.CompanyMapper;
import com.gnnt.mybatisgenerator.mapper.CompanyMapperExt;
import com.gnnt.mybatisgenerator.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyMapperExt companyMapperExt;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> queryAll() {
        return companyMapperExt.queryCompanyList();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Company company) {
        int insert = companyMapper.insert(company);
        int a = insert/0;
    }
}
