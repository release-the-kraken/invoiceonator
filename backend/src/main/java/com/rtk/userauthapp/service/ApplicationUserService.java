package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ApplicationUserService {
    ApplicationUserDTO addUser(CreateUserRequest request);
    Page<ApplicationUserDTO> listUsers(PageRequest pageRequest);
}
