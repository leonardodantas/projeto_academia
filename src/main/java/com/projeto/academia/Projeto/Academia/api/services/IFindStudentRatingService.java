package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFindStudentRatingService {

    CollectionResponse<RatingDTO, Rating> findAllByIdStudent(String idStudent, Pageable pageable);
    List<RatingDTO> findListRatingByIdStudent(String idStudent);
    RatingDTO findLastRatingStudent(String idStudent);
    RatingDTO findLastRatingStudentUpdate(String idStudent);

}
