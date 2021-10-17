package com.projeto.academia.Projeto.Academia.api.controllers.registration.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.registration.IUpdatePercentageRegistrationController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.IUpdateRegistrationService;
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
public class UpdatePercentageRegistrationController implements IUpdatePercentageRegistrationController {

    @Autowired
    private IUpdateRegistrationService updateRegistrationService;

    @Override
    public ResponseEntity<?> updatePercetage(String id, double newPercentage) {
        RegistrationDTO response = updateRegistrationService.updatePercentage(id, newPercentage);
        return ResponseEntity.ok(response);
    }
}
