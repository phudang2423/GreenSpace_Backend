package com.Upload.Phu.Repository;


import com.Upload.Phu.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // Lấy tất cả các sản phẩm trong giỏ hàng của người dùng theo username
    List<CartItem> findByUsername(String username);

    Optional<CartItem> findByUsernameAndProductId(String username, Long productId);

    void deleteByUsernameAndProductIdIn(String username, List<Long> productIds);

    void deleteByUsername(String username);
}



