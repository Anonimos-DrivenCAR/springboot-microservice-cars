package com.anonimos.springboot.app.cars.entity;
import com.anonimos.springboot.app.cars.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car")
    private Long idCar;
    @Column(name="brand")
    private String brand;

    @Column(name="model")
    private String model;
    @Column(name="production_year")
    private int productionYear;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Column(name="color")
    private String color;

    @Column(name="engine_Size")
    private int engineSize;

    @Column(name="power")
    private int power;

    @Column(name="mileage")
    private int mileage;

    @OneToMany(
            mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Category> categories = new ArrayList<>();



    public void addCategory(Category category) {
        categories.add(category);
        category.setCar(this);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
        category.setCar(null);
    }

    /* if the association is Bidirectional
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
    */



}