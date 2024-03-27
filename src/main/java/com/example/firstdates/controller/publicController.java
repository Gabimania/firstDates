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
                "        <h3>Contacto 1</h3>\n" +
                "        <p>Último mensaje: Hola, ¿cómo estás?</p>\n" +
                "        <p>Hace 5 minutos</p>\n" +
                "      </div>\n" +
                "    </div>\n"
                ;}


    @GetMapping("/request")
    public String request() {
        return  "<h2>Solicitudes pendientes</h2>\n" +
                "  <div class=\"solicitud\">\n" +
                "      <div class=\"avatar\">A</div>\n" +
                "      <div class=\"details\">\n" +

                "    <p>Usuario1 ha enviado una solicitud de amistad</p>\n" +
                "    <button class=\"boton-aceptar\">Aceptar</button>\n" +
                "    <button class=\"boton-rechazar\">Rechazar</button>\n" +
                "  </div>"

                ;}

    @GetMapping("/avaible")
    public String avaible() {
        return "<h1>hola mundo</h1>"

                ;}
}
