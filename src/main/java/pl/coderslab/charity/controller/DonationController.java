package pl.coderslab.charity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class DonationController {

    private DonationService donationService;
    private DonationRepository donationRepository;
    private InstitutionService institutionService;
    private CategoryService categoryService;

    public DonationController(DonationService donationService, DonationRepository donationRepository, InstitutionService institutionService, CategoryService categoryService) {
        this.donationService = donationService;
        this.donationRepository = donationRepository;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/addDonation")
    public String addDonation(Model model){
        model.addAttribute("donation", new Donation());

        return "form";
    }
    @PostMapping("/addDonation")
    public String addDonationPost(@ModelAttribute("donation") Donation donation){

        donationRepository.save(donation);
        return "redirect:/addDonation";
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
}


/*    @GetMapping("/addDonation2")
    public String addDonation2(){
        return "donation/donationForm";
    }

    @GetMapping("/donation/addDonation3")
    public String addDonation3(){
        return "form";
    }*/
