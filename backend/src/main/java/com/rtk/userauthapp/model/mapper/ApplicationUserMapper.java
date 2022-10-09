package com.rtk.userauthapp.model.mapper;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.ApplicationUserRole;
import com.rtk.userauthapp.model.dto.ApplicationUserDTO;
import com.rtk.userauthapp.model.dto.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class ApplicationUserMapper {
    public ApplicationUser mapCreateUserRequestToApplicationUser(CreateUserRequest request){
        ApplicationUser mappedUser = new ApplicationUser();

        mappedUser.setUsername(request.getLogin());
        mappedUser.setPassword(request.getPassword());
        mappedUser.setFirstName(request.getName());
        mappedUser.setLastName(request.getSurname());
        mappedUser.setAccountNonLocked(true);
        mappedUser.setAccountNonExpired(true);
        mappedUser.setCredentialsNonExpired(true);
        mappedUser.setEnabled(true);

        return mappedUser;
    }

    public ApplicationUserDTO mapApplicationUserToDTO(ApplicationUser user){
        return ApplicationUserDTO.builder()
                .id(user.getId())
                .login(user.getUsername())
                .name(user.getFirstName())
                .surname(user.getLastName())
                .roles(user.getRoles().stream()
                        .map(ApplicationUserRole::toString)
                        .collect(Collectors.toList()))
                .build();
    }
}
