package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="cart_Id")
    private Cart cart;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Product product;

    @Column(name="quantity")
    private int quantity;
}
