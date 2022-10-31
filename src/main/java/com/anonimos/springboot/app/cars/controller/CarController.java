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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@Tag(name = "Cars", description = "service Web RESTFull de cars")
public class CarController {

    @Autowired
    private CarService service;

    /*@Operation( summary = "Listar Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @GetMapping("/")
    public List<Car> getAll() {
        return service.findAll();
    }

    /*@Operation( summary = "Buscar Car por Id")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
        @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
})*/
    @GetMapping("/id/{idCar}")
    public ResponseEntity<?> getById(@PathVariable Long  idCar){
        Optional<Car> carOptional = service.findById(idCar);
        if(carOptional.isPresent()){
            return ResponseEntity.ok(carOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /*@Operation( summary = "Buscar Car por Brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content) })*/
    @GetMapping("/{brand}")
    public Optional<List<Car>> getByBrand(@PathVariable String brand){
        return service.findCarByBran(brand);
    }


    /*@Operation( summary = "Buscar Car por Model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @GetMapping("/model/{model}")
    public List<Car> getByModel(@PathVariable String model){
        return service.findCarByModel(model);
    }



   /*@Operation( summary = "Buscar Car por ProductionYear")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car encontrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @GetMapping("/production-year/{productionYear}")
    public List<Car> getByProductionYear(@PathVariable int  productionYear){
        return service.findCarsByProductionYear(productionYear);
    }


    @GetMapping("/cars-by-lessor")
    public ResponseEntity<?> getCarsByLessor(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listCarsByIds(ids));
    }


    /**---------------------------------------------------------------------------------------*/

    /*@Operation( summary = "Eliminación de un Car por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car eliminado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @DeleteMapping("/delete/{idCar}")
    public ResponseEntity<?> deleteCar(@PathVariable Long idCar){

        Optional<Car> C = service.findById(idCar);
        if(C.isPresent()){
            service.deleteCar(idCar);
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }

    /*@Operation( summary = "Registro de un Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car registrado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Car car,BindingResult result){
        if(result.hasErrors()){
            return validate(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCar(car));
    }

    /*@Operation( summary = "Actualizacion de datos de un Car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car actualizado", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class)) }),
            @ApiResponse(responseCode = "404", description = "Car no encontrado", content = @Content)
    })*/
    @PutMapping("/update/{idCar}")
    public ResponseEntity<?> update(@PathVariable Long idCar,@RequestBody Car newCar, BindingResult result){
        if(result.hasErrors()){
            return validate(result);
        }
        return new ResponseEntity<>(service.updateCar(newCar, idCar), HttpStatus.OK) ;
    }

    private static ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err ->{
            errors.put(err.getField(), "Field " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
