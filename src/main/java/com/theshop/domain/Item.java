package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    @Column(name = "item_id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private BigDecimal value;

//    @ManyToOne
//    @JoinColumn (name = "order_id")
//    private Order order;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "items")
    private List<Cart> carts = new ArrayList<>();
}
