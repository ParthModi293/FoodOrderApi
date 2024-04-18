package com.PM.Food.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private User customer;

    private Long total;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
    private List<CartItem> item = new ArrayList<>();
}
