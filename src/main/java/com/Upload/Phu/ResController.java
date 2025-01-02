package com.Upload.Phu;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class ResController {
    @Autowired
    private ResService resService;

    @PostMapping("/create-folder")
    public String createFolder(@RequestParam String folderName, @RequestParam(defaultValue = "1Ka8479KlAgWySdSBYa2kmyFGO4vjJLvv") String folderIdParent) {
        try {
            String folderId = resService.createFolder(folderName, folderIdParent);
            return "Thư mục đã được tạo thành công với ID: " + folderId;
        } catch (IOException e) {
            return "Lỗi khi tạo thư mục: " + e.getMessage();
        }
    }

    @PostMapping("/upload-image")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String folderId) {
        try {
            // Đường dẫn lưu tệp vào thư mục tạm trên máy chủ
            //String dirPath = "C:\\Users\\Phu Nguyen\\Desktop\\temp-upload";  // Cập nhật đường dẫn thư mục // Cần đổi thành file lưu trữ tạm thời tren máy của mình
            String dirPath = System.getProperty("java.io.tmpdir");
            System.out.println(dirPath);
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();  // Tạo thư mục nếu chưa có
            }

            // Đường dẫn đầy đủ để lưu tệp
            String filePath = dirPath + "\\" + file.getOriginalFilename();

            // Lưu tệp vào thư mục
            file.transferTo(new java.io.File(filePath));

            // Gọi dịch vụ tải lên Google Drive
            return resService.uploadImage(filePath, folderId);
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }

    @GetMapping("/list-folders")
    public ResponseEntity<List<String>> listFolders() {
        try {
            List<String> folderIds = resService.listFolders(); // Tạo phương thức listFolders trong ResService
            return new ResponseEntity<>(folderIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list-folders/{folderId}")
    public List<String> listFiles(@PathVariable String folderId) {
        try {
            // Gọi phương thức listFiles từ ResService
            return resService.listFiles(folderId);
        } catch (IOException e) {
            // Nếu có lỗi, trả về thông báo lỗi
            throw new RuntimeException("Có lỗi khi lấy danh sách tệp", e);
        }
    }

    //Xóa folder cha
    @DeleteMapping("/delete/{folderId}")
    public void deleteFolder(@PathVariable String folderId) throws IOException {
        resService.deleteFile(folderId);
    }

    //Xóa 1 thư mục con
        @DeleteMapping("/delete/subFolder/{subFolderId}")
    public void deleteSubFolder(@PathVariable String subFolderId) throws IOException {
        resService.deleteSubFolder(subFolderId);
    }

}
