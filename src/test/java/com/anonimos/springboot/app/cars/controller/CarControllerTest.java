package com.anonimos.springboot.app.cars.controller;

import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.entity.Category;
import com.anonimos.springboot.app.cars.enums.CarType;
import com.anonimos.springboot.app.cars.services.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

 class CarControllerTest {

        @Mock
        private CarService carService;
        @InjectMocks
        private CarController carController;


        private Car car;
        private Car car2;
        @BeforeEach
        void setup(){
            MockitoAnnotations.openMocks(this);

            car= new Car(1L);
            car.setBrand("Chevrolet 12");
            car.setCarType(CarType.Coupe);
            car.setColor("Red");
            car.setModel("2023");
            car.setMileage(123);
            car.setEngineSize(123);
            car.setProductionYear(2020);
            car.setCategory(new Category());
            car.setPower(123);

            car2= new Car(2L);
            car2.setBrand("Chevrolet 15");
            car2.setCarType(CarType.Minivan);
            car2.setColor("Blue");
            car2.setModel("2028");
            car2.setMileage(1243);
            car2.setEngineSize(1253);
            car2.setProductionYear(2050);
            car2.setCategory(new Category());
            car2.setPower(1253);

        }

        @Test
        void findAllCars() {
            when(carService.findAllCars()).thenReturn(Collections.singletonList(car));
            assertNotNull(carController.getAll());
        }


        @Test
        void findCarsByBrand() {
            when(carService.findCarByBran(car.getBrand())).thenReturn(Optional.of(Collections.singletonList(car)));
            assertNotNull(carController.getByBrand(anyString()));
        }

        @Test
        void findCarsByModel() {
            when(carService.findCarByModel(car.getModel())).thenReturn(Collections.singletonList(car));
            assertNotNull(carController.getByModel(anyString()));
        }

        @Test
        void getByProductionYear() {
            when(carService.findCarsByProductionYear(car.getProductionYear())).thenReturn(Collections.singletonList(car));
            assertNotNull(carController.getById(anyInt()));
        }

        @Test
        void GetById() {
            when(carService.findCar(1L)).thenReturn(Optional.ofNullable(car));
            assertNotNull(carController.getById(1L));

        }

        @Test
        void newCar() {
            when(carService.saveCar(any(Car.class))).thenReturn(car);
            assertNotNull(carController.newUser(any()));
        }


        @Test
        void replaceCar() {
            when(carService.updateCar(car,1L)).thenReturn(car);
            assertNotNull(carController.updateCar(car2,1L));
        }

        @Test
        void deleteCar() {
            when(carService.findCar(1L)).thenReturn(Optional.ofNullable(car));
            Optional<Car> found_car=carController.getById(1L);
            carService.deleteCar(1L);
            Optional<Car> deleted=null;
            carController.deleteCar(1L);
            found_car=deleted;
            when(carService.findCar(1L)).thenReturn(found_car);

            assertNull(carController.getById(1L));
        }
}

