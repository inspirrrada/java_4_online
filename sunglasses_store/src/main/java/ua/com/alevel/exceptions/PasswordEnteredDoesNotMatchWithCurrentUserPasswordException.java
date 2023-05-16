package ua.com.alevel.exceptions;

public class PasswordEnteredDoesNotMatchWithCurrentUserPasswordException extends RuntimeException {

    public PasswordEnteredDoesNotMatchWithCurrentUserPasswordException(String msg) {
        super(msg);
    }
}
