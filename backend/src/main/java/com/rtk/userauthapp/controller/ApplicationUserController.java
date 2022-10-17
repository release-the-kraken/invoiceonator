package com.rtk.userauthapp.controller;

import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;
import com.rtk.userauthapp.service.ApplicationUserService;
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
@RequestMapping(path = "/api/user")
@CrossOrigin()
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping(params ={"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public Page<ApplicationUserDTO> getListOfUsers(Pageable pageable) {

        return applicationUserService.listUsers(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ApplicationUserDTO postNewUser(@RequestBody CreateUserRequest request) {
        return applicationUserService.addUser(request);
    }
}


