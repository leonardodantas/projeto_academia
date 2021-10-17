package com.projeto.academia.Projeto.Academia.api.controllers.student.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.student.ISaveStudentController;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.services.ICreateStudentService;
import com.projeto.academia.Projeto.Academia.api.services.impl.CreateStudentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(tags = "Alunos")
@RestController
@RequestMapping("/aluno")
public class SaveStudentController implements ISaveStudentController {

    @Autowired
    private ICreateStudentService saveStudentService;

    @Override
    public ResponseEntity<?> saveStudent(StudentDTO request) {
        StudentDTO response = saveStudentService.createStudent(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
