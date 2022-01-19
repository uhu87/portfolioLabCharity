package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.EmailServiceImpl;
import pl.coderslab.charity.UserServiceImpl;
import pl.coderslab.charity.entity.ConfirmationToken;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.ConfirmationTokenRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.ConfirmationTokenService;
import pl.coderslab.charity.service.RegistrationService;
import pl.coderslab.charity.service.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
public class ForgottenPasswordController {

    @Autowired
    private EmailServiceImpl service;
    private UserService userService;
    private UserRepository userRepository;
    private ConfirmationTokenService confirmationTokenService;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private UserServiceImpl userServiceImpl;
    private RegistrationService registrationService;

    public ForgottenPasswordController(EmailServiceImpl service, UserService userService, UserRepository userRepository, ConfirmationTokenService confirmationTokenService, ConfirmationTokenRepository confirmationTokenRepository, UserServiceImpl userServiceImpl, RegistrationService registrationService) {
        this.service = service;
        this.userService = userService;
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userServiceImpl = userServiceImpl;
        this.registrationService = registrationService;
    }

    @GetMapping("/newPasswordSent")
    public String newPasswordSent() {

        return "newPasswordSent";
    }

    @PostMapping("/newPasswordSent")
    @ResponseBody
    public String newPasswordSentPost(@RequestParam String email) {



        if(userRepository.findByEmail(email)==null){
            return "nie ma takiego maila";
        }

        User user = userRepository.findByEmail(email);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(75),
                userService.findByUserName(user.getUsername()));
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        service.sendSimpleMessage(user.getEmail(), "Zresetuj hasło na CharityApp",

                "http://localhost:8080/changePassword?token="+confirmationToken.getToken());


            return "Wysłano link resetujący hasło";
    }


    @GetMapping("/changePassword")
    public String changePassword(@RequestParam String token, Model model){


        registrationService.confirmPasswordToken(token, model);

        return "newPasswordForm";
    }
    @PostMapping("/changePassword")
    @ResponseBody
    public String changePasswordPost(@RequestParam Long id, @RequestParam String password ) {

    User updatedUser = userRepository.findById(id);

    updatedUser.setPassword(password);
    userServiceImpl.updateUserPassword(updatedUser);

    return"Haslo zmienione";
    }



}
