package com.rtk.userauthapp.controller;

import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;
import com.rtk.userauthapp.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class ApplicationUserController {
    private final ApplicationUserService applicationUserService;

    @GetMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') && hasRole('MODERATOR')")
    public List<ApplicationUserDTO> getListOfUsers(){
        return applicationUserService.listUsers();
    }

    @PostMapping()
    @CrossOrigin()
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUserDTO postNewUser(@RequestBody CreateUserRequest request){
        return applicationUserService.addUser(request);
    }
}


