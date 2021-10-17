package com.projeto.academia.Projeto.Academia.api.repositorys.rating;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, String> {

    Page<Rating> findAllByIdStudent(String idStudent, Pageable pageable);
    List<Rating> findAllByIdStudent(String idStudent);
    void deleteAllByIdStudent(String idStudent);
    Optional<Rating> findFirstByIdStudentOrderByDateRatingDesc(String idStudent);
    Optional<Rating> findFirstByIdStudentOrderByDateRatingUpdateDesc(String idStudent);
}
