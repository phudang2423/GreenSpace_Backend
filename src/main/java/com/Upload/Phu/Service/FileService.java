package com.Upload.Phu.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Tạo thư mục mới
    public String createFolder(String folderName) {
        File folder = new File(uploadDir + "/" + folderName);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                return "Thư mục đã được tạo: " + folder.getAbsolutePath();
            } else {
                return "Không thể tạo thư mục.";
            }
        }
        return "Thư mục đã tồn tại.";
    }

    // Xóa thư mục (chỉ nếu trống)
    public String deleteFolder(String folderName) {
        File folder = new File(uploadDir + "/" + folderName);
        if (folder.exists() && folder.isDirectory() && folder.list().length == 0) {
            if (folder.delete()) {
                return "Đã xóa thư mục: " + folder.getAbsolutePath();
            }
            return "Không thể xóa thư mục.";
        }
        return "Thư mục không tồn tại hoặc không trống.";
    }

    // Upload ảnh vào thư mục
    public String uploadFile(MultipartFile file, String folderName) {
        try {
            File folder = new File(uploadDir + "/" + folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            Path filePath = Paths.get(uploadDir + "/" + folderName, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            return "Tải lên thành công: " + filePath.toString();
        } catch (IOException e) {
            return "Lỗi tải lên file: " + e.getMessage();
        }
    }

    // Xóa ảnh trong thư mục
    public String deleteFile(String folderName, String fileName) {
        File file = new File(uploadDir + "/" + folderName + "/" + fileName);
        if (file.exists()) {
            if (file.delete()) {
                return "Đã xóa file: " + file.getAbsolutePath();
            }
            return "Không thể xóa file.";
        }
        return "File không tồn tại.";
    }
}
