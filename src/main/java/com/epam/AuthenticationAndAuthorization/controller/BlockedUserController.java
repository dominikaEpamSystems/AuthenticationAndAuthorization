package com.epam.AuthenticationAndAuthorization.controller;

import com.epam.AuthenticationAndAuthorization.model.User;
import com.epam.AuthenticationAndAuthorization.repository.UserRepository;
import com.epam.AuthenticationAndAuthorization.service.LoginAttemptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class BlockedUserController {
    public static final String BLOCKED_USERS = "blockedUsers";
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    public BlockedUserController(UserRepository userRepository, LoginAttemptService loginAttemptService) {
        this.userRepository = userRepository;
        this.loginAttemptService = loginAttemptService;
    }

    @GetMapping("/blocked")
    public String blockedUsers(Model model) {
        List<User> users = userRepository.findAll();
        Map<String, LocalDateTime> blockedUsers = users.stream()
                .filter(user -> !user.isEnabled())
                .collect(Collectors.toMap(User::getUsername, User::getBlockingTime));
        if (!blockedUsers.isEmpty()) {
            model.addAttribute(BLOCKED_USERS, blockedUsers);
        }
        return "blocked";
    }

    @PostMapping("/blocked/{user}")
    public String unBlockUser(@PathVariable(value = "user") String username) {
        User user = userRepository.findByUsernameIgnoreCase(username);
        user.setEnabled(true);
        user.setBlockingTime(LocalDateTime.now());
        userRepository.save(user);
        return "unblocked";
    }
}
