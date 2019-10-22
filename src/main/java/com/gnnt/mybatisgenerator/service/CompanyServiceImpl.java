package com.gnnt.mybatisgenerator.service;

import com.gnnt.mybatisgenerator.mapper.CompanyMapperExt;
import com.gnnt.mybatisgenerator.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyMapperExt companyMapperExt;

    @Override
    public List<Company> queryAll() {
        return companyMapperExt.queryCompanyList();
    }
}
