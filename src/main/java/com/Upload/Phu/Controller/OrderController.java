package com.Upload.Phu.Controller;

import com.Upload.Phu.Entity.Order;
import com.Upload.Phu.RequestDTO.OrderRequestDTO;
import com.Upload.Phu.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDTO request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{username}")
    public List<Order> getOrdersByUser(@PathVariable String username) {
        return orderService.getOrdersByUsername(username);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllOrders() {
        orderService.deleteAllOrders();
        return ResponseEntity.ok("Đã xóa tất cả đơn hàng.");
    }

    // Lấy tất cả đơn hàng
    @GetMapping("/get")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}