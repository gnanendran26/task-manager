package com.example.task_manager.service.impl;


import com.example.task_manager.dto.LoginRequest;
import com.example.task_manager.dto.LoginResponse;
import com.example.task_manager.dto.RegisterRequest;
import com.example.task_manager.entity.Role;
import com.example.task_manager.entity.User;
import com.example.task_manager.repository.UserRepository;
import com.example.task_manager.security.JwtService;
import com.example.task_manager.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
