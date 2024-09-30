package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity
public class Snacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Enumerated(EnumType.STRING)
    private TypeOfSnack typeOfSnack;

    @Column(nullable = false)
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeOfSnack getTypeOfSnack() {
        return typeOfSnack;
    }

    public void setTypeOfSnack(TypeOfSnack typeOfSnack) {
        this.typeOfSnack = typeOfSnack;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
