package com.anonimos.springboot.app.cars.controller;
import com.anonimos.springboot.app.cars.repository.CarRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.anonimos.springboot.app.cars.entity.Car;
import com.anonimos.springboot.app.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/api/car")
//@Tag(name = "Cars", description = "Microservice CARS")
public class CarController {

    @Autowired
    private CarService service;

    @Operation( summary = "List Cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cars Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @GetMapping("/all")
    public Map<String,List<Car>> getAll() {
        return Collections.singletonMap("cars",service.findAll());
    }

    @Operation( summary = "Find a Car by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Car Found", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
        @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long  id){
        Optional<Car> carOptional = service.findById(id);
        if(carOptional.isPresent()){
            return ResponseEntity.ok(carOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation( summary = "Find a Car by its Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @GetMapping("brand/{brand}")
    public Optional<List<Car>> getByBrand(@PathVariable String brand){
        return service.findCarByBran(brand);
    }


    @Operation( summary = "Find a Car by its Model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @GetMapping("/model/{model}")
    public List<Car> getByModel(@PathVariable String model){
        return service.findCarByModel(model);
    }



   @Operation( summary = "Find a Car by its Year of Production")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @GetMapping("/production-year/{productionYear}")
    public List<Car> getByProductionYear(@PathVariable int  productionYear){
        return service.findCarsByProductionYear(productionYear);
    }


    @Operation( summary = "Delete a Car by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not Found", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){

        Optional<Car> C = service.findById(id);
        if(C.isPresent()){
            service.deleteCar(id);
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @Operation( summary = "Add a new Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car added", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Car car,BindingResult result){
        if(result.hasErrors()){
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCar(car));
    }

    @Operation( summary = "Update a Car by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car Updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car not found", content = @Content)
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Car newCar, BindingResult result){
        if(result.hasErrors()){
            return validate(result);
        }
        return new ResponseEntity<>(service.updateCar(newCar, id), HttpStatus.OK) ;
    }


    /**Microservices Iteration*/
    @Operation( summary = "List Cars by IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cars Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Cars not Found", content = @Content)
    })
    @GetMapping("/cars-by-lessor")
    public ResponseEntity<?> getCarsByLessor(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listCarsByIds(ids));
    }






    /**Validation*/
    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
