package com.Upload.Phu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api/drive")
public class ResController {

    @Autowired
    private ResService resService;

    // 📌 API Tải ảnh lên Google Drive
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("folderId") String folderId) {
        try {
            String fileId = resService.uploadImage(file, folderId);
            return ResponseEntity.ok("Tải ảnh thành công! File ID: " + fileId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi tải ảnh: " + e.getMessage());
        }
    }

    // 📌 API Tạo thư mục mới trên Google Drive
    @PostMapping("/create-folder")
    public ResponseEntity<String> createFolder(@RequestParam String folderName, @RequestParam String parentFolderId) {
        try {
            String folderId = resService.createFolder(folderName, parentFolderId);
            return ResponseEntity.ok("Tạo thư mục thành công! Folder ID: " + folderId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi tạo thư mục: " + e.getMessage());
        }
    }

    // 📌 API Lấy danh sách tất cả thư mục trong Drive
    @GetMapping("/list-folders")
    public ResponseEntity<List<String>> listFolders() {
        try {
            List<String> folders = resService.listFolders();
            return ResponseEntity.ok(folders);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 📌 API Lấy danh sách tất cả file trong một thư mục cụ thể
    @GetMapping("/list-files")
    public ResponseEntity<List<String>> listFiles(@RequestParam String folderId) {
        try {
            List<String> files = resService.listFiles(folderId);
            return ResponseEntity.ok(files);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 📌 API Xóa tệp hoặc thư mục (bao gồm nội dung bên trong)
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteFile(@RequestParam String fileId) {
        try {
            resService.deleteFile(fileId);
            return ResponseEntity.ok("Đã xóa tệp hoặc thư mục có ID: " + fileId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa file: " + e.getMessage());
        }
    }

    // 📌 API Xóa thư mục con trong thư mục cha
    @DeleteMapping("/delete-subfolder")
    public ResponseEntity<String> deleteSubFolder(@RequestParam String childFolderId) {
        try {
            resService.deleteSubFolder(childFolderId);
            return ResponseEntity.ok("Đã xóa thư mục con có ID: " + childFolderId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xóa thư mục con: " + e.getMessage());
        }
    }
}
