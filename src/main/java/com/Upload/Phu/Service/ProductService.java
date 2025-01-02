package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.Product;
import com.Upload.Phu.Repository.ProductRepository;
import com.Upload.Phu.RequestDTO.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(ProductRequestDTO requestDTO) {
        // Kiểm tra nếu name là null hoặc trống, có thể xử lý lỗi hoặc trả về thông báo
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setCategory(requestDTO.getCategory());
        product.setImageUrl(requestDTO.getImageUrl());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());
        product.setSlug(requestDTO.getSlug());
        product.setTags(requestDTO.getTags());
        product.setOriginalPrice(requestDTO.getOriginalPrice()); // Set originalPrice

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Xóa sản phẩm khỏi cơ sở dữ liệu
        productRepository.delete(existingProduct);
    }

    // Cập nhật thông tin sản phẩm
    public Product updateProduct(Long id, ProductRequestDTO requestDTO) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Cập nhật thông tin sản phẩm từ requestDTO
        existingProduct.setName(requestDTO.getName());
        existingProduct.setDescription(requestDTO.getDescription());
        existingProduct.setCategory(requestDTO.getCategory());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setStockQuantity(requestDTO.getStockQuantity());
        existingProduct.setImageUrl(requestDTO.getImageUrl());
        existingProduct.setTags(requestDTO.getTags());
        existingProduct.setSlug(requestDTO.getSlug());
        existingProduct.setOriginalPrice(requestDTO.getOriginalPrice()); // Set originalPrice

        // Lưu lại sản phẩm đã cập nhật
        return productRepository.save(existingProduct);
    }

    //Tìm sản phẩm qua slug
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Product not found with slug: " + slug));
    }

    // Tìm kiếm sản phẩm nâng cao
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    // Phương thức tìm kiếm các gợi ý sản phẩm
    public List<String> getProductSuggestions(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query); // Tìm kiếm sản phẩm theo tên
        return products.stream()
                .map(Product::getName) // Chỉ lấy tên sản phẩm
                .collect(Collectors.toList()); // Trả về danh sách tên sản phẩm
    }
}
