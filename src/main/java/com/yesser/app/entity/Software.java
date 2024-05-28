package com.yesser.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
public class Software extends Product {
    @Column
    @NotNull
    String programmingLanguaje;

    public Software(){

    }

    public Software(Supplier supplier, String name, String description, Double price, String programmingLanguaje) {
        super(supplier, name, description, price);
        this.programmingLanguaje = programmingLanguaje;
    }

    @Override
    public String toString() {
        return "Software{" +
                "programmingLanguaje='" + programmingLanguaje + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", supplier=" + supplier +
                '}';
    }
}
