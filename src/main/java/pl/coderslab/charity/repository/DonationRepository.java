package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(quantity) from donation", nativeQuery = true)
    int sumOfQuantities ();


    @Query(value = "select count(*) from donation", nativeQuery = true)
    int sumOfDonations ();



    @Query(value = "select sum(quantity) from donation where User=:userId", nativeQuery = true)
    Optional<Integer> sumOfUserQuantities (@Param("userId") Long id);


    @Query(value = "select count(*) from donation where User=:userId", nativeQuery = true)
    Integer sumOfUserDonations (@Param("userId") Long id);

    List<Donation> findAllByUser(User user);
    List<Donation> findAllByUserAndAndPickUpDateGreaterThan(User user, LocalDateTime localDateTime);

}


