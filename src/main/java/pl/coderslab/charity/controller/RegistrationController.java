package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.EmailServiceImpl;
import pl.coderslab.charity.entity.ConfirmationToken;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.ConfirmationTokenService;
import pl.coderslab.charity.service.RegistrationService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class RegistrationController {
    @Autowired
    private EmailServiceImpl service;
    private UserService userService;
    private ConfirmationTokenService confirmationTokenService;
    private RegistrationService registrationService;

    public RegistrationController(EmailServiceImpl service, UserService userService, ConfirmationTokenService confirmationTokenService, RegistrationService registrationService) {
        this.service = service;
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.registrationService = registrationService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(User user) {

        userService.saveUser(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(75),
                userService.findByUserName(user.getUsername()));
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        service.sendSimpleMessage(user.getEmail(), "Potwierdź rejestracje na CharityApp",

                "http://localhost:8080/confirmRegistration?token="+confirmationToken.getToken());
        return "redirect:/EmailSentInfo";
    }


    @GetMapping("/EmailSentInfo")
    @ResponseBody
    public String EmailSentInfo() {

        return "Wyslalismy linkt aktywacyjny na podanego maila";
    }



    @GetMapping("/confirmRegistration")
    @ResponseBody
    public String confirmRegistration(@RequestParam String token){

        registrationService.confirmToken(token);

        return "Uzytkownik zarejestrowany";
    }

}
