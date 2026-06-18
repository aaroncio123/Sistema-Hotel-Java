package gestion.com.hotel;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import gestion.com.hotel.entitys.Habitacion;
import gestion.com.hotel.entitys.Reserva;
import gestion.com.hotel.repository.HabitacionRepository;
import gestion.com.hotel.repository.ReservaRepository;
import gestion.com.hotel.services.ReservaService;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    // @Mock crea un "doble" (simulación) de nuestros repositorios para no tocar la BD real
    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private HabitacionRepository habitacionRepository;

    // @InjectMocks inyecta los repositorios simulados dentro de nuestro servicio real
    @InjectMocks
    private ReservaService reservaService;

    private Habitacion habitacion;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        // Este método se ejecuta antes de cada prueba para preparar los datos iniciales
        habitacion = new Habitacion();
        habitacion.setIdHabitacion(1L);
        habitacion.setEstado("Disponible");

        reserva = new Reserva();
        reserva.setIdReserva(1L);
        reserva.setHabitacion(habitacion);
        reserva.setFecha_inicio(LocalDate.now());
        reserva.setFecha_fin(LocalDate.now().plusDays(3));
        reserva.setEstado("Confirmada");
    }

    @Test
    void save_CuandoHabitacionEstaDisponible_DeberiaGuardarReservaYCambiarEstado() {
        // Arrange (Preparar): Le decimos al mock cómo debe comportarse
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));
        when(reservaRepository.save(any(Reserva.class))).thenReturn(reserva);

        // Act (Actuar): Ejecutamos el método que queremos probar
        Reserva reservaGuardada = reservaService.save(reserva);

        // Assert (Afirmar): Verificamos que los resultados sean los esperados
        assertNotNull(reservaGuardada, "La reserva guardada no debería ser nula");
        assertEquals("Ocupada", habitacion.getEstado(), "El estado de la habitación debió cambiar a 'Ocupada'");
        
        // Verificamos que los métodos save de ambos repositorios se llamaron exactamente 1 vez
        verify(habitacionRepository, times(1)).save(habitacion);
        verify(reservaRepository, times(1)).save(reserva);
    }

    @Test
    void save_CuandoHabitacionNoEstaDisponible_DeberiaLanzarExcepcion() {
        // Arrange: Simulamos que la habitación ya está ocupada
        habitacion.setEstado("Ocupada");
        when(habitacionRepository.findById(1L)).thenReturn(Optional.of(habitacion));

        // Act & Assert: Esperamos que se lance una RuntimeException
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> reservaService.save(reserva));
        assertTrue(excepcion.getMessage().contains("La habitación no está disponible para reservar"));
    }
}
