package com.crud.project_root.controllers;

import com.crud.project_root.domain.User;
import com.crud.project_root.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path ="/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>>postUser(@RequestBody User user){
        User userSalve = userService.createUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSalve.getId())
                .toUri();
        // Cria um mapa para a resposta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User created successfully.");
        response.put("createdUser", userSalve);

        return ResponseEntity.created(uri).body(response);

    }
}
