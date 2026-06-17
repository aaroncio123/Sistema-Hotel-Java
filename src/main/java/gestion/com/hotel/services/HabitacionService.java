package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Habitacion;
import gestion.com.hotel.repository.HabitacionRepository;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> findById(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion update(Long id, Habitacion habitacionDetails) {
        return habitacionRepository.findById(id).map(habitacion -> {
            habitacion.setNumero(habitacionDetails.getNumero());
            habitacion.setPiso(habitacionDetails.getPiso());
            habitacion.setEstado(habitacionDetails.getEstado());
            habitacion.setObservaciones(habitacionDetails.getObservaciones());
            habitacion.setHotel(habitacionDetails.getHotel());
            habitacion.setCategoria(habitacionDetails.getCategoria());
            return habitacionRepository.save(habitacion);
        }).orElseThrow(() -> new RuntimeException("Habitación no encontrada con id: " + id));
    }

    public void delete(Long id) {
        habitacionRepository.deleteById(id);
    }
}