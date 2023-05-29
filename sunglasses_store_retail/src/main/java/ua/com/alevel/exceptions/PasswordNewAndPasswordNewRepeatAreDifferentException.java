package ua.com.alevel.exceptions;

public class PasswordNewAndPasswordNewRepeatAreDifferentException extends RuntimeException {

    public PasswordNewAndPasswordNewRepeatAreDifferentException(String msg) {
        super(msg);
    }
}
