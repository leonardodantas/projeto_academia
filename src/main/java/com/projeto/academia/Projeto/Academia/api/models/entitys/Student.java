package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.StudentDTO;
import com.projeto.academia.Projeto.Academia.utils.cpf.ValidateCPF;
import com.projeto.academia.Projeto.Academia.utils.model.Entity;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Table(name = "alunos")
@javax.persistence.Entity
public class Student extends Entity {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 120)
    private String name;

    @Column(length = 14)
    private String cpf;

    @Column(length = 120)
    private String email;

    @Column(length = 12, name = "numero_celular")
    private String cellPhone;

    @Transient
    private List<Rating> ratings;

    private Student(StudentDTO studentDTO) {
        this.id = UUID.randomUUID().toString();
        this.cpf = ValidateCPF.format(studentDTO.getCpf());
        this.ratings = studentDTO.getRatings().stream().map(Rating::from).collect(Collectors.toUnmodifiableList());
        this.email = studentDTO.getEmail();
        this.name = studentDTO.getName();
        this.cellPhone = studentDTO.getCellPhone();
    }

    public static Student from(StudentDTO studentDTO) {
        return new Student(studentDTO);
    }
}
