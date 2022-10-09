package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;

import java.util.List;

public interface ApplicationUserService {
    ApplicationUserDTO addUser(CreateUserRequest request);
    List<ApplicationUserDTO> listUsers();
}
