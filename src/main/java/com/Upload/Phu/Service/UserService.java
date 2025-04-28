package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.Exception.AppException;
import com.Upload.Phu.Exception.ErrorCode;
import com.Upload.Phu.Mapper.UserMapper;
import com.Upload.Phu.Repository.UserRepository;
import com.Upload.Phu.RequestDTO.ChangePasswordRequestDTO;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private PasswordEncoder passwordEncoder;

    //login
    public boolean login(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    //Tạo user
    public User createUser(UserRequestDTO request) {
        User user = new User();
        user.setDisplayName(request.getDisplayName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Mã hóa mật khẩu
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public boolean register(UserRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return false; // username đã tồn tại
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());

        userRepository.save(newUser);
        return true;
    }



    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Xóa người dùng theo id
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);

    }

    // Cập nhật người dùng theo id
    public User updateUser(String username, UserRequestDTO requestDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user.setDisplayName(requestDTO.getDisplayName());
        user.setPhone(requestDTO.getPhone());
        user.setEmail(requestDTO.getEmail());
        user.setDob(requestDTO.getDob());

        return userRepository.save(user);
    }



    //Tìm người dùng theo id
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    //Đổi mật khẩu
    public void changePassword(String username, ChangePasswordRequestDTO request) {
        // 1. Kiểm tra user tồn tại
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND    ));

        // 2. Debug log - kiểm tra giá trị đầu vào
        System.out.println("Debug thông tin đổi mật khẩu:");
        System.out.println("Username: " + username);
        System.out.println("Old password (raw input): " + request.getOldPassword());
        System.out.println("Stored hashed password: " + user.getPassword());

        // 3. Kiểm tra mật khẩu cũ
        boolean isPasswordMatch = passwordEncoder.matches(request.getOldPassword(), user.getPassword());
        System.out.println("Password match result: " + isPasswordMatch);

//        if (!isPasswordMatch) {
//            throw new AppException(ErrorCode.PASSWORD_INVALID);
//        }

         //4. Kiểm tra mật khẩu mới không trùng với mật khẩu cũ
        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_KEY);
        }

        // 5. Mã hóa và lưu mật khẩu mới
        String newEncodedPassword = passwordEncoder.encode(request.getNewPassword());
        System.out.println("New encoded password: " + newEncodedPassword);

        user.setPassword(newEncodedPassword);
        userRepository.save(user);

        System.out.println("Đổi mật khẩu thành công cho user: " + username);
    }


}
