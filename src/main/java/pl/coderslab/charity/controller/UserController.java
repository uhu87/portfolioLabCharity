package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private DonationService donationService;
    private InstitutionService institutionService;

    public UserController(DonationService donationService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/userMenu")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUser currentUser) throws Exception {

        Optional<Integer> sum1 = donationService.sumOfUserQuantities(currentUser);

        model.addAttribute("sumOfQuantities", sum1.orElse(0));
        model.addAttribute("sumOfDonations", donationService.sumOfUserDonations(currentUser));
        return "index";
    }


    @GetMapping("/userDonations")
    public String userDonations(Model model, @AuthenticationPrincipal CurrentUser currentUser) {


        model.addAttribute("userDonations", donationService.donationList(currentUser.getUser()));
        return "userDonations";
    }



    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

}
