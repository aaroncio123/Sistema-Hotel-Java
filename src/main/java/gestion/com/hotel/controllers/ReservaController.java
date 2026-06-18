package gestion.com.hotel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.com.hotel.entitys.Reserva;
import gestion.com.hotel.services.ReservaService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*") // Ajusta según tu configuración de Angular (generalmente http://localhost:4200)
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listarTodas() {
        return reservaService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.save(reserva);
            return ResponseEntity.ok(nuevaReserva);
        } catch (RuntimeException e) {
            // Devuelve un JSON de error si la habitación está ocupada, tal y como lo espera Angular
            return ResponseEntity.badRequest().body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }
}