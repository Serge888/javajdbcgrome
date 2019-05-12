package hibernate.lesson4;

import hibernate.lesson4.controller.HotelController;
import hibernate.lesson4.controller.OrderController;
import hibernate.lesson4.controller.RoomController;
import hibernate.lesson4.controller.UserController;
import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static hibernate.lesson4.model.UserType.*;
@SuppressWarnings("Duplicates")
public class Demo {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();
        HotelController hotelController = new HotelController();
        RoomController roomController = new RoomController();
        OrderController orderController = new OrderController();

//        System.out.println(hotelController.findHoteByName("hotel2"));

//        User user3 = new User();
//        user3.setUserName("user5");
//        user3.setPassword("user5");
//        user3.setCountry("US");
//        user3.setUserType(ADMIN);
//
//        User user4 = new User();
//        user4.setUserName("user6");
//        user4.setPassword("user6");
//        user4.setCountry("UK");
//        user4.setUserType(USER);
//
//        userController.registerUser(user3);
//        userController.registerUser(user4);
//
//
//        System.out.println(userController.findById(2));

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
//        hotelController.save(hotel1);
//        hotelController.save(hotel2);

//        System.out.println(hotelController.findById(4).getRooms());

//        Room room1 = new Room();
//        room1.setHotel(hotelController.findById(4));
//        room1.setNumberOfGuests(2);
//        room1.setBreakfastIncluded(1);
//        room1.setPetsAllowed(0);
//        room1.setPrice(50.00);
//
//        Room room2 = new Room();
//        room2.setHotel(hotelController.findById(4));
//        room2.setNumberOfGuests(3);
//        room2.setBreakfastIncluded(0);
//        room2.setPetsAllowed(1);
//        room2.setPrice(60.00);
//
//        Room room3 = new Room();
//        room3.setHotel(hotelController.findById(4));
//        room3.setNumberOfGuests(2);
//        room3.setBreakfastIncluded(1);
//        room3.setPetsAllowed(1);
//        room3.setPrice(70.00);
//
//        roomController.save(room1);
//        roomController.save(room2);
//        roomController.save(room3);

//        System.out.println(roomController.findById(21));


//        roomController.bookRoom(21,2,new Date(), new Date(), 50.00);
//        roomController.cancelReservation(1);

        Filter filter = new Filter();
        filter.setCity("London");
        filter.setMaxPrice(60.00);
        filter.setBreakfastIncluded(1);

        System.out.println(roomController.findRooms(filter));

//
//        OrderDAO orderDAO = new OrderDAO();
//        Order order = new hibernate.lesson4.model.Order();
//        order.setDateFrom(new Date());
//        order.setRoom(roomController.findRooms(filter).get(0));
//        order.setMoneyPaid(100.89);
//        order.setUserOrdered(userController.findById(1L));
//        orderDAO.save(order);

//        System.out.println(hotelController.findHotelByCity("NY"));

    }
}
