package pl.coderslab.charity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

@Controller
public class LoginController {


    public UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser(@RequestParam String username) {
        User user = new User();
        user.setUsername(username);
        user.setPassword("user");
        user.setEmail("lukaszsowa1@email.com");
        userService.saveUser(user);
        return "zalozone konto";
    }







}
