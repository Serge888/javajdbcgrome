package hibernate.lesson4.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ROOM")
public class Room {
    private long id;
    private Integer numberOfGuests;
    private Double price;
    private Integer breakfastIncluded;
    private Integer petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;


    @Id
    @SequenceGenerator(name = "RM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RM_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "NUMBER_OF_GUESTS")
    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "BREAKFAST_INCLUDED")
    public Integer getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(Integer breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    @Column(name = "PETS_ALLOWED")
    public Integer getPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(Integer petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    @Column(name = "DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    @ManyToOne (optional=false, fetch=FetchType.EAGER)
    @JoinColumn (name="HOTEL_ID", nullable=false)
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numberOfGuests == room.numberOfGuests &&
                Double.compare(room.price, price) == 0 &&
                breakfastIncluded == room.breakfastIncluded &&
                petsAllowed == room.petsAllowed &&
                Objects.equals(dateAvailableFrom, room.dateAvailableFrom) &&
                hotel.equals(room.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfGuests, price, breakfastIncluded, petsAllowed, dateAvailableFrom, hotel);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hotel=" + hotel +
                '}';
    }
}
