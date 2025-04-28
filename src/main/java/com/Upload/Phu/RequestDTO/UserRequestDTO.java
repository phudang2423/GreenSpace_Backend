package com.Upload.Phu.RequestDTO;

import com.Upload.Phu.Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserRequestDTO {

    private String displayName;

    @Size(min = 6, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;

    @Email(message = "Please provide a valid email address.")
    @Size(max = 100, message = "Email cannot be longer than 100 characters.")
    private String email;

    @Pattern(regexp = "^[+0-9]{10}$", message = "Phone number must be between 10 digits and may contain a leading '+' sign.")
    private String phone;

    private String address;
    private String role;
    private LocalDate dob;



    // Phương thức setter để thiết lập giá trị từ đối tượng User
    public void setUser(User user) {
        this.displayName = user.getDisplayName();
        this.username = user.getUsername();
        this.password = user.getPassword(); // Cân nhắc không trả password nếu không cần
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.dob = user.getDob();
    }

        // Getter và Setter


    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

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

    public @Pattern(regexp = "^[+0-9]{10}$", message = "Phone number must be between 10 digits and may contain a leading '+' sign.") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "^[+0-9]{10}$", message = "Phone number must be between 10 digits and may contain a leading '+' sign.") String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
