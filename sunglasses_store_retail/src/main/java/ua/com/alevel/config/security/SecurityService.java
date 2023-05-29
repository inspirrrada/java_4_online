package ua.com.alevel.config.security;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String userName, String password);
}
