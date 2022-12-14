package com.rtk.userauthapp.component;

import com.rtk.userauthapp.model.ApplicationUser;
import com.rtk.userauthapp.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrincipalComponent {
    private final ApplicationUserRepository applicationUserRepository;

    public Optional<ApplicationUser> findUser(UsernamePasswordAuthenticationToken springToken) {
        return applicationUserRepository.findByUsername(String.valueOf(springToken.getPrincipal()));
    }

    public ApplicationUser getUser(UsernamePasswordAuthenticationToken springToken) {
        return applicationUserRepository
                .findByUsername(String.valueOf(springToken.getPrincipal()))
                .orElseThrow(EntityNotFoundException::new);
    }

    public ApplicationUser getUser(UsernamePasswordAuthenticationToken springToken, Long userId) {
        ApplicationUser user = getUser(springToken);

        if (!user.getId().equals(userId)) {
            throw new IllegalStateException(String.format("Invalid user id sent in the request! %d != %d", user.getId(), userId));
        }
        return user;
    }
}