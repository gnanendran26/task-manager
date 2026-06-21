package com.example.task_manager.service;

import com.example.task_manager.dto.LoginRequest;
import com.example.task_manager.dto.LoginResponse;
import com.example.task_manager.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
