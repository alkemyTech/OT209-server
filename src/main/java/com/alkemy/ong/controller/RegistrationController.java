package com.alkemy.ong.controller;

import com.alkemy.ong.models.request.RegistrationRequest;
import com.alkemy.ong.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth/register")
@AllArgsConstructor
@Slf4j

public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {

        log.info("Inicializando servicio de registro de usuarios");
        return registrationService.register(request);
    }
    @PutMapping(value ="/{id}")
    public void putRegister(@RequestBody RegistrationRequest request) {

        log.info("Inicializando servicio de registro para modificar datos de usuarios");
        registrationService.Putregister(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token, @AuthenticationPrincipal User user) {

        log.info("Analisis de credenciales de usuario");
        return registrationService.confirmToken(token);
    }
    @DeleteMapping(path = "delete/{id}")
    public String delete(@PathVariable("id") Long id){
        try {
            registrationService.delete(id);
            return "USER was deleted id: " + id;
        } catch (Exception e) {
            return "USER cannot deleted id: " + id;
        }
    }
}