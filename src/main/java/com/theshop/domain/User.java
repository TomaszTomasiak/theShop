package com.theshop.domain;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="mail_adress")
    private String mailAdress;

    @Column(name="phone")
    private String phoneNumber;

    @Column(name="password")
    private String password;

    @Builder.Default
    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch=FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
