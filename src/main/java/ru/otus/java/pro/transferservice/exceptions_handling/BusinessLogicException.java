package ru.otus.java.pro.transferservice.exceptions_handling;

public class BusinessLogicException extends RuntimeException {
    private String code;

    public String getCode() {
        return code;
    }

    public BusinessLogicException(String message, String code) {
        super(message);
        this.code = code;
    }
}
