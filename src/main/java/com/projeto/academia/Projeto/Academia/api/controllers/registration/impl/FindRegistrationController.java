package com.projeto.academia.Projeto.Academia.api.controllers.registration.impl;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.controllers.registration.IFindRegistrationController;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Registration;
import com.projeto.academia.Projeto.Academia.api.models.dto.RegistrationDTO;
import com.projeto.academia.Projeto.Academia.api.services.IFindRegistrationService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Api(tags = "Cadastro")
@RequestMapping("/cadastro")
public class FindRegistrationController implements IFindRegistrationController {

    @Autowired
    private IFindRegistrationService findRegistrationService;

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        CollectionResponse<RegistrationDTO, Registration> response = findRegistrationService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findAllById(String id) {
        RegistrationDTO response = findRegistrationService.findById(id);
        if (Strings.isNullOrEmpty(response.getId())){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}
