package com.Upload.Phu.Exception;

public class AppException extends RuntimeException {

    public AppException(ErrorCode errorcode) {
        super(errorcode.getMessage());
        this.errorcode = errorcode;
    }

    private ErrorCode errorcode;
    private  Object details;


    public ErrorCode getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(ErrorCode errorcode) {
        this.errorcode = errorcode;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
