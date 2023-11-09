package com.epam.AuthenticationAndAuthorization.config;

import com.epam.AuthenticationAndAuthorization.repository.UserRepository;
import com.epam.AuthenticationAndAuthorization.service.LoginAttemptService;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private final LoginAttemptService loginAttemptService;
    private final UserRepository userRepository;

    public AuthenticationFailureListener(LoginAttemptService loginAttemptService, UserRepository userRepository) {
        this.loginAttemptService = loginAttemptService;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String username = (String) event.getAuthentication().getPrincipal();
            if (userRepository.findByUsernameIgnoreCase(username) != null) {
                loginAttemptService.loginFailed(username);
            }
        }
    }
}
