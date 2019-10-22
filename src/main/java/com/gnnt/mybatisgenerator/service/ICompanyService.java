package com.gnnt.mybatisgenerator.service;

import com.gnnt.mybatisgenerator.model.Company;

import java.util.List;

public interface ICompanyService {

    abstract List<Company> queryAll();
}
