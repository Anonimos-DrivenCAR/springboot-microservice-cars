package com.anonimos.springboot.app.cars.service;

import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.entity.Category;
import com.anonimos.springboot.app.cars.enums.CarType;
import com.anonimos.springboot.app.cars.repository.CarRepository;
import com.anonimos.springboot.app.cars.services.impl.carServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    @Mock
    private CarRepository carRepository;


    @InjectMocks
    private carServiceImpl carService;

    private Car car;
    private Car car2;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        car= new Car();
        //car.setCategory(new Category());
        car.setModel("BMV series 1");
        car.setBrand("BMV");
        car.setColor("Blue");
        car.setEngineSize(42);
        car.setPower(23);
        car.setCarType(CarType.Convertible);
        car.setMileage(123123);

        car2=new Car();
        car2.setBrand("TOYOTA");
        car2.setModel("LAST MODEL");
        car2.setProductionYear(2025);
        car2.setColor("RED");
        car2.setEngineSize(54);
        car2.setPower(55);
        car2.setCarType(CarType.Crossover);
        car2.setMileage(22322);

    }

    @Test
    void findAllCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(car,car2));
        assertNotNull(carService.findAll());
    }

    @Test
    void saveCar() {
        when(carRepository.save(any(Car.class))).thenReturn(car);
        assertNotNull(carService.saveCar(new Car()));
    }

    @Test
    void updateCar(){
        when(carRepository.findById(11L)).thenReturn(Optional.ofNullable(car));

        Optional<Car> new_car= carService.findById(11L);
        new_car.get().setBrand(car2.getBrand());
        new_car.get().setModel(car2.getModel());
        new_car.get().setProductionYear(car2.getProductionYear());
        new_car.get().setColor(car2.getColor());
        new_car.get().setEngineSize(car2.getEngineSize());
        new_car.get().setPower(car2.getPower());
        new_car.get().setCarType(car2.getCarType());
        new_car.get().setMileage(car2.getMileage());

        when(carRepository.save(new_car.get())).thenReturn(new_car.get());
        Car updated=carService.saveCar(new_car.get());

        assertNotNull(carService.updateCar(updated,11L));
    }

    @Test
    void findCarByBran() {
        when(carRepository.findCarsByBrand(car.getBrand())).thenReturn(Collections.singletonList(car));
        assertNotNull(carService.findCarByBran(any()));
    }

    @Test
    void findCarByModel() {
        when(carRepository.findCarsByModel(any())).thenReturn(Collections.singletonList(car));
        assertNotNull(carService.findCarByModel(any()));
    }

    @Test
    void findCarById_success() {
        when(carRepository.findById(car.getIdCar())).thenReturn(Optional.ofNullable(car)
        );
        assertNotNull(carService.findById(car.getIdCar()));
    }

    @Test
    void findCarsByProductionYear() {
        when(carRepository.findCarsByProductionYear(car.getProductionYear())).thenReturn(Collections.singletonList(car));
        assertNotNull(carService.findCarsByProductionYear(anyInt()));
    }


}
