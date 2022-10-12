package com.rtk.userauthapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    private String id;

    private String number;

    private String description;

    private int dueTimeInDays;

    @CreationTimestamp
    private LocalDate creationDate;

    private LocalDate dueDate;

    @ManyToOne
    private ApplicationUser appUser;

    @ManyToOne
    private Company company;
}
