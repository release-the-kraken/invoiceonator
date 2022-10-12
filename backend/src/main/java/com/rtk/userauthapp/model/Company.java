package com.rtk.userauthapp.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "uuid2"
    )
    private String id;
    private String name;
    private String countryOfOrigin;
    private String industry;

    @ManyToOne
    private ApplicationUser appUser;

    @OneToMany(mappedBy = "company")
    @EqualsAndHashCode.Exclude
    private Set<Invoice> invoices;

}
