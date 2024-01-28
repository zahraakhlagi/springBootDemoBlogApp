package com.example.springbootblogwebapp.security;

import com.example.springbootblogwebapp.entity.User;
import com.example.springbootblogwebapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //we create a our own userdetailsservice , create a user object from entity
        User user= userRepository.findByEmail(email);
        //check the user is null or not
        if(user!=null){

            //now we create a object from user in userdetails
            org.springframework.security.core.userdetails.User authenticatedUser=
                    new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            user.getRoles().stream()
                                    //convert list of roles to list of simple granted authority object
                                    .map((role)->new SimpleGrantedAuthority(role.getName()))
                                    .collect(Collectors.toList())

                    );
            return authenticatedUser;
        }else {
            throw new UsernameNotFoundException("Invalid username and password");
        }

    }

}
