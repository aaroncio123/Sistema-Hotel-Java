package gestion.com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import gestion.com.hotel.entitys.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByReserva_IdReserva(Long idReserva);
}