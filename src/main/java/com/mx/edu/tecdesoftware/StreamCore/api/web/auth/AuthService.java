package com.mx.edu.tecdesoftware.StreamCore.api.web.auth;

import com.mx.edu.tecdesoftware.StreamCore.api.persistence.crud.UsuarioCrudRepository;
import com.mx.edu.tecdesoftware.StreamCore.api.persistence.entity.Usuario;
import com.mx.edu.tecdesoftware.StreamCore.api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(String email, String rawPassword) {
        Usuario usuario = usuarioCrudRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new BadCredentialsException("Correo o contraseña incorrectos"));

        if (usuario.getContrasena() == null || !passwordEncoder.matches(rawPassword, usuario.getContrasena())) {
            throw new BadCredentialsException("Correo o contraseña incorrectos");
        }

        return jwtUtil.generateToken(usuario.getCorreoElectronico());
    }
}