package com.HireAtE.Response;


public class APIResponseClass {
    private String message;
    private String code;

    // Constructors, getters, and setters
    public APIResponseClass(String message, String Code) {
        this.message = message;
        this.code = Code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   
}
