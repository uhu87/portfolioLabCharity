package pl.coderslab.charity.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.ConfirmationTokenRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.ConfirmationTokenService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class DonationController {

    private DonationService donationService;
    private DonationRepository donationRepository;
    private InstitutionService institutionService;
    private CategoryService categoryService;
    private UserRepository userRepository;
    private ConfirmationTokenService confirmationTokenService;

    public DonationController(DonationService donationService, DonationRepository donationRepository, InstitutionService institutionService, CategoryService categoryService, UserRepository userRepository, ConfirmationTokenService confirmationTokenService) {
        this.donationService = donationService;
        this.donationRepository = donationRepository;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/addDonation")
    public String addDonation(Model model, @AuthenticationPrincipal CurrentUser customUser){
        model.addAttribute("donation", new Donation());

        return "form";
    }
    @PostMapping("/addDonation")
    public String addDonationPost(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, @AuthenticationPrincipal CurrentUser customUser){

        if(result.hasErrors()){
            return "form";
        }

        donationService.saveDonation(donation, customUser);

        return "redirect:/formConfirmation";
    }


    @GetMapping("/formConfirmation")
    public String formConfirmation(){
        return "form-confirmation";
    }




    //___________________________________________________MODEL_ATTRIBUTES

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }


    @GetMapping("/test")
    @ResponseBody
    public String getCurrentUser(@AuthenticationPrincipal CurrentUser currentUser){


        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now(),
                currentUser.getUser()

        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return confirmationToken.getToken();
    }
}


