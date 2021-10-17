package com.projeto.academia.Projeto.Academia.api.controllers.student.impl;

import com.projeto.academia.Projeto.Academia.api.controllers.student.IFindStudentController;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentService;
import com.projeto.academia.Projeto.Academia.api.services.impl.FindStudentService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(tags = "Alunos")
@RestController
@RequestMapping("/aluno")
public class FindStudentController implements IFindStudentController {

    @Autowired
    private IFindStudentService findStudentService;

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        CollectionResponse<StudentDTO, Student> response = findStudentService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> findById(String id) {
        StudentDTO response = findStudentService.findById(id);
        return ResponseEntity.ok(response);
    }
}
