package com.anonimos.springboot.app.cars.services.impl;
import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.repository.CarRepository;
import com.anonimos.springboot.app.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Service
public class carServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;



    @Override
    @Transactional(readOnly = true)
    public List<Car> findAllCars() {
      return (List<Car>) carRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Car>> findCarByBran(String bran) {
        return Optional.ofNullable(carRepository.findCarsByBrand(bran));
    }
    @Override
    public List<Car> findCarByModel(String model) {
       return carRepository.findCarsByModel(model);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<Car> findCar(Long id) {
       return Optional.ofNullable(carRepository.findById(id).orElse(null));
    }
    @Override
    @Transactional(readOnly = true)
    public List<Car> findCarsByProductionYear(int productionYear) {
        return carRepository.findCarsByProductionYear(productionYear);
    }
    @Override
    @Transactional
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
    @Override
    @Transactional
    public void deleteCar(Long idCar) {
      carRepository.deleteById(idCar);

    }
    @Override
    @Transactional
    public Car updateCar(Car newCar, Long id) {
      return carRepository.findById(id).map(
             car -> {
                car.setBrand(newCar.getBrand());
                car.setIdCategory(newCar.getIdCategory());
                car.setModel(newCar.getModel());
                car.setProductionYear(newCar.getProductionYear());
                car.setColor(newCar.getColor());
                car.setEngineSize(newCar.getEngineSize());
                car.setPower(newCar.getPower());
                car.setCarType(newCar.getCarType());
                car.setMileage(newCar.getMileage());

                return carRepository.save(car);
             }
      ).get();
    }
}
