package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.Repository.UserRepository;
import com.Upload.Phu.RequestDTO.ApiResponse;
import com.Upload.Phu.RequestDTO.ChangePasswordRequestDTO;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import com.Upload.Phu.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173") // Cho phép frontend gọi API
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    // Lấy tất cả người dùng
    @GetMapping("/users")
    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Lấy tất cả người dùng thành công!");
        apiResponse.setResult(userService.getAllUsers());
        return apiResponse;
    }

    //Lấy người dùng theo id
    @GetMapping("/users/{id}")
    public ApiResponse<User> getUser(@PathVariable int id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserRequestDTO> getUserByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserRequestDTO userDTO = new UserRequestDTO();
        userDTO.setUser(user);  // Thiết lập giá trị từ User vào UserRequestDTO

        return ResponseEntity.ok(userDTO);
    }


    // Tạo mới người dùng
    @PostMapping("/users")
    public ApiResponse<User> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Tạo người dùng thành công!");
        apiResponse.setResult(userService.createUser(requestDTO));
        return apiResponse;
    }


    // Xóa người dùng theo id
    @DeleteMapping("/users/{id}")
    public ApiResponse deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Đã xóa thành công!");
        return apiResponse;
    }



    // Sửa người dùng theo username
    @PutMapping("/users/{username}")
    public ApiResponse<User> updateUser(@PathVariable String username, @RequestBody UserRequestDTO requestDTO) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Cập nhật người dùng thành công!");
        apiResponse.setResult(userService.updateUser(username, requestDTO)); // dùng username
        return apiResponse;
    }






}
