package pl.coderslab.charity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DonationController {

    @GetMapping("/addDonation")
    public String addDonation(){
        return "form";
    }


    @GetMapping("/addDonation2")
    public String addDonation2(){
        return "donation/donationForm";
    }

    @GetMapping("/donation/addDonation3")
    public String addDonation3(){
        return "form";
    }

}
