package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.google.common.base.Strings;
import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.services.IUpdateRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UpdateRatingService implements IUpdateRatingService {

    private static final String ID_IS_REQUIRED = "Avaliação para atualização não possui ID";
    private static final String NOT_RATING = "Nenhuma avaliação para ser atualizada";

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public RatingDTO updateRating(RatingDTO rating) {
        Rating ratingExist = validateRating(rating);
        ratingExist.updateDate();
        return rating.with(ratingExist);
    }

    private Rating validateRating(RatingDTO rating) {
        if (Strings.isNullOrEmpty(rating.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ID_IS_REQUIRED);
        }
        return this.ratingRepository.findById(rating.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, NOT_RATING));
    }
}
