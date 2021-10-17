package com.projeto.academia.Projeto.Academia.api.models.dto;

import com.projeto.academia.Projeto.Academia.api.models.entitys.Student;
import com.projeto.academia.Projeto.Academia.utils.model.dto.DataTransferObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentDTO implements DataTransferObject {

    private String id;

    @ApiModelProperty(value = "Nome do aluno", name = "nome", dataType = "String", example = "Leonardo Dantas")
    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String name;

    @ApiModelProperty(value = "CPF do aluno", name = "cpf", dataType = "String", example = "547812366584")
    @NotNull(message = "{not.null}") @Length(min = 11, max = 14, message = "{length.invalid}")
    private String cpf;

    @ApiModelProperty(value = "Email do aluno", name = "email", dataType = "String", example = "leonardo@email.com.br")
    @Email(message = "{email}")
    @NotNull(message = "{not.null}") @Length(min = 20, max = 120, message = "{length.invalid}")
    private String email;

    @ApiModelProperty(value = "Numero do aluno", name = "numeroCelular", dataType = "String", example = "99999999")
    @NotNull @Length(min = 8, max = 12)
    private String cellPhone;

    @ApiModelProperty(value = "Lista de avaliações realizadas do aluno", name = "avaliacoes", dataType = "List<String>")
    private List<RatingDTO> ratings;

    private StudentDTO(Student student) {
        this.email = student.getEmail();
        this.cpf = student.getCpf();
        this.ratings = student.getRatings().stream().map(RatingDTO::from).collect(Collectors.toUnmodifiableList());
        this.name = student.getName();
        this.cellPhone = student.getCellPhone();
    }

    private StudentDTO(StudentDTO student, List<RatingDTO> ratingList) {
        this.email = student.getEmail();
        this.cpf = student.getCpf();
        this.ratings = ratingList;
        this.name = student.getName();
        this.cellPhone = student.getCellPhone();
    }

    public static StudentDTO from(Student student) {
        return new StudentDTO(student);
    }

    public StudentDTO rating(List<RatingDTO> ratingList) {
        return new StudentDTO(this, ratingList);
    }
}
