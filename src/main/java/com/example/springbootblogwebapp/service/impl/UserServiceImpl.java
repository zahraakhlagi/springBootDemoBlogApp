package com.example.springbootblogwebapp.service.impl;

import com.example.springbootblogwebapp.dto.RegistrationDto;
import com.example.springbootblogwebapp.entity.Role;
import com.example.springbootblogwebapp.entity.User;
import com.example.springbootblogwebapp.repository.RoleRepository;
import com.example.springbootblogwebapp.repository.UserRepository;
import com.example.springbootblogwebapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(RegistrationDto registrationDto) {
        //create a user object
        User user= new User();
        //taket a data from registrationDto and pass to user object
        user.setName(registrationDto.getFirstName()+ " "+ registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        //give role to the user
        //we already saved a two role in database , "ROLE_GUEST" AND "ROLE_ADMIN"
        Role role= roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
