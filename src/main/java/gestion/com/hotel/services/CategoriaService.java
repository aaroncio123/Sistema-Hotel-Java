package gestion.com.hotel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gestion.com.hotel.entitys.Categoria;
import gestion.com.hotel.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Long id, Categoria categoriaDetails) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(categoriaDetails.getNombre());
            categoria.setSlug(categoriaDetails.getSlug());
            categoria.setDescripcion(categoriaDetails.getDescripcion());
            categoria.setTarifaBase(categoriaDetails.getTarifaBase());
            categoria.setCapacidad(categoriaDetails.getCapacidad());
            categoria.setImagenUrl(categoriaDetails.getImagenUrl());
            categoria.setActivo(categoriaDetails.getActivo());
            return categoriaRepository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }

    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }   
}
