package com.projeto.academia.Projeto.Academia.api.controllers.registration.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.registration.ICreateRegistrationController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.ICreateRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.impl.CreateRegistrationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "Cadastro")
@RequestMapping("/cadastro")
public class CreateRegistrationController implements ICreateRegistrationController {

    @Autowired
    private ICreateRegistrationService createRegistrationService;

    @Override
    public ResponseEntity<?> createRegistration(RegistrationDTO request) {
        RegistrationDTO response = createRegistrationService.createRegistration(request);
        return ResponseEntity.ok(response);
    }
}
