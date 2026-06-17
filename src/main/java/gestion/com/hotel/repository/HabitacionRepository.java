package gestion.com.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gestion.com.hotel.entitys.Habitacion;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    Optional<Habitacion> findByHotel_IdHotelAndNumero(Long idHotel, String numero);
    List<Habitacion> findByHotel_IdHotel(Long idHotel);
}