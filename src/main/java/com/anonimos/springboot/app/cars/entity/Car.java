package com.anonimos.springboot.app.cars.entity;
import com.anonimos.springboot.app.cars.enums.CarType;
import javax.persistence.*;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_car")
    private Long idCar;
    @Column(name="brand")
    private String brand;
    @Column(name = "id_category")
    private Integer idCategory;
    @Column(name="model")
    private String model;
    @Column(name="production_year")
    private int productionYear;
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @Column(name="color")
    private String color;
    @Column(name="engine_Size")
    private int engineSize;
    @Column(name="power")
    private int power;
    @Column(name="mileage")
    private int mileage;


    @ManyToOne
    @JoinColumn(name = "id_category", insertable = false, updatable = false)
    private Category category;


    public Car() {
    }

    public Car(Long idCar, String brand, Integer idCategory, String model, int productionYear, CarType carType, String color, int engineSize, int power, int mileage, Category category) {
        this.idCar = idCar;
        this.brand = brand;
        this.idCategory = idCategory;
        this.model = model;
        this.productionYear = productionYear;
        this.carType = carType;
        this.color = color;
        this.engineSize = engineSize;
        this.power = power;
        this.mileage = mileage;
        this.category = category;
    }
    public Car(Long id) {
        this.idCar = id;

    }


    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
