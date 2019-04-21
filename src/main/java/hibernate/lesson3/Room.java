//package hibernate.lesson3;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "ROOM")
//public class Room {
//    private long id;
//    private int numberOfGuests;
//    private double price;
//    private int breakfastIncluded;    // (1 или 0)
//    private int petsAllowed;          // (1 или 0)
//    private Date dateAvailableFrom;
//    private Hotel hotel;
//
//
//    @Id
//    @SequenceGenerator(name = "RM_SEQ", sequenceName = "ROOM_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RM_SEQ")
//    @Column(name = "ID")
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    @Column(name = "NUMBER_OF_GUESTS")
//    public int getNumberOfGuests() {
//        return numberOfGuests;
//    }
//
//    public void setNumberOfGuests(int numberOfGuests) {
//        this.numberOfGuests = numberOfGuests;
//    }
//
//    @Column(name = "PRICE")
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    @Column(name = "BREAKFAST_INCLUDED")
//    public int getBreakfastIncluded() {
//        return breakfastIncluded;
//    }
//
//    public void setBreakfastIncluded(int breakfastIncluded) {
//        this.breakfastIncluded = breakfastIncluded;
//    }
//
//    @Column(name = "PETS_ALLOWED")
//    public int getPetsAllowed() {
//        return petsAllowed;
//    }
//
//    public void setPetsAllowed(int petsAllowed) {
//        this.petsAllowed = petsAllowed;
//    }
//
//    @Column(name = "DATE_AVAILABLE_FROM")
//    public Date getDateAvailableFrom() {
//        return dateAvailableFrom;
//    }
//
//    public void setDateAvailableFrom(Date dateAvailableFrom) {
//        this.dateAvailableFrom = dateAvailableFrom;
//    }
//
//    @OneToOne (optional=false, fetch=FetchType.LAZY)
//    @JoinColumn (name="HOTEL_ID")
//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
//
//    @Override
//    public String toString() {
//        return "Room{" +
//                "id=" + id +
//                ", numberOfGuests=" + numberOfGuests +
//                ", price=" + price +
//                ", breakfastIncluded=" + breakfastIncluded +
//                ", petsAllowed=" + petsAllowed +
//                ", dateAvailableFrom=" + dateAvailableFrom +
//                ", hotel=" + hotel +
//                '}';
//    }
//}
