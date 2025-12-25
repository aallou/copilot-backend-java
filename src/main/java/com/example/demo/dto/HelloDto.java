package com.example.demo.dto;

public class HelloDto {
    private String message;

    public HelloDto() {}

    public HelloDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
