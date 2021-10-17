package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.data.domain.Pageable;

public interface IFindStudentService {

    CollectionResponse<StudentDTO, Student> findAll(Pageable pageable);
    StudentDTO findById(String id);
    StudentDTO throwExceptionNotExistStudentById(String id);
}
