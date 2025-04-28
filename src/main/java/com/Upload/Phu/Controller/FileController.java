package com.Upload.Phu.Controller;

import com.Upload.Phu.Service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    // Tạo thư mục mới
    @PostMapping("/create-folder")
    public ResponseEntity<String> createFolder(@RequestParam String folderName) {
        return ResponseEntity.ok(fileService.createFolder(folderName));
    }

    // Xóa thư mục (chỉ nếu trống)
    @DeleteMapping("/delete-folder")
    public ResponseEntity<String> deleteFolder(@RequestParam String folderName) {
        return ResponseEntity.ok(fileService.deleteFolder(folderName));
    }

    // Upload ảnh
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String folderName) {
        return ResponseEntity.ok(fileService.uploadFile(file, folderName));
    }

    // Xóa ảnh
    @DeleteMapping("/delete-file")
    public ResponseEntity<String> deleteFile(@RequestParam String folderName, @RequestParam String fileName) {
        return ResponseEntity.ok(fileService.deleteFile(folderName, fileName));
    }
}
