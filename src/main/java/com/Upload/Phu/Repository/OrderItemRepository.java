package com.Upload.Phu.Repository;

import com.Upload.Phu.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    List<OrderItem> findByUsername(String username);
    //List<OrderItem> findByOrderID(long id);
    List<OrderItem> findByOrderId(long orderId);

}

