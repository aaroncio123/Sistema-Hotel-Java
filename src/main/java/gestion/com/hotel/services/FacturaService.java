package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Factura;
import gestion.com.hotel.repository.FacturaRepository;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }

    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura update(Long id, Factura facturaDetails) {
        return facturaRepository.findById(id).map(factura -> {
            factura.setReserva(facturaDetails.getReserva());
            factura.setMonto_total(facturaDetails.getMonto_total());
            factura.setEstado(facturaDetails.getEstado());
            return facturaRepository.save(factura);
        }).orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }

    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }
}