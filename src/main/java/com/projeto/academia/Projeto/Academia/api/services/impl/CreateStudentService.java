package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.student.IStudentRepository;
import com.projeto.academia.Projeto.Academia.api.services.ICreateStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CreateStudentService implements ICreateStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    private static final String CPF_REGISTRATION = "CPF já cadastrado";

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        checkStudentAlreadyRegistered(studentDTO);
        Student student = Student.from(studentDTO);
        Student studentSave = studentRepository.save(student);
        return StudentDTO.from(studentSave);
    }

    private void checkStudentAlreadyRegistered(StudentDTO studentDTO) {
        Optional<Student> studentExist = studentRepository.findByCpf(studentDTO.getCpf());
        if (studentExist.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, CPF_REGISTRATION);
        }
    }

}
