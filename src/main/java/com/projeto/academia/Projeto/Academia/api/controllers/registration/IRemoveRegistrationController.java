package com.projeto.academia.Projeto.Academia.api.controllers.registration;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IRemoveRegistrationController {

    @DeleteMapping("/{id}")
    @Transactional
    ResponseEntity<?> removeById(@PathVariable("id") String id);

}
