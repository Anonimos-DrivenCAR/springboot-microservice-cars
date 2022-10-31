package com.anonimos.springboot.app.cars.repository;
import com.anonimos.springboot.app.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByBrand(String Brand);
    List<Car> findCarsByModel(String model);
    List<Car> findCarsByProductionYear(int productionYear);
}
