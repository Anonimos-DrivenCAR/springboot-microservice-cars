package com.anonimos.springboot.app.cars.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer idCategory;
    private String description;

    @Column(name = "is_available")
    private boolean isAvailable;


    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_car")
    private Car car;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Category))
            return false;

        return
                idCategory != null &&
                        idCategory.equals(((Category) o).getIdCategory());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }






    /*@OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    @JoinColumn(name = "id_category")
    private List<Car> cars = new ArrayList<>();*/

    /* This is in case a Bidirectional @OneToMany association
     public void addCar(Car car) {
        cars.add(car);
        car.setCategory(this);
    }

    public void removeCar(Car car) {
        cars.remove(car);
        car.setCategory(null);
    }*/

}


