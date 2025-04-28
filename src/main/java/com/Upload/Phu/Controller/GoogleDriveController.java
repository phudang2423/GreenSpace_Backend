//package com.Upload.Phu.Controller;
//
//import com.Upload.Phu.Service.GoogleDriveService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/drive")
//public class GoogleDriveController {
//
//    private GoogleDriveService googleDriveService;
//
//    // Tạo folder mới trong folder cha
//    @PostMapping("/create-folder")
//    public ResponseEntity<String> createFolder(@RequestParam String folderName) {
//        return ResponseEntity.ok(googleDriveService.createFolder(folderName));
//    }
//
//    // Đổi tên folder theo tên hiện tại
//    @PutMapping("/rename-folder")
//    public ResponseEntity<String> renameFolder(@RequestParam String currentName, @RequestParam String newName) {
//        return ResponseEntity.ok(googleDriveService.renameFolder(currentName, newName));
//    }
//
//    // Xóa folder theo tên
//    @DeleteMapping("/delete-folder")
//    public ResponseEntity<String> deleteFolder(@RequestParam String folderName) {
//        return ResponseEntity.ok(googleDriveService.deleteFolder(folderName));
//    }
//
//    // Upload ảnh vào thư mục
//    @PostMapping("/upload-file")
//    public ResponseEntity<String> uploadFile(@RequestParam String folderName, @RequestParam MultipartFile file) throws IOException {
//        return ResponseEntity.ok(googleDriveService.uploadFile(folderName, file));
//    }
//
//    // Đổi tên ảnh theo tên thư mục chứa nó
//    @PutMapping("/rename-file")
//    public ResponseEntity<String> renameFile(@RequestParam String folderName, @RequestParam String oldFileName, @RequestParam String newFileName) {
//        return ResponseEntity.ok(googleDriveService.renameFile(folderName, oldFileName, newFileName));
//    }
//
//    // Xóa ảnh theo tên và thư mục chứa nó
//    @DeleteMapping("/delete-file")
//    public ResponseEntity<String> deleteFile(@RequestParam String folderName, @RequestParam String fileName) {
//        return ResponseEntity.ok(googleDriveService.deleteFile(folderName, fileName));
//    }
//}
