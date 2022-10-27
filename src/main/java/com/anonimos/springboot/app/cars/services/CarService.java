package com.anonimos.springboot.app.cars.services;

import com.anonimos.springboot.app.cars.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {
    public List<Car> findAll();
    public Optional<List<Car>> findCarByBran(String manufacturer);
    public List<Car> findCarByModel(String model);
    public Optional <Car> findById(Long id);
    public List<Car> findCarsByProductionYear(int productionYear);
    public Car saveCar(Car car);
    public void deleteCar(Long id);
    public Car updateCar(Car newCar,Long id);

}
