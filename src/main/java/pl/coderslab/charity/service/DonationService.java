package pl.coderslab.charity.service;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, UserService userService, UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    public List<Donation> findAll(){
        return donationRepository.findAll();
    }


    public int sumOfQuantities(){
        return donationRepository.sumOfQuantities();
    }

    public int sumOfDonations(){
        return donationRepository.sumOfDonations();
    }

    public int sumOfUserQuantities(@AuthenticationPrincipal CurrentUser currentUser){
        return donationRepository.sumOfUserQuantities(currentUser.getUser().getId());
    }

    public int sumOfUserDonations(@AuthenticationPrincipal CurrentUser currentUser){
        return donationRepository.sumOfUserDonations(currentUser.getUser().getId());
    }

    @Transactional
    public void saveDonation(Donation donation, CurrentUser customUser ){
        User entityUser = customUser.getUser();
        donation.setUser(entityUser);
        donationRepository.save(donation);
    }
}
