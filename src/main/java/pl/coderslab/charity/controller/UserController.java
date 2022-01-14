package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;
@Controller
public class UserController {

    private DonationService donationService;
    private InstitutionService institutionService;

    public UserController(DonationService donationService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/userMenu")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("sumOfQuantities", donationService.sumOfUserQuantities(currentUser));
        model.addAttribute("sumOfDonations", donationService.sumOfUserDonations(currentUser));
        return "index";
    }


    @GetMapping("/register")
    public String register(Model model){

        return "register";
    }


    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

}
