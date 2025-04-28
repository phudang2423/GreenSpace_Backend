package com.Upload.Phu;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.FileList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class ResService {
    private static final String FOLDER_ID = "1spzFinCq3ijhnLotRhhTsEy6ZB_Ggd3v";  // ID của thư mục Google Drive cần tạo trong đó
    private static final String CREDENTIALS_FILE_PATH = "key.json"; // Đường dẫn đến file credentials.json

    //Thêm ảnh
    public String uploadImage(MultipartFile file, String folderId) throws IOException {
        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList(DriveScopes.DRIVE_FILE));

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        Drive driveService = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();

        // Chuyển đổi MultipartFile thành tệp tạm thời
        java.io.File tempFile = convertMultiPartToFile(file);

        // Khởi tạo metadata của tệp để tải lên Google Drive
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList(folderId));

        // Xác định nội dung file cần tải lên
        FileContent mediaContent = new FileContent(file.getContentType(), tempFile);

        try {
            // Tải file lên Google Drive
            File uploadedFile = driveService.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();

            // Xóa file tạm để tránh tràn bộ nhớ
            tempFile.delete();

            return uploadedFile.getId();
        } catch (GoogleJsonResponseException e) {
            System.err.println("Lỗi khi tải ảnh lên Google Drive: " + e.getDetails());
            throw e;
        }
    }

    private java.io.File convertMultiPartToFile(MultipartFile file) throws IOException {
        java.io.File convFile = new java.io.File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }


    // Phương thức tạo thư mục trên Google Drive
    public String createFolder(String folderName, String folderId) throws IOException {
        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/drive.file"));

        // Khởi tạo Drive API client
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        Drive service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();

        // Metadata của thư mục
        File fileMetadata = new File();

        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        fileMetadata.setParents(Arrays.asList(folderId));  // Thư mục cha, có thể thay đổi để tạo thư mục con ở vị trí khác

        try {
            // Tạo thư mục
            File file = service.files().create(fileMetadata)
                    .setFields("id")
                    .execute();
            System.out.println("Folder ID: " + file.getId());
            return file.getId(); // Trả về ID của thư mục vừa tạo
        } catch (GoogleJsonResponseException e) {
            // Xử lý lỗi nếu không thể tạo thư mục
            System.err.println("Không thể tạo thư mục: " + e.getDetails());
            throw e;
        }
    }

    // Phương thức liệt kê các thư mục trong Google Drive
    public List<String> listFolders() throws IOException {
        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList(DriveScopes.DRIVE));

        // Khởi tạo Drive API client
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        Drive service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();

        // Lấy danh sách các thư mục trong Google Drive
        FileList result = service.files().list()
                .setQ("mimeType='application/vnd.google-apps.folder'")  // Lọc để chỉ lấy thư mục
                .setFields("files(id, name)")  // Chỉ lấy ID và tên của các thư mục
                .execute();

        // Lưu các thư mục vào danh sách và trả về
        List<String> folderIds = new ArrayList<>();
        for (File file : result.getFiles()) {
            folderIds.add(file.getId() + ": " + file.getName());  // Lưu ID và tên của thư mục
        }
        return folderIds;
    }

    // Phương thức liệt kê các tệp trong thư mục Google Drive
    public List<String> listFiles(String folderId) throws IOException {
        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/drive.readonly"));

        // Khởi tạo Drive API client
        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
        Drive service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();

        List<String> allFiles = new ArrayList<>();
        fetchFilesRecursively(service, folderId, allFiles);
        return allFiles;
    }

    private void fetchFilesRecursively(Drive service, String folderId, List<String> allFiles) throws IOException {
        // Lấy danh sách tệp trong thư mục hiện tại
        FileList result = service.files().list()
                .setQ("'" + folderId + "' in parents") // Lọc tệp trong thư mục hiện tại
                .setFields("files(id, name, mimeType)") // Lấy thêm mimeType để kiểm tra xem là thư mục hay không
                .execute();

        for (File file : result.getFiles()) {
            // Thêm tệp hoặc thư mục vào danh sách
            allFiles.add("ID: " + file.getId() + ", Name: " + file.getName());

            // Nếu là thư mục (mimeType = application/vnd.google-apps.folder), duyệt đệ quy
            if ("application/vnd.google-apps.folder".equals(file.getMimeType())) {
                fetchFilesRecursively(service, file.getId(), allFiles);
            }
        }
    }

    //Chỉ có thể xóa ở thư mục cha + toàn bộ bên trong
    public void deleteFile(String fileId) throws IOException {

        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList(DriveScopes.DRIVE));

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);
        Drive service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();


//        // check xem permissions của tài khoản
//        File file = service.files().get(fileId)
//                .setFields("permissions")
//                .execute();
//        System.out.println(file.getPermissions());

        try {
            // Xóa tệp hoặc thư mục
            service.files().delete(fileId).execute();
            System.out.println("Tệp hoặc thư mục với ID: " + fileId + " đã được xóa.");
        } catch (GoogleJsonResponseException e) {
            // Xử lý lỗi nếu không thể xóa
            System.err.println("Không thể xóa tệp hoặc thư mục: " + e.getDetails());
            throw e;
        }
    }


    //    //Xóa 1 thư mục con
    public void deleteSubFolder(String childFolderId) throws IOException {
        // Lấy thông tin chứng thực từ file credentials.json
        GoogleCredentials credentials = GoogleCredentials
                .fromStream(new ClassPathResource(CREDENTIALS_FILE_PATH).getInputStream())
                .createScoped(Arrays.asList(DriveScopes.DRIVE));

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credentials);

        Drive service = new Drive.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance(), requestInitializer)
                .setApplicationName("Drive Service")
                .build();

        try {
            // Xóa thư mục con
            service.files().delete(childFolderId).execute();
            System.out.println("Đã xóa thư mục con với ID: " + childFolderId);
        } catch (GoogleJsonResponseException e) {
            System.err.println("Không thể xóa thư mục con: " + e.getDetails());
            throw e;
        }
    }
}

