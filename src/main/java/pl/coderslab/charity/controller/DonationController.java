package pl.coderslab.charity.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.EmailServiceImpl;
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
import java.util.stream.Collectors;

@Controller
public class DonationController {

    private DonationService donationService;
    private DonationRepository donationRepository;
    private InstitutionService institutionService;
    private CategoryService categoryService;
    private UserRepository userRepository;
    private ConfirmationTokenService confirmationTokenService;
    @Autowired
    private EmailServiceImpl service;


    public DonationController(DonationService donationService,
                              DonationRepository donationRepository,
                              InstitutionService institutionService,
                              CategoryService categoryService,
                              UserRepository userRepository,
                              ConfirmationTokenService confirmationTokenService, EmailServiceImpl service) {
        this.donationService = donationService;
        this.donationRepository = donationRepository;
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.service = service;
    }

    @GetMapping("/addDonation")
    public String addDonation(Model model, @AuthenticationPrincipal CurrentUser customUser){
        model.addAttribute("donation", new Donation());

        return "form";
    }
    @PostMapping("/addDonation")
    public String addDonationPost(@ModelAttribute("donation") @Valid Donation donation, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){

        if(result.hasErrors()){
            return "form";
        }

        service.sendSummaryMessage(currentUser.getUser().getEmail(),
                summarySubject(donation, currentUser),
                summaryContent(donation, currentUser)
                );
        donationService.saveDonation(donation, currentUser);

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


    public String summarySubject (Donation donation, CurrentUser currentUser){
        return "CharityApp: podsumowanie darowizny od użytkownika: "+
                currentUser.getUsername()+ " dla fundacji " +donation.getInstitution().getName();
    }

    public String summaryContent (Donation donation, CurrentUser currentUser){
        List <Category> donationCategories = donation.getCategories();

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h3>\n" +
                "    Witaj " +currentUser.getUsername()+ " !</br>\n" +
                "</h3>\n" +
                "\n" +
                "poniżej znajduje się podsumowanie Twojej darowizny. </br>\n" +
                "<li>Ilość worków: " +donation.getQuantity()+ "</li>\n" +
                "<li>Kategorie darów: " + donationCategories.stream()
                .map(c->c.toString()+", ")
                .collect(Collectors.joining())
                +"</li>\n"
                +"<li>Adres: " +donation.getStreet()+ ", "+donation.getCity()+
                ", "+donation.getZipCode()+"</li>\n" +
                "<li>Uwagi dla kuriera: "+donation.getPickUpComment()+"</li>\n" +
                "<li>Data odbioru: "+donation.getPickUpDate()+"</li>\n" +
                "<li>Godzina odbioru: "+donation.getPickUpTime()+"</li>\n" +
                "<li>Telefon: "+donation.getPhone()+"</li>\n" +
                "<li>Fundacja: "+donation.getInstitution()+"</li>\n" +
                "\n" +
                "<h4>Dziękujemy,</br>\n" +
                "CharityApp</h4>\n" +
                "</body>\n" +
                "</html>";
    }


}


