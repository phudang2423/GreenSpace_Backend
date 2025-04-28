package com.Upload.Phu.RequestDTO;

import com.Upload.Phu.Entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

import java.util.List;

public class CartItemRequestDTO {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Mối quan hệ với User

    @Column(nullable = false)
    private Long productId; // Mã sản phẩm

    private String username; // Tên sản phẩm

    @ElementCollection
    private List<String> imageUrl; // URL hình ảnh sản phẩm

    @Min(1)
    private int quantity; // Số lượng sản phẩm

    private Float price; // Giá sản phẩm

    private String slug;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Min(1)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(1) int quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
