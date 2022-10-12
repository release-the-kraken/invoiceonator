package com.rtk.userauthapp.model.mapper;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.dto.AddCompanyRequest;
import com.rtk.userauthapp.model.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyMapper {
    private final InvoiceMapper invoiceMapper;
    public Company mapAddCompanyRequestToCompany(AddCompanyRequest request, ApplicationUser user){
        Company newCompany = new Company();

        newCompany.setName(request.getName());
        newCompany.setCountryOfOrigin(request.getCountryOfOrigin());
        newCompany.setIndustry(request.getIndustry());
        newCompany.setAppUser(user);

        return newCompany;
    }

    public CompanyDTO mapCompanyToCompanyDTO(Company company){
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .countryOfOrigin(company.getCountryOfOrigin())
                .industry(company.getIndustry())
                .userFullName(String.format("%s %s",
                        company.getAppUser().getFirstName(),
                        company.getAppUser().getLastName()))
                .invoices(company.getInvoices().stream()
                        .map(invoiceMapper::mapInvoiceToInvoiceDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
