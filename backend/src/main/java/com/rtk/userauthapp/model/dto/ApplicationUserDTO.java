package com.rtk.userauthapp.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUserDTO {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private List<String> roles;
}
