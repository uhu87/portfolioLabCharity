package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.charity.entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query(value = "select sum(quantity) from donation where user_id=:givenId", nativeQuery = true)
    int sumOfQuantities (@Param("givenId") Long id);


    @Query(value = "select count(*) from donation where user_id=:givenId", nativeQuery = true)
    int sumOfDonations (@Param("givenId") Long id);

}
