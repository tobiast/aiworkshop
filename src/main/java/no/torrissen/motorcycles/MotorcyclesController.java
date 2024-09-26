package no.torrissen.motorcycles;

import no.torrissen.motorcycles.model.Motorcycle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MotorcyclesController {

    private static final Logger logger = LogManager.getLogger(MotorcyclesController.class);

    @GetMapping("/motorcycle")
    public Motorcycle getMotorcycle() {
        logger.info("Fetching a motorcycle");
        return new Motorcycle("Street 750", "Harley-Davidson", 2023);
    }

    @PostMapping("/motorcycle")
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        logger.info("Creating a new motorcycle: {}", motorcycle);
        // Here you would typically save the motorcycle to a database
        // For now, we'll just return the created motorcycle
        return ResponseEntity.ok(motorcycle);
    }
}
