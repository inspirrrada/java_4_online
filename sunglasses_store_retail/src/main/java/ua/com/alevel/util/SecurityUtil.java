package ua.com.alevel.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.atomic.AtomicBoolean;

public final class SecurityUtil {

    public static String getUsername() {
        Authentication authentication = getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    public static boolean hasRole(String role) {
        Authentication authentication = getAuthentication();
        AtomicBoolean result = new AtomicBoolean(false);
        authentication.getAuthorities().forEach(authority -> {
            if (authority.getAuthority().equalsIgnoreCase(role)) {
                result.set(true);
            }
        });
        return result.get();
    }

    public static void authenticate(UsernamePasswordAuthenticationToken authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
