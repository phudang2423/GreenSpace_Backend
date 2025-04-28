//package com.Upload.Phu.Service;
//
//
//import com.google.api.client.googleapis.json.GoogleJsonResponseException;
//import com.google.api.client.http.ByteArrayContent;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.File;
//import com.google.api.services.drive.model.FileList;
//import org.springframework.stereotype.Service;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class GoogleDriveService {
//    private  Drive driveService;
//    private final String parentFolderId = "YOUR_PARENT_FOLDER_ID"; // Thay bằng ID thư mục cha thực tế
//
//
//    public String createFolder(String folderName) throws IOException {
//       try{
//           File folder = new File();
//           folder.setName(folderName);
//           folder.setMimeType("application/vnd.google-apps.folder");
//           folder.setParents(Collections.singletonList(parentFolderId));
//
//           File createdFolder = driveService.files().create(folder)
//                   .setFields("id")
//                   .execute();
//           return createdFolder.getId();
//       }
//       catch (GoogleJsonResponseException e){
//           return e.getDetails().getMessage();
//       }
//    }
//
//    public void deleteFolder(String folderName) throws IOException {
//        try{
//            String folderId = getFolderIdByName(folderName);
//            if (folderId != null) {
//                driveService.files().delete(folderId).execute();
//            }
//        }
//        catch (GoogleJsonResponseException e){
//            return;
//        }
//    }
//
//    public void renameFolder(String oldName, String newName) throws IOException {
//        String folderId = getFolderIdByName(oldName);
//        if (folderId != null) {
//            File file = new File();
//            file.setName(newName);
//            driveService.files().update(folderId, file).execute();
//        }
//    }
//
//    public String uploadFile(byte[] fileData, String fileName, String folderName) throws IOException {
//        String folderId = getFolderIdByName(folderName);
//        if (folderId == null) {
//            throw new IOException("Folder not found");
//        }
//
//        File fileMetadata = new File();
//        fileMetadata.setName(fileName);
//        fileMetadata.setParents(Collections.singletonList(folderId));
//
//        ByteArrayContent mediaContent = new ByteArrayContent("image/jpeg", fileData);
//        File file = driveService.files().create(fileMetadata, mediaContent)
//                .setFields("id")
//                .execute();
//        return file.getId();
//    }
//
//    public void deleteFile(String fileName, String folderName) throws IOException {
//        String fileId = getFileIdByName(fileName, folderName);
//        if (fileId != null) {
//            driveService.files().delete(fileId).execute();
//        }
//    }
//
//    public void renameFile(String oldName, String newName, String folderName) throws IOException {
//        String fileId = getFileIdByName(oldName, folderName);
//        if (fileId != null) {
//            File file = new File();
//            file.setName(newName);
//            driveService.files().update(fileId, file).execute();
//        }
//    }
//
//    private String getFolderIdByName(String folderName) throws IOException {
//        return getFileIdByName(folderName, parentFolderId);
//    }
//
//    private String getFileIdByName(String fileName, String parentId) throws IOException {
//        String query = String.format("name = '%s' and '%s' in parents and trashed = false", fileName, parentId);
//        FileList result = driveService.files().list().setQ(query).setFields("files(id)").execute();
//        List<File> files = result.getFiles();
//        return files.isEmpty() ? null : files.get(0).getId();
//    }
//}
