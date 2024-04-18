package com.PM.Food.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ingredientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private  String name;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<IngredientsItems> ingredients = new ArrayList<>();
}
