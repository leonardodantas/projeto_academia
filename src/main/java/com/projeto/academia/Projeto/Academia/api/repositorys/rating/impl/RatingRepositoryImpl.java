package com.projeto.academia.Projeto.Academia.api.repositorys.rating.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class RatingRepositoryImpl implements IRatingRepository {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Page<Rating> findAllByIdStudent(String id, Pageable pageable) {
        return ratingRepository.findAllByIdStudent(id, pageable);
    }

    @Override
    public List<Rating> findAllByIdStudent(String id) {
        return ratingRepository.findAllByIdStudent(id);
    }

    @Override
    public void deleteAllByIdStudent(String id) {
        try {
            ratingRepository.deleteAllByIdStudent(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<Rating> findFirstByIdAlunoOrderByDateRating(String id) {
        return ratingRepository.findFirstByIdStudentOrderByDateRatingDesc(id);
    }

    @Override
    public Optional<Rating> findFirstByIdAlunoOrderByDateUpdate(String id) {
        return ratingRepository.findFirstByIdStudentOrderByDateRatingUpdateDesc(id);
    }

    @Override
    public Rating save(Rating rating) {
        try {
            return ratingRepository.save(rating);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<Rating> findById(String id) {
        return ratingRepository.findById(id);
    }

    @Override
    public Optional<Rating> findFirstByIdStudentWithDateRating(String idStudent) {
        return ratingRepository.findFirstByIdStudentOrderByDateRatingDesc(idStudent);
    }

    @Override
    public Optional<Rating> findFirstByIdStudentOrderByDateRating(String idStudent) {
        return ratingRepository.findFirstByIdStudentOrderByDateRatingDesc(idStudent);
    }

    @Override
    public void removeRating(String idRating) {
        try {
            ratingRepository.deleteById(idRating);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Page<Rating> findAll(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }
}
