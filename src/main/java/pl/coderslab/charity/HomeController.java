package pl.coderslab.charity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.DonationService;


@Controller
public class HomeController {

    private DonationService donationService;

    public HomeController(DonationService donationService) {
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        return "index";
    }


    @RequestMapping("/form")
    public String homeActionTEst(Model model){
        return "form";
    }


    @RequestMapping("/all")
    @ResponseBody
    public String allDonations(Model model){
        return ""+donationService.findAll();
    }


}
