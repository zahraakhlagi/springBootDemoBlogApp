package com.example.springbootblogwebapp.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SecurityUtils {
    public static org.springframework.security.core.userdetails.User getCurrentUser(){
        //give info about th who was wrote the post
        Object principle= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principle instanceof org.springframework.security.core.userdetails.User){
            return (User) principle;
        }
        return null;
    }

    public static String getRole(){
        User user= getCurrentUser();
        Collection<GrantedAuthority> authorities=user.getAuthorities();
        for(GrantedAuthority authority:authorities){
            return authority.getAuthority();
        }
        return null;
    }

}
