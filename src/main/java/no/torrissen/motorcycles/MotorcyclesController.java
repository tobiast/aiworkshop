package no.torrissen.motorcycles;

import no.torrissen.motorcycles.model.Motorcycle;
import no.torrissen.motorcycles.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/motorcycles")
public class MotorcyclesController {

    private static final Logger logger = LogManager.getLogger(MotorcyclesController.class);

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @GetMapping
    public List<Motorcycle> getAllMotorcycles() {
        logger.info("Fetching all motorcycles");
        return motorcycleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable Long id) {
        logger.info("Fetching motorcycle with id: {}", id);
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        return motorcycle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        logger.info("Creating a new motorcycle: {}", motorcycle);
        Motorcycle savedMotorcycle = motorcycleRepository.save(motorcycle);
        return ResponseEntity.ok(savedMotorcycle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Motorcycle> updateMotorcycle(@PathVariable Long id, @RequestBody Motorcycle motorcycleDetails) {
        logger.info("Updating motorcycle with id: {}", id);
        Optional<Motorcycle> optionalMotorcycle = motorcycleRepository.findById(id);
        if (optionalMotorcycle.isPresent()) {
            Motorcycle existingMotorcycle = optionalMotorcycle.get();
            existingMotorcycle.setModel(motorcycleDetails.getModel());
            existingMotorcycle.setBrand(motorcycleDetails.getBrand());
            existingMotorcycle.setYear(motorcycleDetails.getYear());
            return ResponseEntity.ok(motorcycleRepository.save(existingMotorcycle));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long id) {
        logger.info("Deleting motorcycle with id: {}", id);
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isPresent()) {
            motorcycleRepository.delete(motorcycle.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/maintenance-cost")
    public ResponseEntity<Double> getTotalMaintenanceCost(@PathVariable Long id) {
        logger.info("Fetching total maintenance cost for motorcycle with id: {}", id);
        Optional<Motorcycle> motorcycle = motorcycleRepository.findById(id);
        if (motorcycle.isPresent()) {
            double totalCost = motorcycle.get().getTotalMaintenanceCost();
            return ResponseEntity.ok(totalCost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
