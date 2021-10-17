package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.ProfileUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Table(name = "perfil")
@Getter
@Entity
public class Profile implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Profile(ProfileUser profileUser) {

    }

    public static Profile from(ProfileUser profileUser) {
        return new Profile(profileUser);
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
