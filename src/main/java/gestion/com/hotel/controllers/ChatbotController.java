package gestion.com.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.com.hotel.services.HabitacionService;
import gestion.com.hotel.services.HotelService;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping("/preguntar")
    public ResponseEntity<String> responderPregunta(@RequestBody PreguntaRequest pregunta) {
        String mensajeUsuario = pregunta.getMensaje().toLowerCase();
        String respuesta = "Hola, soy el asistente virtual del HotelesUPN. ¿En qué te puedo ayudar?";

        // Lógica de lectura básica del backend. 
        // ¡Aquí en un futuro puedes mandar esta información como contexto a una IA como Gemini!
        if (mensajeUsuario.contains("hoteles")) {
            int cantidadHoteles = hotelService.findAll().size();
            respuesta = "Actualmente tenemos " + cantidadHoteles + " hoteles disponibles en nuestra cadena.";
        } else if (mensajeUsuario.contains("habitaciones")) {
            int cantidadHabitaciones = habitacionService.findAll().size();
            respuesta = "Contamos con un total de " + cantidadHabitaciones + " habitaciones registradas en el sistema.";
        } else if (mensajeUsuario.contains("reserva")) {
            respuesta = "Para realizar una reserva, puedes dirigirte a la sección de Hoteles, seleccionar uno y ver sus habitaciones disponibles.";
        } else {
            respuesta = "Aún estoy aprendiendo, por ahora puedo hablarte sobre nuestros 'hoteles', 'habitaciones' o cómo generar una 'reserva'.";
        }

        // Se retorna formato JSON plano
        return ResponseEntity.ok("{\"respuesta\": \"" + respuesta + "\"}");
    }
}

class PreguntaRequest {
    private String mensaje;

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}