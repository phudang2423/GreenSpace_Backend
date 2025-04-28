package com.Upload.Phu.Service;

import com.Upload.Phu.Entity.Order;
import com.Upload.Phu.Entity.OrderItem;
import com.Upload.Phu.Repository.CartItemRepository;
import com.Upload.Phu.Repository.OrderItemRepository;
import com.Upload.Phu.Repository.OrderRepository;
import com.Upload.Phu.RequestDTO.OrderItemRequestDTO;
import com.Upload.Phu.RequestDTO.OrderRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService
                        ) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }

    /**
     * Tạo đơn hàng mới từ OrderRequestDTO.
     */
    @Transactional
    public Order createOrder(OrderRequestDTO request) {
        // Tạo đối tượng đơn hàng mới
        Order order = new Order();
        order.setUsername(request.getUsername());
        order.setEmail(request.getEmail());
        order.setFullName(request.getFullName());
        order.setPhone(request.getPhone());
        order.setAddress(request.getAddress());
        order.setShippingFee(request.getShippingFee());
        order.setTotalAmount(request.getTotalAmount());

        // Chuyển đổi danh sách sản phẩm sang OrderItem
        List<OrderItem> items = request.getItems().stream()
                .map(item -> mapToOrderItem(order, item))
                .collect(Collectors.toList());

        // Gán danh sách sản phẩm vào đơn hàng
        order.setItems(items);

        // Lưu đơn hàng vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);

        // Xoá giỏ hàng của người dùng sau khi đặt hàng
        cartService.clearCart(request.getUsername());

        return savedOrder;
    }

    /**
     * Chuyển đổi OrderItemRequestDTO thành OrderItem và gắn vào đơn hàng.
     */
    private OrderItem mapToOrderItem(Order order, OrderItemRequestDTO item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProductId(item.getProductId());
        orderItem.setName(item.getName());
        orderItem.setPrice(item.getPrice());
        orderItem.setQuantity(item.getQuantity());
        orderItem.setImageUrl(item.getImageUrl());
        return orderItem;
    }

    /**
     * Trả về tất cả đơn hàng của một người dùng.
     */
    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    /**
     * Xoá toàn bộ đơn hàng (chỉ nên dùng cho mục đích admin hoặc testing).
     */
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    /**
     * Trả về tất cả đơn hàng (dùng cho admin).
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
