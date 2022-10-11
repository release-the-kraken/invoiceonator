package com.rtk.userauthapp.configuration;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.model.ApplicationUserRole;
import com.rtk.userauthapp.model.config.DefaultUser;
import com.rtk.userauthapp.repository.ApplicationUserRepository;
import com.rtk.userauthapp.repository.ApplicationUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final InitialUsersConfiguration initialUsersConfiguration;
    private final ApplicationUserRoleRepository applicationUserRoleRepository;
    private final ApplicationUserRepository applicationUserRepository;

    private final PasswordEncoder passwordEncoder;

    //Initiates when all beans are loaded
    @EventListener(ContextRefreshedEvent.class)
    public void initializeDatabase() {
        log.info(String.format("Initial users: %s", initialUsersConfiguration));

        initializeRoles(initialUsersConfiguration.getRoles());
        initializeUsers(initialUsersConfiguration.getUsers());
    }

    private void initializeUsers(List<DefaultUser> users) {
        users.stream()
                .filter(defaultUser -> !applicationUserRepository.existsByUsername(defaultUser.getUsername()))
                .map(this::mapDefaultUserToApplicationUser)
                .forEach(applicationUserRepository::save);
    }

    private ApplicationUser mapDefaultUserToApplicationUser(DefaultUser defaultUser) {
        Set<ApplicationUserRole> userRoles = defaultUser.getRoles()
                .stream()
                .map(applicationUserRoleRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        return ApplicationUser.builder()
                .password(passwordEncoder.encode(defaultUser.getPassword()))
                .username(defaultUser.getUsername())
                .roles(userRoles)
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .enabled(true)
                .build();
    }

    private void initializeRoles(List<String> roleNames) {
        for (String roleName : roleNames) {
            if (!applicationUserRoleRepository.existsByName(roleName)) {
                createRoleWithName(roleName);
            }
        }
    }

    private void createRoleWithName(String roleName) {
        log.info(String.format("Creating role: %s", roleName));
        ApplicationUserRole role = ApplicationUserRole.builder()
                .name(roleName)
                .build();

        applicationUserRoleRepository.save(role);
    }
}
