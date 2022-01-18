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

        Integer sum1 = donationService.sumOfUserQuantities(currentUser);

        if(sum1==null){
            sum1=0;
        }
        /*Optional<Integer> sumOptional= Optional.ofNullable(sum1);
        Optional<Integer> sumOptional2 = Optional.of(Integer.valueOf(sum1));*/


        model.addAttribute("sumOfQuantities", sum1);
        model.addAttribute("sumOfDonations", donationService.sumOfUserDonations(currentUser));
        return "index";
    }



    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

}
