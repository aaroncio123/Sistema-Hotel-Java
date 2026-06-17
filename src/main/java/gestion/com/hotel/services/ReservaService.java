package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Reserva;
import gestion.com.hotel.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva reserva) {
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