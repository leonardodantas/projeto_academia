package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;

public interface IRemoveRatingStudentService  {

    StudentDTO removeAllRatingOfStudent(String idStudent);
}
