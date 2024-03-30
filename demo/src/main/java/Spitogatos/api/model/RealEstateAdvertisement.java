package Spitogatos.api.model;

import jakarta.validation.constraints.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.validation.constraints.*;
import javax.persistence.*;
@Entity
@Table(name = "real_estate_advertisement")
public class RealEstateAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Area is required")
    @Pattern(regexp = "(Athens|Thessaloniki|Patras|Heraklion)", message = "Invalid area")
    private String area;

    @NotNull(message = "Price is required")
    @Min(value = 50, message = "Price must be at least 50")
    private Integer price;

    @NotBlank(message = "Availability is required")
    @Pattern(regexp = "(rent|sale)", message = "Invalid availability")
    private String availability;

    @NotNull(message = "Area size is required")
    @Min(value = 1, message = "Area size must be at least 1")
    private Integer areaSize;


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getAreaSize() {
        return areaSize;
    }

    public void setAreaSize(int areaSize) {
        this.areaSize = areaSize;
    }
}
