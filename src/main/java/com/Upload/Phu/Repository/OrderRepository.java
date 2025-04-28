package com.Upload.Phu.Repository;

import com.Upload.Phu.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
        List<Order> findByUsername(String username);
}

