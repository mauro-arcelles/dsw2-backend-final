package com.proyecto.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@ToString
public class JwtDto {
    private String token;
    private String bearer = "Bearer";
    private String login;
    private String nombreCompleto;
    private int idUsuario;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token,String login, String nombreCompleto, int idUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.login = login;
        this.nombreCompleto = nombreCompleto;
        this.authorities = authorities;
        this.idUsuario = idUsuario;
    }

    
}
