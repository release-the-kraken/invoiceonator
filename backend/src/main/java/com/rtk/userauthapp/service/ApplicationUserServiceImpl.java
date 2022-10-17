package com.rtk.userauthapp.service;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;
import com.rtk.userauthapp.model.mapper.ApplicationUserMapper;
import com.rtk.userauthapp.repository.ApplicationUserRepository;
import com.rtk.userauthapp.repository.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {
    @Value("${application.default.newUserRole:ROLE_USER}")
    private String defaultNewUserRole;

    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository userRepository;
    private final ApplicationUserMapper applicationUserMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUserDTO addUser(CreateUserRequest request) {
        ApplicationUser newUser = applicationUserMapper.mapCreateUserRequestToApplicationUser(request);

        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRoles(
                new HashSet<>(
                        Collections.singletonList(applicationUserRoleRepository
                                .findByName(defaultNewUserRole)
                                .orElseThrow(EntityNotFoundException::new))));

        return applicationUserMapper.mapApplicationUserToDTO(userRepository.save(newUser));
    }

    @Override
    public Page<ApplicationUserDTO> listUsers(PageRequest pageRequest) {
        Page<ApplicationUser> usersPage = userRepository.findAll(pageRequest);
        List<ApplicationUserDTO> userDTOS = usersPage
                .stream()
                .map(applicationUserMapper::mapApplicationUserToDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(userDTOS, usersPage.getPageable(), usersPage.getTotalElements());
    }
}
