package com.yesser.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column
    @NotNull
    String name;

    @Column
    @NotNull
    String description;

    @Column
    @NotNull
    Double price;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    Supplier supplier;

    public Product(Supplier supplier,
            String name,
            String description,
            Double price){
        this.supplier = supplier;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
