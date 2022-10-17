package com.rtk.userauthapp.controller;

import com.rtk.userauthapp.model.dto.AddCompanyRequest;
import com.rtk.userauthapp.model.dto.CompanyDTO;
import com.rtk.userauthapp.service.CompanyService;
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
@RequestMapping(path = "/api/company")
@RequiredArgsConstructor
@CrossOrigin
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping(params ={"page", "size"})//todo - remove params
    @PreAuthorize("hasRole('ADMIN')")
    public Page<CompanyDTO> listAll(Pageable pageable){
        log.info("Fetching list of all companies.");
        return companyService.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @GetMapping(value="/{userId}", params ={"page", "size"})
    @PreAuthorize("isAuthenticated()")
    public Page<CompanyDTO> listAllForUser(@PathVariable long userId, Pageable pageable){
        log.info("Fetching list of all companies.");
        return companyService.findAllByAppUser(userId, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated()")
    public CompanyDTO addNewCompany(@RequestBody AddCompanyRequest request){
        return companyService.addNewCompany(request);
    }
}
