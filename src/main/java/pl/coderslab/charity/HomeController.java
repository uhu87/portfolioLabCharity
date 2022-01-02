package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;

import java.util.List;


@Controller
public class HomeController {

    private DonationService donationService;
    private InstitutionService institutionService;

    public HomeController(DonationService donationService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.institutionService = institutionService;
    }

    @GetMapping("/index")
    public String homeAction(Model model){
        model.addAttribute("sumOfQuantities", donationService.sumOfQuantities());
        model.addAttribute("sumOfDonations", donationService.sumOfDonations());
        return "index";
    }


    @GetMapping("/form")
    public String homeActionTEst(Model model){
        return "form";
    }


    @RequestMapping("/all")
    @ResponseBody
    public String allDonations(Model model){
        return ""+donationService.findAll();
    }


    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

}
