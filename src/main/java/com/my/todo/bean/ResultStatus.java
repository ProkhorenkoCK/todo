package com.my.todo.bean;

public enum ResultStatus {
    SUCCESS("success"),
    ERROR("error");

    private String message;

    ResultStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
