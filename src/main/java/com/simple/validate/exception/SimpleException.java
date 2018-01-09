package com.simple.validate.exception;

public class SimpleException extends RuntimeException{

    /**
     * The error code.
     */
    private  int code;

    /**
     * The optional error data.
     */
    private  Object data;

    public SimpleException() {
        super();
    }

    public SimpleException(int code,String message) {
        super(message);
        this.code = code;
        this.data = message;
    }


}
