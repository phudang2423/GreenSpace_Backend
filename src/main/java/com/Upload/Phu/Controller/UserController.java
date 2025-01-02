package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.User;
import com.Upload.Phu.RequestDTO.ApiResponse;
import com.Upload.Phu.RequestDTO.UserRequestDTO;
import com.Upload.Phu.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Lấy tất cả người dùng
    @GetMapping("/Users")
    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Lấy tất cả người dùng thành công!");
        apiResponse.setResult(userService.getAllUsers());
        return apiResponse;
    }

    //Lấy người dùng theo id
    @GetMapping("/Users/{id}")
    public ApiResponse<User> getUser(@PathVariable int id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    // Tạo mới người dùng
    @PostMapping("/Users")
    public ApiResponse<User> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Tạo người dùng thành công!");
        apiResponse.setResult(userService.createUser(requestDTO));
        return apiResponse;
    }

    // Xóa người dùng theo id
    @DeleteMapping("/Users/{id}")
    public ApiResponse deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Đã xóa thành công!");
        return apiResponse;
    }



    // Sửa người dùng theo id
    @PutMapping("/Users/{id}")
    public ApiResponse<User> updateUser(@PathVariable int id, @RequestBody UserRequestDTO requestDTO) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setMessage("Cập nhật người dùng thành công!");
        apiResponse.setResult(userService.updateUser(id, requestDTO));
        return apiResponse;
    }
}
