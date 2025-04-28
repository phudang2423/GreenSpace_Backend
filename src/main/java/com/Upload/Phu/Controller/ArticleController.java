package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.Article;
import com.Upload.Phu.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")

public class ArticleController {
    @Autowired
    private ArticleRepository repo;
    private static final String IMGBB_API_KEY = "d69fcf0e6ca45647d8065a1ebe347da0"; // Thay bằng API key thực của bạn
    private static final String IMGBB_UPLOAD_URL = "https://api.imgbb.com/1/upload";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Chuyển đổi MultipartFile thành base64
            String base64Image = java.util.Base64.getEncoder().encodeToString(file.getBytes());

            // Tạo request body
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("image", base64Image);

            // Tạo headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            // Thêm API key như một tham số
            body.add("key", IMGBB_API_KEY);

            // Gửi request tới ImgBB API
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(
                    IMGBB_UPLOAD_URL,
                    requestEntity,
                    String.class
            );

            // Xử lý response từ ImgBB
            if (response.getStatusCode() == HttpStatus.OK) {
                // Parse JSON response để lấy URL ảnh
                // Giả sử response có dạng: {"data":{"url":"https://i.ibb.co/..."}}
                String imgUrl = parseImageUrlFromResponse(response.getBody());
                return ResponseEntity.ok(imgUrl);
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("Lỗi khi upload ảnh lên ImgBB: " + response.getBody());
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Lỗi khi xử lý ảnh: " + e.getMessage());
        }
    }


    @GetMapping
    public List<Article> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        article.setCreatedAt(LocalDateTime.now());
        return repo.save(article);
    }

        private String parseImageUrlFromResponse(String jsonResponse) {
            // Đơn giản parse JSON, trong thực tế nên dùng thư viện JSON như Jackson
            int urlIndex = jsonResponse.indexOf("\"url\":\"") + 7;
            int urlEndIndex = jsonResponse.indexOf("\"", urlIndex);
            return jsonResponse.substring(urlIndex, urlEndIndex);
        }


    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article updated) {
        return repo.findById(id).map(article -> {
            article.setTitle(updated.getTitle());
            article.setThumbnailUrl(updated.getThumbnailUrl());
            article.setContent(updated.getContent());
            return ResponseEntity.ok(repo.save(article));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}


