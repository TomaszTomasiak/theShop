package com.theshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "cart_id", unique = true)
    private Long id;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "items_on_carts",
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "cart_id"),
            inverseJoinColumns = @JoinColumn(name ="item_id", referencedColumnName = "item_id")
    )
    private List<Item> items = new ArrayList<>();

}
