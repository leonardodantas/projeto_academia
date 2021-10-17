package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.services.IRemoveRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RemoveRatingService implements IRemoveRatingService {

    @Autowired
    private IRatingRepository ratingRepository;

    private static final String RATING_NOT_EXIST = "Avaliação não encontrada";

    @Override
    public RatingDTO removeRating(String idRating){
        Rating rating = ratingRepository.findById(idRating)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, RATING_NOT_EXIST));
        ratingRepository.removeRating(idRating);
        return RatingDTO.from(rating);
    }
}
