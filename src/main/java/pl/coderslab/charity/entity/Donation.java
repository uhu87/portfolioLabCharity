package pl.coderslab.charity.entity;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Musisz podać ilość worków")
    @Min(value=1, message = "Musisz przekazać co najmniej jeden worek")
    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donation_categories")
    @NotEmpty(message = "wybierz co najmniej jedną kategorię")
    private List<Category>categories;


    @ManyToOne
    @NotNull(message = "Musisz wybrać instytucję")
    private Institution institution;

    @NotEmpty(message = "Pole wymagane")
    @Size(max = 255, message = "Pole nie może przekraczać 255 znaków")
    private String street;

    @NotEmpty(message = "Pole wymagane")
    @Size(max = 255, message = "Pole nie może przekraczać 255 znaków")
    private String city;

    @NotEmpty(message = "Pole wymagane")
    @Size(max = 20, message = "Pole nie może przekraczać 20 znaków")
    private String zipCode;

    @NotNull(message = "Podaj datę odbioru")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Podaj datę w przyszłości")
    private LocalDate pickUpDate;

    @NotNull(message = "Podaj godzinę odbioru")
    private LocalTime pickUpTime;

    @Size(max = 255, message = "Pole nie może przekraczać 255 znaków")
    private String pickUpComment;

    @NotEmpty(message = "Pole wymagane")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Donation() {
    }

    public Donation(Long id, Integer quantity, List<Category> categories, Institution institution, String street, String city, String zipCode, LocalDate pickUpDate, LocalTime pickUpTime, String pickUpComment, String phone, User user) {
        this.id = id;
        this.quantity = quantity;
        this.categories = categories;
        this.institution = institution;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.pickUpComment = pickUpComment;
        this.phone = phone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
