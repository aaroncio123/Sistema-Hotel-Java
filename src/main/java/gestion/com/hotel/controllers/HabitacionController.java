package gestion.com.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gestion.com.hotel.entitys.Habitacion;
import gestion.com.hotel.services.HabitacionService;

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin(origins = "*")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> getAll() {
        return habitacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> getById(@PathVariable Long id) {
        return habitacionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Habitacion> create(@RequestBody Habitacion habitacion) {
        return new ResponseEntity<>(habitacionService.save(habitacion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> update(@PathVariable Long id, @RequestBody Habitacion habitacionDetails) {
        try {
            return ResponseEntity.ok(habitacionService.update(id, habitacionDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            habitacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}