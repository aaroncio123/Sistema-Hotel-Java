package gestion.com.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gestion.com.hotel.services.CategoriaService;
import gestion.com.hotel.services.GeminiService;
import gestion.com.hotel.services.HabitacionService;
import gestion.com.hotel.services.HotelService;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    @Autowired
    private GeminiService geminiService;
    @Autowired
    private HotelService hotelService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private HabitacionService habitacionService;
   @PostMapping("/preguntar")
    public ResponseEntity<ChatbotResponse> responderPregunta(@RequestBody PreguntaRequest pregunta) {

        int cantidadHoteles = hotelService.findAll().size();
        int cantidadHabitaciones = habitacionService.findAll().size();
        int cantCategorias = categoriaService.findAll().size();

        String contexto = """
        Eres el asistente virtual de HotelesUPN, responde breve y se cordial.

        Información actual del sistema:
        - Hoteles registrados: %d
        - Habitaciones registradas: %d
        - Categorias registradas: %d
        Pregunta del usuario:
        %s

        Responde de forma breve y clara usando solo la información del sistema.
        """.formatted(
            cantidadHoteles,
            cantidadHabitaciones,
            cantCategorias,
            pregunta.getMensaje()
        );

        String respuesta = geminiService.preguntar(contexto);

        return ResponseEntity.ok(new ChatbotResponse(respuesta));
    }
}