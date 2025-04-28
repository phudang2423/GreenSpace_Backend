package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.Product;
import com.Upload.Phu.Repository.ProductRepository;
import com.Upload.Phu.RequestDTO.ProductRequestDTO;
import com.Upload.Phu.Util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // BƯỚC 1: Tạo sản phẩm trước
    public Product createProduct(ProductRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Tên sản phẩm không được để trống!");
        }

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setDescription(requestDTO.getDescription());
        product.setCategory(requestDTO.getCategory());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());
        product.setTags(requestDTO.getTags());
        product.setOriginalPrice(requestDTO.getOriginalPrice());
        product.setSlug(SlugUtil.generateSlug(requestDTO.getName()));

        // Kiểm tra nếu có danh sách ảnh thì thêm vào
        product.setImageUrl(requestDTO.getImageUrl() != null ? requestDTO.getImageUrl() : new ArrayList<>());

        return productRepository.save(product);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Xóa sản phẩm
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));
        productRepository.delete(existingProduct);
    }

    // Cập nhật thông tin sản phẩm
    public Product updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        existingProduct.setName(requestDTO.getName());
        existingProduct.setDescription(requestDTO.getDescription());
        existingProduct.setCategory(requestDTO.getCategory());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setStockQuantity(requestDTO.getStockQuantity());
        existingProduct.setTags(requestDTO.getTags());
        existingProduct.setSlug(requestDTO.getSlug());
        existingProduct.setOriginalPrice(requestDTO.getOriginalPrice());

        // Cập nhật danh sách ảnh nếu có dữ liệu mới
        if (requestDTO.getImageUrl() != null) {
            existingProduct.setImageUrl(requestDTO.getImageUrl());
        }

        return productRepository.save(existingProduct);
    }


    // Tìm sản phẩm qua slug
    public Product getProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với slug: " + slug));
    }

    // Tìm kiếm sản phẩm nâng cao
    public List<Product> searchProducts(String query) {
        return productRepository.searchProducts(query);
    }

    // Tìm kiếm gợi ý sản phẩm
    public List<String> getProductSuggestions(String query) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        return products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    // BƯỚC 2: Cập nhật danh sách ảnh sau khi tải lên Google Drive
    public Product updateProductImages(Long productId, List<String> imageIds) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm"));

        // Gán danh sách ảnh mới thay vì cộng dồn
        product.setImageUrl(imageIds != null ? new ArrayList<>(imageIds) : new ArrayList<>());

        return productRepository.save(product);
    }


    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }


}
