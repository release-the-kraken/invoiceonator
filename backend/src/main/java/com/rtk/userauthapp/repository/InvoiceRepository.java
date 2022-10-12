package com.rtk.userauthapp.repository;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Page<Invoice> findAllByAppUser(ApplicationUser user, PageRequest pageRequest);

    Page<Invoice> findAllByAppUserAndCompany(ApplicationUser user, Company company, PageRequest pageRequest);
}
