package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotations.Public;
import com.example.demo.config.JwtUtil;
import com.example.demo.dto.AuthDTO;
import com.example.demo.entity.Pessoa;
import com.example.demo.repository.PessoaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @Public
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO dto) {

        String email = dto.getEmail();
        String senha = dto.getSenha(); // Ainda em texto puro

        Optional<Pessoa> pessoaOpt = pessoaRepository.findByEmail(email);

        if (pessoaOpt.isPresent() && passwordEncoder.matches(senha, pessoaOpt.get().getSenha())) {
            // DEU CERTO SENHA IGUAL

            String nivelAcesso = pessoaOpt.get().getNivelAcesso().toString();

            String token = jwtUtil.generateToken(email, nivelAcesso);

            return ResponseEntity.ok(Map.of(
                    "token", token, "tipo", nivelAcesso));
        }

        return ResponseEntity.status(401).body("Credenciais inválidas!");
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {

        if (authentication == null) {
            return ResponseEntity.status(401).body("Não autenticado");
        }

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(authority -> authority.substring(5))
                .findFirst()
                .orElse("PADRAO");

        return ResponseEntity.ok().body(
                Map.of(
                        "username", authentication.getName(),
                        "role", role
                ));
    }
}
