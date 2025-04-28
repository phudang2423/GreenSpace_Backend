package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.Repository.UserRepository;
import com.Upload.Phu.RequestDTO.ApiResponse;
import com.Upload.Phu.RequestDTO.ChangePasswordRequestDTO;
import com.Upload.Phu.RequestDTO.LoginRequestDTO;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import com.Upload.Phu.Service.AuthService;
import com.Upload.Phu.Service.UserService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserRequestDTO request) {
        ApiResponse<String> response = new ApiResponse<>();

        boolean isLoginSuccessful = userService.login(request.getUsername(), request.getPassword());

        if (isLoginSuccessful) {
            response.setCode(1000);
            response.setMessage("Đăng nhập thành công!");
            response.setResult("Chào mừng " + request.getUsername());
        } else {
            response.setCode(1001);
            response.setMessage("Sai tên đăng nhập hoặc mật khẩu!");
            response.setResult(null);
        }

        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Tên người dùng đã tồn tại.");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());

        userRepository.save(newUser);
        return ResponseEntity.ok("Đăng ký thành công.");
    }

    //user thay đổi mật khẩu
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO request) {
        userService.changePassword(request.getUsername(), request);
        return ResponseEntity.ok("Đổi mật khẩu thành công!");
    }

}
