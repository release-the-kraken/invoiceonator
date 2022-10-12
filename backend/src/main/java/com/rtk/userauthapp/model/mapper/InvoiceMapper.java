package com.rtk.userauthapp.model.mapper;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import com.rtk.userauthapp.model.Invoice;
import com.rtk.userauthapp.model.dto.CreateInvoiceRequest;
import com.rtk.userauthapp.model.dto.InvoiceDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class InvoiceMapper {
    public Invoice mapCreateInvoiceRequestToInvoice(CreateInvoiceRequest request,
                                                    ApplicationUser user,
                                                    Company company){
        Invoice newInvoice = new Invoice();

        newInvoice.setNumber(request.getNumber());
        newInvoice.setDescription(request.getDescription());
        newInvoice.setDueDate(LocalDate.now().plusDays(request.getDueTimeInDays()));
        newInvoice.setAppUser(user);
        newInvoice.setCompany(company);

        return newInvoice;
    }

    public InvoiceDTO mapInvoiceToInvoiceDTO(Invoice invoice){
        return InvoiceDTO.builder()
                .id(invoice.getId())
                .number(invoice.getNumber())
                .description(invoice.getDescription())
                .creationDate(invoice.getCreationDate())
                .dueDate(invoice.getDueDate())
                .userFullName(String.format("%s %s",
                        invoice.getAppUser().getFirstName(),
                        invoice.getAppUser().getLastName()))
                .companyName(invoice.getCompany().getName())
                .build();
    }
}
