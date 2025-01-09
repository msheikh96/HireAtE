package com.HireAtE.Response;


public class APIResponseClass {
    private String message;
    private String code;
    private Object data;

    // Constructors, getters, and setters
    public APIResponseClass(String message, String Code, Object data) {
        this.message = message;
        this.code = Code;
        this.data = data;
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

    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }

   
}
