package com.anonimos.springboot.app.cars.entity;
import com.anonimos.springboot.app.cars.enums.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car")
    private Long idCar;

    @Column(name="brand")
    @NotBlank
    private String brand;

    @Column(name="model")
    @NotBlank
    private String model;

    @Column(name="production_year")
    @NotNull
    private int productionYear;

    @Enumerated(EnumType.STRING)
    @NotBlank
    private CarType carType;

    @Column(name="color")
    @NotBlank
    private String color;

    @Column(name="engine_Size")
    @NotNull
    private int engineSize;

    @Column(name="power")
    @NotBlank
    private int power;

    @Column(name="mileage")
    @NotNull
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