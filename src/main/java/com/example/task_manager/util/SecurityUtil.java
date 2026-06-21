package com.example.task_manager.util;

import com.example.task_manager.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static User getCurrentUser() {
        Authentication auth =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        return (User) auth.getPrincipal();
    }
}
