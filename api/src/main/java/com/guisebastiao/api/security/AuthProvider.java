package com.guisebastiao.api.security;

import com.guisebastiao.api.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthProvider {
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated()) {
            return null;
        }

        Object principal = auth.getPrincipal();

        if(principal instanceof User user) {
            return user;
        }

        return null;
    }
}
