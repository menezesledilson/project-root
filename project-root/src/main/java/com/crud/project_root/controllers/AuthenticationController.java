package com.crud.project_root.controllers;

import com.crud.project_root.domain.user.AuthenticationDTO;
import com.crud.project_root.domain.user.RegisterDTO;
import com.crud.project_root.domain.user.User;
import com.crud.project_root.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetar PasswordEncoder

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            // Retornar sucesso com uma mensagem ou token, se aplicável
            return ResponseEntity.ok().body("Login successful"); // Adicione uma resposta de sucesso apropriada

        } catch (Exception e) {
            // Retornar mensagem de erro se falha na autenticação
            return ResponseEntity.status(401).body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        // Verificar se o login já existe
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        // Criptografar a senha
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        // Salvar o novo usuário no banco de dados
        this.repository.save(newUser);

        // Criar URI para o novo recurso
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        // Retornar sucesso com URI do novo recurso
        return ResponseEntity.created(uri).body("User registered successfully.");
    }
}
