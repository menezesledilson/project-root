package com.crud.project_root.service;

import com.crud.project_root.domain.user.User;
import com.crud.project_root.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User createUser(User user){
        user.setId(null);
        return userRepository.save(user);
    }

}
