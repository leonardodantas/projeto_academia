package com.projeto.academia.Projeto.Academia.api.controllers.registration.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.registration.IRemoveRegistrationController;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRegistrationService;
import com.projeto.academia.Projeto.Academia.api.services.impl.RemoveRegistrationService;
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
public class RemoveRegistrationController implements IRemoveRegistrationController {

    @Autowired
    private IRemoveRegistrationService removeRegitrationService;

    @Override
    public ResponseEntity<?> removeById(String id) {
        RegistrationDTO response = removeRegitrationService.removeRegistration(id);
        return ResponseEntity.ok(response);
    }
}
