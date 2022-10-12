package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.dto.AddCompanyRequest;
import com.rtk.userauthapp.model.dto.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CompanyService {
    Page<CompanyDTO> findAll(PageRequest pageRequest);

    Page<CompanyDTO> findAllByAppUser(long userId, PageRequest pageRequest);

    CompanyDTO addNewCompany(AddCompanyRequest request);
}
