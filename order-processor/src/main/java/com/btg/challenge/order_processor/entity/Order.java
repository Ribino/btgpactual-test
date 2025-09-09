package com.btg.challenge.order_processor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "\"order\"")
public class Order extends  BaseEntity {
    @Column(name = "code")
    private Long code;

    @Column(name = "client_code")
    private Long clientCode;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    public void calculateTotalAmount() {
        totalAmount = BigDecimal.ZERO;

        for(var item : items) {
            BigDecimal totalPerItem = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(totalPerItem);
        }
    }
}
