package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gestion.com.hotel.entitys.Habitacion;
import gestion.com.hotel.entitys.Reserva;
import gestion.com.hotel.repository.HabitacionRepository;
import gestion.com.hotel.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    @Transactional
    public Reserva save(Reserva reserva) {
        // 1. Buscar la habitación en la base de datos
        Habitacion habitacion = habitacionRepository.findById(reserva.getHabitacion().getIdHabitacion())
                .orElseThrow(() -> new RuntimeException("La habitación solicitada no existe."));

        // 2. Validar que la habitación esté disponible
        if (!"Disponible".equalsIgnoreCase(habitacion.getEstado())) {
            throw new RuntimeException("La habitación no está disponible para reservar. Estado actual: " + habitacion.getEstado());
        }

        // 3. Cambiar el estado de la habitación a 'Ocupada' y actualizarla
        habitacion.setEstado("Ocupada");
        habitacionRepository.save(habitacion);

        // 4. Asignar la habitación validada y guardar la reserva
        reserva.setHabitacion(habitacion);
        return reservaRepository.save(reserva);
    }

    public Reserva update(Long id, Reserva reservaDetails) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setUsuario(reservaDetails.getUsuario());
            reserva.setHabitacion(reservaDetails.getHabitacion());
            reserva.setFecha_inicio(reservaDetails.getFecha_inicio());
            reserva.setFecha_fin(reservaDetails.getFecha_fin());
            reserva.setEstado(reservaDetails.getEstado());
            return reservaRepository.save(reserva);
        }).orElseThrow(() -> new RuntimeException("Reserva no encontrada con id: " + id));
    }

    public void delete(Long id) {
        reservaRepository.deleteById(id);
    }
}