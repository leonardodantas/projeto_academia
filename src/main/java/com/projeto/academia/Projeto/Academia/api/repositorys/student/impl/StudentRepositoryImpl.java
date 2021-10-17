package com.projeto.academia.Projeto.Academia.api.repositorys.student.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.repositorys.student.IStudentRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class StudentRepositoryImpl implements IStudentRepository {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Optional<Student> findByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }

    @Override
    public Student save(Student student) {
        try {
            return this.studentRepository.save(student);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return this.studentRepository.findAll(pageable);
    }

    @Override
    public Optional<Student> findById(String id) {
        return this.studentRepository.findById(id);
    }
}
