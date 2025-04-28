package com.Upload.Phu.RequestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {
    @Size(min = 6, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;

    @Email(message = "Please provide a valid email address.")
    @Size(max = 100, message = "Email cannot be longer than 100 characters.")
    private String email;

    public @Size(min = 6, message = "USERNAME_INVALID") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 6, message = "USERNAME_INVALID") String username) {
        this.username = username;
    }

    public @Size(min = 6, message = "PASSWORD_INVALID") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "PASSWORD_INVALID") String password) {
        this.password = password;
    }

    public @Email(message = "Please provide a valid email address.") @Size(max = 100, message = "Email cannot be longer than 100 characters.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Please provide a valid email address.") @Size(max = 100, message = "Email cannot be longer than 100 characters.") String email) {
        this.email = email;
    }
}
