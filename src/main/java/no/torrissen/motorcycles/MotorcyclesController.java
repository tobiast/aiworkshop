package no.torrissen.motorcycles;

import no.torrissen.motorcycles.model.Motorcycle;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
public class MotorcyclesController {

    @GetMapping
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {

        return ResponseEntity.ok(List.of(
                new Motorcycle("Ninja", "Kawasaki", 2021),
                new Motorcycle("CBR", "Honda", 2021)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Long id) {
        return ResponseEntity.ok(new Motorcycle("Ninja", "Kawasaki", 2021));
    }
}
