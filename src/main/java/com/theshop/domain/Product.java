package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", unique = true)
    private Long productId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    @Size(max = 1000)
    @Length(max = 1000)
    private String description;

    @Column(name="price")
    private double price;

    @ManyToOne
    @JoinColumn(name="group_id")
    private ProductGroup group;

    @Builder.Default
    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "product",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    private boolean available;

}
