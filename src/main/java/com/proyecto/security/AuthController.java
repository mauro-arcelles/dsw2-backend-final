package com.proyecto.security;

import com.proyecto.entidad.Usuario;
import com.proyecto.service.UsuarioService;
import com.proyecto.util.AppSettings;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CommonsLog
@RestController
@RequestMapping("/url/auth")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtProvider jwtProvider;
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario){
    	log.info(">>> login >>> " + loginUsuario.getLogin());
    	log.info(">>> login >>> " + loginUsuario.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getLogin(), loginUsuario.getPassword()));
        
        //Generacion del Token
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        log.info(">>> jwt >>> " + jwt);
        
        //Validaciones en la base de datos
        UsuarioPrincipal usuario = (UsuarioPrincipal)authentication.getPrincipal();
        log.info(">>> usuario >>> " + usuario.toString());
        
        JwtDto jwtDto = new JwtDto(jwt,  usuario.getUsername(),usuario.getNombreCompleto(), usuario.getIdUsuario(), usuario.getAuthorities());
        log.info(">>> jwtDto >>> " + jwtDto.toString());
        
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario){
        HashMap<String, String> response = new HashMap<>();
        try {
            Usuario usuarioDb = usuarioService.buscaPorLogin(usuario.getLogin());
            if (usuarioDb != null) {
                response.put("estado", "0");
                response.put("mensaje", "El usuario ya existe");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            usuarioService.guardar(usuario);
            response.put("estado", "1");
            response.put("mensaje", "Usuario registrado con éxito");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put("estado", "-1");
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
