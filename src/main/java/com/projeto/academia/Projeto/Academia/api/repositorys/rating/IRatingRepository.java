package com.projeto.academia.Projeto.Academia.api.repositorys.rating;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IRatingRepository  {

    Page<Rating> findAllByIdStudent(String id, Pageable pageable);
    List<Rating> findAllByIdStudent(String id);
    void deleteAllByIdStudent(String id);
    Optional<Rating> findFirstByIdAlunoOrderByDateRating(String id);
    Optional<Rating> findFirstByIdAlunoOrderByDateUpdate(String id);
    Rating save(Rating rating);
    Optional<Rating> findById(String id);
    Optional<Rating> findFirstByIdStudentWithDateRating(String idStudent);
    Optional<Rating> findFirstByIdStudentOrderByDateRating(String idStudent);
    void removeRating(String idRating);
    Page<Rating> findAll(Pageable pageable);
}
