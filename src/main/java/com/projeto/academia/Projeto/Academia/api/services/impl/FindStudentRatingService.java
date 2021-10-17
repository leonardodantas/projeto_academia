package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.student.IStudentRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentRatingService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindStudentRatingService implements IFindStudentRatingService {

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public CollectionResponse<RatingDTO, Rating> findAllByIdStudent(String idStudent, Pageable pageable) {
        Page<Rating> ratingPage = ratingRepository.findAllByIdStudent(idStudent, pageable);
        List<RatingDTO> ratingRequestDTO = ratingPage.getContent().stream().map(RatingDTO::from).collect(Collectors.toUnmodifiableList());
        return new CollectionResponse<>(ratingPage, ratingRequestDTO);
    }

    @Override
    public List<RatingDTO> findListRatingByIdStudent(String idStudent) {
        List<Rating> ratingList = ratingRepository.findAllByIdStudent(idStudent);
        return ratingList.stream().map(RatingDTO::from).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public RatingDTO findLastRatingStudent(String idStudent) {
        Optional<Rating> rating = ratingRepository.findFirstByIdStudentOrderByDateRating(idStudent);
        return RatingDTO.from(rating.get());
    }

    @Override
    public RatingDTO findLastRatingStudentUpdate(String idStudent) {
        Optional<Rating> rating = ratingRepository.findFirstByIdStudentWithDateRating(idStudent);
        return RatingDTO.from(rating.get());
    }
}
