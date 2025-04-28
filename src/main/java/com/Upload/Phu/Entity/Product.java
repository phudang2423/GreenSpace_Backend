package com.Upload.Phu.Entity;

import jakarta.persistence.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId; // Mã định danh duy nhất

    @Column(nullable = false)
    private String name; // Tên sản phẩm

    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả sản phẩm

    private String category; // Liên kết đến danh mục sản phẩm (Foreign Key)

    private String folderId; // Lưu thư mục Google Drive của sản phẩm


    @ElementCollection
    private List<String> imageUrl; // URL hình ảnh sản phẩm (dùng List để lưu nhiều ảnh)

    @Column(nullable = false)
    private Float price; // Giá sản phẩm

    private Integer stockQuantity; // Số lượng tồn kho

    private LocalDateTime createdAt; // Thời điểm tạo sản phẩm

    private LocalDateTime updatedAt; // Thời điểm cập nhật gần nhất

    @Column(unique = true, nullable = false) // Đảm bảo cột slug được ánh xạ
    private String slug;

    private String tags;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Lifecycle callbacks
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    // Add the originalPrice field
    private Double originalPrice;

    // Getters and setters for all fields, including originalPrice
    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    // Lấy URL đầu tiên trong danh sách ảnh
    public String getFirstImageUrl() {
        return (imageUrl != null && !imageUrl.isEmpty()) ? imageUrl.get(0) : null;
    }
}
