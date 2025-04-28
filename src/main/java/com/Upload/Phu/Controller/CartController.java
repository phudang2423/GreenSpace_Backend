package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.CartItem;
import com.Upload.Phu.Entity.Product;
import com.Upload.Phu.Repository.CartItemRepository;
import com.Upload.Phu.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*") // Cho phép gọi API từ React
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productJpaRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody CartItem cartItem) {
        // Kiểm tra tính hợp lệ của cartItem
        if (cartItem.getProductId() == null || cartItem.getUsername() == null) {
            return ResponseEntity.badRequest().body("Product ID or Username is missing.");
        }

        // Kiểm tra sản phẩm có tồn tại không
        Product product = productJpaRepository.findById(cartItem.getProductId())
                .orElse(null);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + cartItem.getProductId() + " not found.");
        }

        // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItem> existingCartItem = cartItemRepository.findByUsernameAndProductId(
                cartItem.getUsername(), cartItem.getProductId()
        );

        CartItem savedItem;

        if (existingCartItem.isPresent()) {
            // Nếu đã có, cập nhật số lượng
            CartItem item = existingCartItem.get();
            int newQuantity = item.getQuantity() + (cartItem.getQuantity() > 0 ? cartItem.getQuantity() : 1);
            item.setQuantity(newQuantity);
            savedItem = cartItemRepository.save(item);
        } else {
            // Nếu chưa có, thêm sản phẩm mới
            CartItem newCartItem = new CartItem();
            newCartItem.setUsername(cartItem.getUsername());
            newCartItem.setProductId(product.getProductId());
            newCartItem.setName(product.getName());
            newCartItem.setPrice(product.getPrice());
            newCartItem.setQuantity(cartItem.getQuantity() > 0 ? cartItem.getQuantity() : 1);
            newCartItem.setImageUrl(product.getFirstImageUrl());
            newCartItem.setSlug(product.getSlug());

            savedItem = cartItemRepository.save(newCartItem);
        }

        // Trả về CartItem sau khi đã thêm hoặc cập nhật
        return ResponseEntity.ok(savedItem);
    }




    @GetMapping("/{username}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String username) {
        List<CartItem> cartItems = cartItemRepository.findByUsername(username);
        return ResponseEntity.ok(cartItems); // ✅ Trả về danh sách giỏ hàng đúng
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItem cartItem) {
        Optional<CartItem> existingItem = cartItemRepository.findById(cartItem.getId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(cartItem.getQuantity());
            cartItemRepository.save(item);
            return ResponseEntity.ok("Cập nhật số lượng thành công!");
        }

        return ResponseEntity.badRequest().body("Sản phẩm không tồn tại trong giỏ hàng!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable Long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return ResponseEntity.ok("Xóa sản phẩm thành công!");
        }
        return ResponseEntity.badRequest().body("Sản phẩm không tồn tại trong giỏ hàng!");
    }

    @DeleteMapping("/delete-multiple")
    public ResponseEntity<?> deleteMultipleCartItems(@RequestParam String username, @RequestBody List<Long> productIds) {
        if (productIds.isEmpty()) {
            return ResponseEntity.badRequest().body("Danh sách sản phẩm cần xóa trống!");
        }

        // Xóa các sản phẩm theo productId và username
        cartItemRepository.deleteByUsernameAndProductIdIn(username, productIds);

        return ResponseEntity.ok("Đã xóa sản phẩm thành công!");
    }


}
