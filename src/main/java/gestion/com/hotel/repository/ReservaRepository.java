package gestion.com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gestion.com.hotel.entitys.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByUsuario_IdUsuario(Long idUsuario);
    List<Reserva> findByHabitacion_IdHabitacion(Long idHabitacion);
}