package no.torrissen.motorcycles.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private int year;

    public Motorcycle() {
    }

    public Motorcycle(String model, String brand, int year) {
        this.model = model;
        this.brand = brand;
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                '}';
    }
}
