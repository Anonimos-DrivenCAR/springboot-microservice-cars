package com.anonimos.springboot.app.cars.controller;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@Tag(name = "Cars", description = "service Web RESTFull de cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Operation( summary = "Listar Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @GetMapping("/")
    public List<Car> getAll() {
        return carService.findAllCars();
    }


    @Operation( summary = "Buscar Car por Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @GetMapping("/brand/{brand}")
    public Optional<List<Car>> getByBrand(@PathVariable String brand){
        return carService.findCarByBran(brand);
    }


    @Operation( summary = "Buscar Car por Model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @GetMapping("/model/{model}")
    public List<Car> getByModel(@PathVariable String model){
        return carService.findCarByModel(model);
    }



    @Operation( summary = "Buscar Car por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @GetMapping("/{idCar}")
    public Optional<Car> getById(@PathVariable long  idCar){
        return carService.findCar(idCar);
    }


    @Operation( summary = "Buscar Car por ProductionYear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @GetMapping("productionYear/{productionYear}")
    public List<Car> getByProductionYear(@PathVariable int  productionYear){
        return carService.findCarsByProductionYear(productionYear);
    }

    @Operation( summary = "Registro de un Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car registrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })

    @PostMapping("/save/new-car")
    public ResponseEntity<Car> newUser(@RequestBody Car car){
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.CREATED);
    }


    @Operation( summary = "Eliminación de un Car por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car eliminado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @DeleteMapping("/delete/{idCar}")
    public ResponseEntity<Car> deleteCar(@PathVariable Long idCar){
        carService.deleteCar(idCar);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation( summary = "Actualizacion de datos de un Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car actualizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })
    @PutMapping("update/{idCar}")
    public ResponseEntity<Car> updateCar(@RequestBody Car newcar, @PathVariable long idCar){
        return new ResponseEntity<>(carService.updateCar(newcar, idCar), HttpStatus.OK) ;
    }
}
