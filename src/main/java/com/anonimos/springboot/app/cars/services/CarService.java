package com.anonimos.springboot.app.cars.services;

import com.anonimos.springboot.app.cars.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Optional<List<Car>> findCarByBran(String manufacturer);
    List<Car> findCarByModel(String model);
    Optional <Car> findById(Long id);
    List<Car> findCarsByProductionYear(int productionYear);
    Car saveCar(Car car);
    void deleteCar(Long id);
    Car updateCar(Car newCar,Long id);
    List<Car> listCarsByIds(Iterable<Long> ids);

}
