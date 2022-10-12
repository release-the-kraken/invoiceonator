package com.rtk.userauthapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCompanyRequest {
    private String name;
    private String countryOfOrigin;
    private String industry;

    private long userId;
}
