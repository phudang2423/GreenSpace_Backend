package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.CartItem;
import com.Upload.Phu.Entity.Product;
import com.Upload.Phu.Repository.CartItemRepository;
import com.Upload.Phu.Repository.ProductRepository;
import com.Upload.Phu.Util.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository; // Đổi tên biến thành chữ thường theo chuẩn

    @Autowired
    private ProductRepository productRepository;

    public CartItem addItemToCart(String username, Long productId, int quantity) {
        // Kiểm tra sản phẩm có tồn tại không
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItem> existingCartItem = cartItemRepository.findByUsernameAndProductId(username, productId);

        if (existingCartItem.isPresent()) {
            // Nếu đã có, cộng dồn số lượng
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartItemRepository.save(cartItem);
        } else {
            // Nếu chưa có, thêm sản phẩm mới vào giỏ hàng
            CartItem newCartItem = new CartItem();
            newCartItem.setUsername(username);
            newCartItem.setProductId(product.getProductId());
            newCartItem.setName(product.getName());
            newCartItem.setPrice(product.getPrice());
            newCartItem.setQuantity(quantity);
            newCartItem.setImageUrl(product.getFirstImageUrl()); // Lấy URL đầu tiên của sản phẩm
            newCartItem.setSlug(SlugUtil.generateSlug(newCartItem.getName()));

            return cartItemRepository.save(newCartItem);
        }
    }

    // Lấy các mặt hàng trong giỏ của người dùng theo username
    public List<CartItem> getCartItemsByUsername(String username) {
        return cartItemRepository.findByUsername(username);
    }

    public void clearCart(String username) {
        cartItemRepository.deleteByUsername(username);
    }


}
