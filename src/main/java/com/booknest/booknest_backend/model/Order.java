package com.booknest.booknest_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    private Double amount;

    private Integer quantity = 1;

    private String address;

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum OrderType {
        BUY, RENT, EXCHANGE
    }

    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    // Getters
    public Long getId() { return id; }
    public User getBuyer() { return buyer; }
    public Book getBook() { return book; }
    public OrderType getType() { return type; }
    public OrderStatus getStatus() { return status; }
    public Double getAmount() { return amount; }
    public Integer getQuantity() { return quantity; }
    public String getAddress() { return address; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBuyer(User buyer) { this.buyer = buyer; }
    public void setBook(Book book) { this.book = book; }
    public void setType(OrderType type) { this.type = type; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setAmount(Double amount) { this.amount = amount; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public void setAddress(String address) { this.address = address; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
