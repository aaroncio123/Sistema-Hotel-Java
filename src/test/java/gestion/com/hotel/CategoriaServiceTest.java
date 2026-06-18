package gestion.com.hotel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import gestion.com.hotel.entitys.Categoria;
import gestion.com.hotel.repository.CategoriaRepository;
import gestion.com.hotel.services.CategoriaService;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNombre("Suite de Lujo");
        categoria.setSlug("suite-de-lujo");
        categoria.setTarifaBase(250.00);
        categoria.setCapacidad(2);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));

        // Act
        List<Categoria> categorias = categoriaService.findAll();

        // Assert
        assertNotNull(categorias);
        assertEquals(1, categorias.size());
        verify(categoriaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        // Act
        Optional<Categoria> resultado = categoriaService.findById(1L);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Suite de Lujo", resultado.get().getNombre());
        verify(categoriaRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        // Arrange
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);

        // Act
        Categoria categoriaGuardada = categoriaService.save(new Categoria());

        // Assert
        assertNotNull(categoriaGuardada);
        assertEquals("Suite de Lujo", categoriaGuardada.getNombre());
        verify(categoriaRepository, times(1)).save(any(Categoria.class));
    }

    @Test
    void testDelete() {
        // Arrange
        Long categoriaId = 1L;
        doNothing().when(categoriaRepository).deleteById(categoriaId);

        // Act
        categoriaService.delete(categoriaId);

        // Assert
        verify(categoriaRepository, times(1)).deleteById(categoriaId);
    }
}