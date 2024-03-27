package com.example.firstdates.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class publicController {

    @GetMapping("/public")
    public String publics() {
        return
                "  <div class=\"conversation-list\">\n" +
                        "    <div class=\"conversation-item\">\n" +
                        "        <div class=\"details\">\n" +
                        "            <div style=\"display: flex; align-items: center;\">\n" +
                        "                <div class=\"avatar\"></div>\n" +
                        "                <h3 style=\"margin-left: 10px;\"><span>Contacto 1</span></h3>\n" +

                        "            <div style=\"display: flex; align-items: center;\">\n" +
                        "                <div class=\"avatar\"></div>\n" +
                        "                <h3 style=\"margin-left: 10px;\"><span>Contacto 2</span></h3>\n" +
                        "            </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</div>\n"
                ;}

    @GetMapping("/chat")
    public String chat() {
        return
                "  <div class=\"conversation-list\">\n" +
                "    <div class=\"conversation-item\">\n" +
                "      <div class=\"avatar\">A</div>\n" +
                "      <div class=\"details\">\n" +
                "        <h3/>Contacto 1</h3>\n" +
                "        <p>Último mensaje: Hola, ¿cómo estás?</p>\n" +
                "        <p>Hace 5 minutos</p>\n" +
                "      </div>\n" +
                "    </div>\n"
                ;}


    @GetMapping("/request")
    public String request() {
        return  "<div class=\"conversation-list\">\n" +
                "    <div class=\"conversation-item\">\n" +
                "        <div class=\"avatar\">A</div>\n" +
                "        <div class=\"details\">\n" +
                "            <h3>Contacto 1</h3>\n" +
                "            <p>Usuario1 ha enviado una solicitud de amistad</p>\n" +
                "            <button class=\"btnAceptar\">Aceptar</button>\n" +
                "            <button class=\"btnRechazar\">Rechazar</button>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n"

                ;}

    @GetMapping("/avaible")
    public String avaible() {
        return"<div class=\"conversation-list\">\n" +
                "    <div class=\"conversation-item\">\n" +
                "      <div class=\"avatar\">A</div>\n" +
                "      <div class=\"details\">\n" +
                "        <h3>Contacto 1</h3>\n" +
                "        <p>Usuario1 disponible para cita</p>\n" +
                "        <button class=\"btnSolicitar\">Solicitar Unirse</button>\n" +
                "      </div>\n" +
                "    </div>\n"
                ;}}

    /*
    @GetMapping("/user")
    public String user(){
        return

                "    <div class=\"chat-container\">\n" +
                "        <div class=\"contact-name\">Nombre del Contacto</div>\n" +
                "        <div class=\"chat-messages\">\n" +
                "            <div class=\"message received\">\n" +
                "                <div class=\"message-content\">¡Hola! ¿Cómo estás?</div>\n" +
                "            </div>\n" +
                "            <div class=\"message sent\">\n" +
                "                <div class=\"message-content\">Hola, estoy bien. ¿Y tú?</div>\n" +
                "            </div>\n" +
                "            <!-- Más mensajes pueden ir aquí -->\n" +
                "        </div>\n" +
                "        <div class=\"input-container\">\n" +
                "            <input type=\"text\" placeholder=\"Escribe tu mensaje...\">\n" +
                "            <button>Enviar</button>\n" +
                "        </div>\n" +
                "    </div>";
    }
}
*/