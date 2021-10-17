package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentService;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRatingStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveRatingStudentService implements IRemoveRatingStudentService {

    @Autowired
    private IFindStudentService findStudentService;

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public StudentDTO removeAllRatingOfStudent(String idStudent) {
        StudentDTO studentDTO = findStudentService.throwExceptionNotExistStudentById(idStudent);
        ratingRepository.deleteAllByIdStudent(studentDTO.getId());
        return studentDTO;
    }
}
