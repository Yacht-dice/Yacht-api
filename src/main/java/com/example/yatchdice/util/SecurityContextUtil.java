package com.example.yatchdice.util;

import com.example.yatchdice.exception.badrequest.BadRequestTokenException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class SecurityContextUtil {

    public static String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            throw new BadRequestTokenException();
        }
        return (String) principal;
    }
}
