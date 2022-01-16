package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.EmailServiceImpl;
import pl.coderslab.charity.entity.User;

@Controller
public class Sender {

    @Autowired
    private EmailServiceImpl service;

    @GetMapping("/send")
    @ResponseBody
    public String send() {

        service.sendSimpleMessage("lukaszsowa1@gmail.com", "test", "test");

        return "wyslane";
    }


}
