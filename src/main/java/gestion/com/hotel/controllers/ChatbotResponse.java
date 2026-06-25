package gestion.com.hotel.controllers;

public class ChatbotResponse {

    private String respuesta;

    public ChatbotResponse(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }
}