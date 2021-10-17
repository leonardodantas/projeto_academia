package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.student.IStudentRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentService;
import com.projeto.academia.Projeto.Academia.utils.cpf.ValidateCPF;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindStudentService implements IFindStudentService {

    @Autowired
    private IStudentRepository studentRepository;

    private static final String STUDENT_NOT_EXIST = "Aluno não existe na base de dados";

    @Override
    public CollectionResponse<StudentDTO, Student> findAll(Pageable pageable) {
        Page<Student> studentsPage = studentRepository.findAll(pageable);
        List<StudentDTO> students = studentsPage.getContent().stream().map(StudentDTO::from).collect(Collectors.toUnmodifiableList());
        return new CollectionResponse<StudentDTO, Student>(studentsPage, students);
    }

    @Override
    public StudentDTO findById(String id){
        Optional<Student> student = studentRepository.findById(id);
        return StudentDTO.from(student.get());
    }

    @Override
    public StudentDTO throwExceptionNotExistStudentById(String id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, STUDENT_NOT_EXIST));
        return StudentDTO.from(student);
    }

}
