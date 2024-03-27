package com.example.firstdates.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {
    @GetMapping("/api")
    public String datos(){

        return "<h1>hola mundo</h1>";
    }
}




