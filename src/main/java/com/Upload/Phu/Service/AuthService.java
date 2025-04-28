package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.Repository.UserRepository;
import com.Upload.Phu.RequestDTO.ChangePasswordRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // BCrypt

    public void changePassword(String username, ChangePasswordRequestDTO request) {
        // 1. Tìm user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        // 2. Debug log
        System.out.println("Mật khẩu cũ (input): " + request.getOldPassword());
        System.out.println("Mật khẩu đã hash (db): " + user.getPassword());

        // 3. Kiểm tra mật khẩu cũ
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        // 4. Kiểm tra mật khẩu mới không trùng cũ
        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Mật khẩu mới phải khác mật khẩu cũ");
        }

        // 5. Mã hóa và lưu mật khẩu mới
        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
