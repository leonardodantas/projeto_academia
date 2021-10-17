package com.projeto.academia.Projeto.Academia.api.repositorys.student;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentRepository {

    Optional<Student> findByCpf(String cpf);
    Student save(Student student);
    Page<Student> findAll(Pageable pageable);
    Optional<Student> findById(String id);
}
