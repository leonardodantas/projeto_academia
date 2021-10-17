package com.projeto.academia.Projeto.Academia.api.repositorys.student;


import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByCpf(String cpf);
}
