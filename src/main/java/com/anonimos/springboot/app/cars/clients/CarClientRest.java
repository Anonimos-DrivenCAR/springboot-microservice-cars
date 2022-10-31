package com.anonimos.springboot.app.cars.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-lessors", url = "localhost:8003")
public interface CarClientRest {
    @DeleteMapping("/delete_car/{id}")
    void deleteLessorCar(@PathVariable Long id);
}
