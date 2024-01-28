package com.example.springbootblogwebapp.controller;

import com.example.springbootblogwebapp.dto.RegistrationDto;
import com.example.springbootblogwebapp.entity.User;
import com.example.springbootblogwebapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    //handler method to handle a login page request
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

//method to registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        RegistrationDto user= new RegistrationDto();
        model.addAttribute("user",user);
        return "register";

    }

    //handler method to handle user registration from submit request
     @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user,
                           BindingResult result,
                           Model model){
        //check the unique email
         User existingUser= userService.findByEmail(user.getEmail());
         if (existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()){
             result.rejectValue("email", null, "there is already a user with the same email");

         }
        //if there is error , shows in url
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";

     }
}
