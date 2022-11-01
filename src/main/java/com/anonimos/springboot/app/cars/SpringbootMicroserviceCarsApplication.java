package com.anonimos.springboot.app.cars;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableFeignClients
@SpringBootApplication
public class SpringbootMicroserviceCarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMicroserviceCarsApplication.class, args);
    }

}
