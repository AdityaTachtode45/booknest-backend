package com.booknest.booknest_backend.controller;

import com.booknest.booknest_backend.model.Order;
import com.booknest.booknest_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place order
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam Long bookId,
            @RequestParam String type,
            @RequestParam String address,
            @RequestParam(defaultValue = "1") Integer quantity) {
        try {
            Order order = orderService.placeOrder(
                    userDetails.getUsername(), bookId, type, address, quantity);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get my orders as buyer
    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            List<Order> orders = orderService.getMyOrders(
                    userDetails.getUsername());
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get orders for my books as seller
    @GetMapping("/seller-orders")
    public ResponseEntity<?> getSellerOrders(
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            List<Order> orders = orderService.getSellerOrders(
                    userDetails.getUsername());
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get order by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update order status
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String status) {
        try {
            Order order = orderService.updateStatus(
                    id, userDetails.getUsername(), status);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Cancel order
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        try {
            Order order = orderService.cancelOrder(
                    id, userDetails.getUsername());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
