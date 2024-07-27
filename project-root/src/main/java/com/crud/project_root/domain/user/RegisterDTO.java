package com.crud.project_root.domain.user;
public record RegisterDTO(String login, String email, String password, UserRole role) {
}