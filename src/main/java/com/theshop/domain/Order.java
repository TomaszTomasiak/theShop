package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "date_of_order")
    private LocalDate ordered;

    @Column(name = "comments")
    private String comments;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "value")
    private BigDecimal totalValue;

    private boolean isCompleted;

//    @Builder.Default
//    @OneToMany(
//            targetEntity = Item.class,
//            mappedBy = "order",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY)
//    private List<Item> items = new ArrayList<>();
}