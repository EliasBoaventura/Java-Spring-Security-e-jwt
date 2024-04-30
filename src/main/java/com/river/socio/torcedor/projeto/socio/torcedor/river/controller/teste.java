package com.river.socio.torcedor.projeto.socio.torcedor.river.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping(value = "/protegido")
public class teste {
    
    @GetMapping(value = "/usuario")
    @PreAuthorize("hasAuthority('SCOPE_BASIC')")
    public String paginaProtegida() {
        return "cheguei aqui sou um usuario";
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public String paginaProtegidaAdmin() {
        return "cheguei aqui sou  um admin";
    }

    @GetMapping()
    public String paginaTeste(Model model) {
        return "teste"; 
    }
}
