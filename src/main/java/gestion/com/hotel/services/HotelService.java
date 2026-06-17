package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Hotel;
import gestion.com.hotel.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> findById(Long id) {
        return hotelRepository.findById(id);
    }

    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel update(Long id, Hotel hotelDetails) {
        return hotelRepository.findById(id).map(hotel -> {
            hotel.setNombre(hotelDetails.getNombre());
            hotel.setSlug(hotelDetails.getSlug());
            hotel.setDescripcionBreve(hotelDetails.getDescripcionBreve());
            hotel.setDescripcion(hotelDetails.getDescripcion());
            hotel.setDireccion(hotelDetails.getDireccion());
            hotel.setCiudad(hotelDetails.getCiudad());
            hotel.setTelefono(hotelDetails.getTelefono());
            hotel.setCorreo(hotelDetails.getCorreo());
            hotel.setImagenUrl(hotelDetails.getImagenUrl());
            hotel.setActivo(hotelDetails.getActivo());
            return hotelRepository.save(hotel);
        }).orElseThrow(() -> new RuntimeException("Hotel no encontrado con id: " + id));
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }
}