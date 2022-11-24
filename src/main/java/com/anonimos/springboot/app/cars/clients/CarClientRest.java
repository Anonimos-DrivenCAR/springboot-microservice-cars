package com.anonimos.springboot.app.cars.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-lessors", url = "ec2-54-152-10-194.compute-1.amazonaws.com:8003/api/lessor")
public interface CarClientRest {
    @DeleteMapping("/delete_car/{id}")
    void deleteLessorCar(@PathVariable Long id);
}
