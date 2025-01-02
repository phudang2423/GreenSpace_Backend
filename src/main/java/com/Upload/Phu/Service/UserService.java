package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.Exception.AppException;
import com.Upload.Phu.Exception.ErrorCode;
import com.Upload.Phu.Mapper.UserMapper;
import com.Upload.Phu.Repository.UserRepository;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserRequestDTO requestDTO) {

        if(userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(requestDTO);

        return userRepository.save(user);
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
    public User updateUser(int id, UserRequestDTO requestDTO) {
        if(userRepository.existsById(getUserById(id).getId())) {
            User user = getUserById(id);
            userMapper.updateUser(user, requestDTO);
            return userRepository.save(user);
        }
        return null;
    }

    //Tìm người dùng theo id
    public User getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
    }
}
