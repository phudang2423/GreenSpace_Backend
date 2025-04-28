package com.Upload.Phu.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; // Khóa chính

    @Column(nullable = false)
    private String displayName;

   @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
   private String password;

    @Email(message = "Please provide a valid email address.")
    @Size(max = 100, message = "Email cannot be longer than 100 characters.")
    private String email;
    @Pattern(regexp = "^[+0-9]{10}$", message = "Phone number must be between 10 digits and may contain a leading '+' sign.")
    private String phone;
    private String address;
    private String role;
    private LocalDate dob; // Ngày sinh

    @Column(updatable = false)
    private LocalDateTime createAt; // Thời gian tạo

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
    }

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
