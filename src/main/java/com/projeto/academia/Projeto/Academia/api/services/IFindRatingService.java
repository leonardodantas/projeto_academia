package com.projeto.academia.Projeto.Academia.api.services;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.data.domain.Pageable;


public interface IFindRatingService {

    CollectionResponse<RatingDTO, Rating> findAll(Pageable pageable);

}
