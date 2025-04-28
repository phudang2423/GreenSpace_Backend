package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.Product;
import com.Upload.Phu.RequestDTO.ProductRequestDTO;
import com.Upload.Phu.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173") // Cho phép frontend truy cập
public class ProductController {

    @Autowired
    private ProductService productService;

    // Lấy tất cả sản phẩm
    @GetMapping("")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    // Lấy sản phẩm bằng slug
    @GetMapping("/{slug}")
    public Product getProduct(@PathVariable String slug) {
        return productService.getProductBySlug(slug);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get()); // ✅ Trả về sản phẩm nếu có
        } else {
            return ResponseEntity.notFound().build(); // ❌ 404 nếu không tìm thấy
        }
    }


    // Thêm sản phẩm mới -> BƯỚC 1: Tạo sản phẩm trước
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody @Validated ProductRequestDTO requestDTO) {
        try {
            Product product = productService.createProduct(requestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // BƯỚC 2: Cập nhật danh sách ảnh sau khi tải lên
    @PutMapping("/{id}/upload-images")
    public ResponseEntity<?> updateProductImages(@PathVariable("id") Long id, @RequestBody List<String> imageUrls) {
        try {
            Product updatedProduct = productService.updateProductImages(id, imageUrls);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        }
    }

    // Cập nhật thông tin sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody @Validated ProductRequestDTO requestDTO) {
        try {
            Product updatedProduct = productService.updateProduct(id, requestDTO);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Xóa sản phẩm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // API Tìm kiếm
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> products = productService.searchProducts(query);
        return ResponseEntity.ok(products);
    }

    // Endpoint tìm kiếm gợi ý
    @GetMapping("/autocomplete")
    public List<String> autocomplete(@RequestParam String query) {
        return productService.getProductSuggestions(query);
    }
}
