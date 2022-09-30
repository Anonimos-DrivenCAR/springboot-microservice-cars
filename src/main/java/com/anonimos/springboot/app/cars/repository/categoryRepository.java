package com.anonimos.springboot.app.cars.repository;
import com.anonimos.springboot.app.cars.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category, Long> {

}
