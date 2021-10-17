package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;

public interface IRemoveRatingService {

    RatingDTO removeRating(String idRating);
}
