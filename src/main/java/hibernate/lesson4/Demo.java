package hibernate.lesson4;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.dao.UserDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;

import java.util.Date;

import static hibernate.lesson4.model.UserType.*;

public class Demo {
    public static void main(String[] args) throws Exception {
        UserDAO userDAO = new UserDAO();
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();
        OrderDAO orderDAO = new OrderDAO();


//        User user3 = new User();
//        user3.setUserName("user3");
//        user3.setPassword("user3");
//        user3.setCountry("US");
//        user3.setUserType(ADMIN);
//
//        User user4 = new User();
//        user4.setUserName("user4");
//        user4.setPassword("user4");
//        user4.setCountry("UK");
//        user4.setUserType(USER);
//
//        userDAO.registerUser(user3);
//        userDAO.registerUser(user4);
//
//
//        System.out.println(userDAO.findById(2));

//        Hotel hotel1 = new Hotel();
//        hotel1.setName("hotel1");
//        hotel1.setCountry("US");
//        hotel1.setCity("NY");
//        hotel1.setStreet("Broadway");
//
//        Hotel hotel2 = new Hotel();
//        hotel2.setName("hotel2");
//        hotel2.setCountry("US");
//        hotel2.setCity("NY");
//        hotel2.setStreet("37");
//
//        hotelDAO.save(hotel1);
//        hotelDAO.save(hotel2);

//        System.out.println(hotelDAO.findById(4).getRooms());

//        Room room1 = new Room();
//        room1.setHotel(hotelDAO.findById(4));
//        room1.setNumberOfGuests(2);
//        room1.setBreakfastIncluded(1);
//        room1.setPetsAllowed(0);
//        room1.setPrice(50.00);
//
//        Room room2 = new Room();
//        room2.setHotel(hotelDAO.findById(4));
//        room2.setNumberOfGuests(3);
//        room2.setBreakfastIncluded(0);
//        room2.setPetsAllowed(1);
//        room2.setPrice(60.00);
//
//        Room room3 = new Room();
//        room3.setHotel(hotelDAO.findById(4));
//        room3.setNumberOfGuests(2);
//        room3.setBreakfastIncluded(1);
//        room3.setPetsAllowed(1);
//        room3.setPrice(70.00);
//
//        roomDAO.save(room1);
//        roomDAO.save(room2);
//        roomDAO.save(room3);

//        System.out.println(roomDAO.findById(21));


//        roomDAO.bookRoom(21,2,new Date(), new Date(), 50.00);
//        roomDAO.cancelReservation(1);

//        Filter filter = new Filter();
//        filter.setCity("NY");
//
//        System.out.println(roomDAO.findRooms(filter));

//        System.out.println(hotelDAO.findHotelByCity("NY"));

    }
}
