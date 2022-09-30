package com.anonimos.springboot.app.cars.services.impl;
import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.repository.CarRepository;
import com.anonimos.springboot.app.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class carServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Override
    public List<Car> findAllCars() {
      return (List<Car>) carRepository.findAll();
    }
    @Override
    public Optional<List<Car>> findCarByBran(String bran) {
        return Optional.ofNullable(carRepository.findCarsByBrand(bran));
    }
    @Override
    public List<Car> findCarByModel(String model) {
       return carRepository.findCarsByModel(model);
    }
    @Override
    public Optional<Car> findCar(Long id) {
       return Optional.ofNullable(carRepository.findById(id).orElse(null));
    }
    @Override
    public List<Car> findCarsByProductionYear(int productionYear) {
        return carRepository.findCarsByProductionYear(productionYear);
    }
    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
    @Override
    public void deleteCar(Long idCar) {
      carRepository.delete(new Car(idCar));
      carRepository.delete(new Car(idCar));
    }
    @Override
    public Car updateCar(Car newCar, Long id) {
      return carRepository.findById(id).map(
             car -> {
                car.setBrand(newCar.getBrand());
                car.setModel(newCar.getModel());
                car.setProductionYear(newCar.getProductionYear());
                car.setColor(newCar.getColor());
                car.setEngineSize(newCar.getEngineSize());
                car.setPower(newCar.getPower());
                car.setCarType(newCar.getCarType());
                car.setMileage(newCar.getMileage());
                car.setIdCategory(newCar.getIdCategory());
                return carRepository.save(car);
             }
      ).get();
    }
}
