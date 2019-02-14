package hibernate.lesson4.model;

import java.util.Date;

public class Filter {
    // room
    private Integer numberOfGuests;
    private Double maxPrice;
    private Double minPrice;
    private Integer breakfastIncluded;
    private Integer petsAllowed;
    private Date dateAvailableFrom;
    //hotel
    private String name;
    private String country;
    private String city;

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Integer isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(Integer breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public Integer isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(Integer petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
