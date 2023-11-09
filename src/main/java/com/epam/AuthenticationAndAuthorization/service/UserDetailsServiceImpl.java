package com.epam.AuthenticationAndAuthorization.service;

import com.epam.AuthenticationAndAuthorization.config.AuthenticationFailureHandler;
import com.epam.AuthenticationAndAuthorization.model.User;
import com.epam.AuthenticationAndAuthorization.repository.UserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User Not Found";
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    public UserDetailsServiceImpl(UserRepository userRepository, LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND);
        } else {
            if (loginAttemptService.isBlocked(username)) {
                user.setEnabled(false);
                user.setBlockingTime(loginAttemptService.getCachedValue(username).getBlockedTimestamp());
                userRepository.save(user);
                throw new LockedException(AuthenticationFailureHandler.USER_IS_BLOCKED);
            }
        }
        return UserDetailsImpl.build(user);
    }
}
