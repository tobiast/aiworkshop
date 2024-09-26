package no.torrissen.motorcycles.model;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String firstName;
    private String lastName;
    private String email;
    private List<Motorcycle> motorcycles;

    public Owner() {
        this.motorcycles = new ArrayList<>();
    }

    public Owner(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.motorcycles = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }

    public void addMotorcycle(Motorcycle motorcycle) {
        this.motorcycles.add(motorcycle);
    }

    public void removeMotorcycle(Motorcycle motorcycle) {
        this.motorcycles.remove(motorcycle);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", motorcycles=" + motorcycles +
                '}';
    }
}
