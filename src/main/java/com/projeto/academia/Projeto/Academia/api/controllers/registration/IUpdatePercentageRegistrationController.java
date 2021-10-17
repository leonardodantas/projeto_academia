package com.projeto.academia.Projeto.Academia.api.controllers.registration;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUpdatePercentageRegistrationController {

    @PutMapping
    @ApiOperation(value = "Atualizar a porcentagem de um cadastro")
    ResponseEntity<?> updatePercetage(@RequestParam(required = true, name = "cadastro") String id,
                                      @RequestParam(required = true, name = "porcentagem") double newPercentage);

}
