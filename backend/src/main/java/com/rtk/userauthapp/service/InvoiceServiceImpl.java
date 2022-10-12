package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.Invoice;
import com.rtk.userauthapp.model.dto.CreateInvoiceRequest;
import com.rtk.userauthapp.model.dto.InvoiceDTO;
import com.rtk.userauthapp.model.mapper.InvoiceMapper;
import com.rtk.userauthapp.repository.ApplicationUserRepository;
import com.rtk.userauthapp.repository.CompanyRepository;
import com.rtk.userauthapp.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ApplicationUserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final InvoiceMapper invoiceMapper;


    @Override
    public Page<InvoiceDTO> findAll(PageRequest pageRequest) {
        Page<Invoice> invoicePage = invoiceRepository.findAll(pageRequest);
        List<InvoiceDTO> invoiceDTOS = invoicePage.stream()
                .map(invoiceMapper::mapInvoiceToInvoiceDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDTOS, invoicePage.getPageable(), invoicePage.getTotalElements());
    }

    @Override
    public Page<InvoiceDTO> findAllByAppUser(long userId, PageRequest pageRequest) {
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        Page<Invoice> invoicePage = invoiceRepository.findAllByAppUser(user, pageRequest);
        List<InvoiceDTO> invoiceDTOS = invoicePage.stream()
                .map(invoiceMapper::mapInvoiceToInvoiceDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDTOS, invoicePage.getPageable(), invoicePage.getTotalElements());
    }

    @Override
    public Page<InvoiceDTO> findAllByAppUserAndCompany(long userId, String companyId, PageRequest pageRequest) {
        ApplicationUser user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        Company company = companyRepository.findById(companyId)
                .orElseThrow(EntityNotFoundException::new);
        Page<Invoice> invoicePage = invoiceRepository.findAllByAppUserAndCompany(user, company, pageRequest);
        List<InvoiceDTO> invoiceDTOS = invoicePage.stream()
                .map(invoiceMapper::mapInvoiceToInvoiceDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(invoiceDTOS, invoicePage.getPageable(), invoicePage.getTotalElements());
    }

    @Override
    public InvoiceDTO createNewInvoice(CreateInvoiceRequest invoiceRequest) {
        ApplicationUser user = userRepository.findById(invoiceRequest.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        Company company = companyRepository.findById(invoiceRequest.getCompanyId())
                .orElseThrow(EntityNotFoundException::new);
        Invoice newInvoice = invoiceMapper.mapCreateInvoiceRequestToInvoice(invoiceRequest, user, company);

        return invoiceMapper.mapInvoiceToInvoiceDTO(invoiceRepository.save(newInvoice));
    }
}
