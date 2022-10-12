package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.dto.AddCompanyRequest;
import com.rtk.userauthapp.model.dto.CompanyDTO;
import com.rtk.userauthapp.model.mapper.CompanyMapper;
import com.rtk.userauthapp.repository.ApplicationUserRepository;
import com.rtk.userauthapp.repository.CompanyRepository;
import com.rtk.userauthapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ApplicationUserRepository userRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Page<CompanyDTO> findAll(PageRequest pageRequest) {
        Page<Company> companyPage = companyRepository.findAll(pageRequest);
        List<CompanyDTO> companyDTOS = companyPage.stream()
                .map(companyMapper::mapCompanyToCompanyDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(companyDTOS, companyPage.getPageable(), companyPage.getTotalElements());
    }

    @Override
    public Page<CompanyDTO> findAllByAppUser(long userId, PageRequest pageRequest) {
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        Page<Company> companyPage = companyRepository.findAllByAppUser(user, pageRequest);
        List<CompanyDTO> companyDTOS = companyPage.stream()
                .map(companyMapper::mapCompanyToCompanyDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(companyDTOS, companyPage.getPageable(), companyPage.getTotalElements());
    }

    @Override
    public CompanyDTO addNewCompany(AddCompanyRequest request) {
        ApplicationUser user = userRepository.findById(request.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        Company newCompany = companyMapper.mapAddCompanyRequestToCompany(request, user);

        return companyMapper.mapCompanyToCompanyDTO(companyRepository.save(newCompany));
    }
}
