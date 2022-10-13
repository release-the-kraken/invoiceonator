package com.rtk.userauthapp.controller;

import com.rtk.userauthapp.model.dto.CreateInvoiceRequest;
import com.rtk.userauthapp.model.dto.InvoiceDTO;
import com.rtk.userauthapp.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/invoice")
@RequiredArgsConstructor
@CrossOrigin
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(params ={"page", "size"})
    @PreAuthorize("hasRole('ADMIN')")
    public Page<InvoiceDTO> listAll(Pageable pageable){
        log.info("Fetching list of all companies.");
        return invoiceService.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping(value="/{userId}", params ={"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public Page<InvoiceDTO> listAllForUser(@PathVariable long userId, Pageable pageable){
        log.info("Fetching list of all companies.");
        return invoiceService.findAllByAppUser(userId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping(value="/{userId}/{companyId}", params ={"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public Page<InvoiceDTO> listAllForUser(@PathVariable long userId, @PathVariable String companyId, Pageable pageable){
        log.info("Fetching list of all companies.");
        return invoiceService.findAllByAppUserAndCompany(userId, companyId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public InvoiceDTO addNewCompany(@RequestBody CreateInvoiceRequest request){
        return invoiceService.createNewInvoice(request);
    }
}
