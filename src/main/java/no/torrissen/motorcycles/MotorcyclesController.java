package no.torrissen.motorcycles;

import no.torrissen.motorcycles.model.Motorcycle;
import no.torrissen.motorcycles.repository.MotorcycleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/motorcycles")
public class MotorcyclesController {

    private static final Logger logger = LogManager.getLogger(MotorcyclesController.class);

    private final MotorcycleRepository motorcycleRepository;

    public MotorcyclesController(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles() {

        logger.info("Fetching all motorcycles");
        List<Motorcycle> motorcycles = motorcycleRepository.findAll();
        logger.debug("Motorcycles found: {}", motorcycles);
        return ResponseEntity.ok(motorcycles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Long id) {
        logger.info("Fetching motorcycle with ID: {}", id);
        return motorcycleRepository.findById(id)
                .map(motorcycle -> {
                    logger.debug("Motorcycle found: {}", motorcycle);
                    return ResponseEntity.ok(motorcycle);
                })
                .orElseGet(() -> {
                    logger.warn("Motorcycle with ID: {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }
    @PostMapping
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        logger.info("Creating new motorcycle: {}", motorcycle);
        Motorcycle savedMotorcycle = motorcycleRepository.save(motorcycle);
        logger.debug("Motorcycle saved: {}", savedMotorcycle);
        return ResponseEntity.ok(savedMotorcycle);
    }
}
