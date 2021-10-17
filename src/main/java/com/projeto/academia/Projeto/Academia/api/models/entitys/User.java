package com.projeto.academia.Projeto.Academia.api.models.entitys;

import com.projeto.academia.Projeto.Academia.api.models.dto.UserDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@Table(name = "usuarios")
public class User extends com.projeto.academia.Projeto.Academia.utils.model.Entity implements UserDetails {

    @Id
    private String id;
    private String name;
    private String password;
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    private User(UserDTO userDTO) {
        this.id = UUID.randomUUID().toString();
        this.profiles = Collections.singletonList(Profile.from(userDTO.getProfileUser()));
        this.email = userDTO.getEmail();
        this.name = userDTO.getName();
        this.password = userDTO.getPassword();
    }

    public static User from(UserDTO userDTO) {
        return new User(userDTO);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
