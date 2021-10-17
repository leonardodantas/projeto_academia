package com.projeto.academia.Projeto.Academia.api.services.impl;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import com.projeto.academia.Projeto.Academia.api.models.dto.RatingDTO;
import com.projeto.academia.Projeto.Academia.api.repositorys.rating.IRatingRepository;
import com.projeto.academia.Projeto.Academia.api.services.IFindRatingService;
import com.projeto.academia.Projeto.Academia.utils.response.CollectionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindRatingService implements IFindRatingService {

    @Autowired
    private IRatingRepository ratingRepository;

    @Override
    public CollectionResponse<RatingDTO, Rating> findAll(Pageable pageable){
        Page<Rating> ratingPage = ratingRepository.findAll(pageable);
        List<RatingDTO> ratingsDTO = ratingPage.getContent().stream().map(RatingDTO::from).collect(Collectors.toUnmodifiableList());
        return new CollectionResponse<>(ratingPage, ratingsDTO);
    }
}
