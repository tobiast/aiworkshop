package no.torrissen.motorcycles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorcyclesController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/motorcycle")
    public Motorcycle getMotorcycle() {
        return new Motorcycle("Street 750", "Harley-Davidson", 2023);
    }
}
