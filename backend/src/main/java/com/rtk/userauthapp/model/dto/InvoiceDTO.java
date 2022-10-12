package com.rtk.userauthapp.model.dto;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private String id;

    private String number;

    private String description;

    private LocalDate creationDate;

    private LocalDate dueDate;

    private String userFullName;

    private String companyName;
}
