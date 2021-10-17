package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;

public interface ICreateStudentService {

    StudentDTO createStudent(StudentDTO studentDTO);
}
