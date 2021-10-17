package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.services.ICreateRatingService;
import com.projeto.academia.Projeto.Academia.api.services.IFindStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateRatingService implements ICreateRatingService {

    @Autowired
    private IFindStudentService findStudentService;

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {
        findStudentService.throwExceptionNotExistStudentById(ratingDTO.getIdStudent());
        Rating rating = Rating.createOf(ratingDTO);
        Rating ratingSave = ratingRepository.save(rating);
        return RatingDTO.from(ratingSave);
    }
}
