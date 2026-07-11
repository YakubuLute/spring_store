package com.creatorstore.creatorstore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false, name = "customer_email")
    private String customerEmail;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name = "total_price")
   private BigDecimal totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem>  orderItem;

    @Column(nullable = false, name = "created_at")
   private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }



    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

}
