package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Servicio;
import gestion.com.hotel.repository.ServicioRepository;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> findById(Long id) {
        return servicioRepository.findById(id);
    }

    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio update(Long id, Servicio servicioDetails) {
        return servicioRepository.findById(id).map(servicio -> {
            servicio.setNombre(servicioDetails.getNombre());
            servicio.setSlug(servicioDetails.getSlug());
            servicio.setDescripcion(servicioDetails.getDescripcion());
            servicio.setImagenUrl(servicioDetails.getImagenUrl());
            servicio.setActivo(servicioDetails.getActivo());
            return servicioRepository.save(servicio);
        }).orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
    }

    public void delete(Long id) {
        servicioRepository.deleteById(id);
    }
}