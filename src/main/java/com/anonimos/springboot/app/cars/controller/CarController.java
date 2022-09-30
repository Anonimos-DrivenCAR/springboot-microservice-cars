package com.anonimos.springboot.app.cars.controller;

import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public List<Car> getAll() {
        return carService.findAllCars();
    }

    @GetMapping("/brand/{brand}")
    public Optional<List<Car>> getByBrand(@PathVariable String brand){
        return carService.findCarByBran(brand);
    }

    @GetMapping("/model/{model}")
    public List<Car> getByModel(@PathVariable String model){
        return carService.findCarByModel(model);
    }

    @GetMapping("/{idCar}")
    public Optional<Car> getById(@PathVariable long  idCar){
        return carService.findCar(idCar);
    }

    @GetMapping("productionYear/{productionYear}")
    public List<Car> getById(@PathVariable int  productionYear){
        return carService.findCarsByProductionYear(productionYear);
    }

    @PostMapping("/save/new-car")
    public ResponseEntity<Car> newUser(@RequestBody Car car){
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idCar}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long idCar){
        carService.deleteCar(idCar);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("update/{idCar}")
    public ResponseEntity<Car> updateCar(@RequestBody Car newcar, @PathVariable long idCar){
        return new ResponseEntity<>(carService.updateCar(newcar, idCar), HttpStatus.OK) ;
    }
}
