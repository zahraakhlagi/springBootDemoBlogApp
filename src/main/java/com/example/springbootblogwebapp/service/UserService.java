package com.example.springbootblogwebapp.service;

import com.example.springbootblogwebapp.dto.RegistrationDto;
import com.example.springbootblogwebapp.entity.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    User findByEmail(String email);
}
