package no.torrissen.motorcycles;

import no.torrissen.motorcycles.model.Motorcycle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorcyclesController {

    @GetMapping("/motorcycle")
    public Motorcycle getMotorcycle() {
        return new Motorcycle("Street 750", "Harley-Davidson", 2023);
    }
}
