package com.Upload.Phu.Exception;

public enum ErrorCode {
    INVALID_KEY(1001, "Lỗi chưa được định nghĩa"),
    USER_EXISTED(1002, "Tài khoản đã tồn tại!"),
    USERNAME_INVALID(1003, "Tài khoản phải từ 6 ký tự trở lên"),
    PASSWORD_INVALID(1004, "Mật khẩu phải từ 6 ký tự trở lên"),
    USER_NOT_FOUND(1005, "Không tìm thấy người dùng"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
