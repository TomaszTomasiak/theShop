package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "product_groups")
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER
    )
    List<Product> products = new ArrayList<>();
}

