package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.dto.CreateInvoiceRequest;
import com.rtk.userauthapp.model.dto.InvoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface InvoiceService {
    Page<InvoiceDTO> findAll(PageRequest pageRequest);

    Page<InvoiceDTO> findAllByAppUser(long userId, PageRequest pageRequest);

    Page<InvoiceDTO> findAllByAppUserAndCompany(long userId, String companyId, PageRequest pageRequest);

    InvoiceDTO createNewInvoice(CreateInvoiceRequest invoiceRequest);
}
