package hibernate.lesson3;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();

//        Hotel hotel1 = new Hotel();
//        hotel1.setName("hotel1");
//        hotel1.setCountry("US");
//        hotel1.setCity("NY");
//        hotel1.setStreet("Broadway");
//
//        Hotel hotel2 = new Hotel();
//        hotel2.setName("hotel2");
//        hotel2.setCountry("UK");
//        hotel2.setCity("London");
//        hotel2.setStreet("Main str");


        Hotel hotel1 = hotelDAO.findById(3);
        hotel1.setName("updated name");
        hotelDAO.update(hotel1);

        Room room1 = new Room();
        room1.setBreakfastIncluded(1);
        room1.setDateAvailableFrom(new Date());
        room1.setNumberOfGuests(1);
        room1.setPrice(100.00);
        room1.setPetsAllowed(1);
        room1.setHotel(hotel1);

        Room room = roomDAO.findById(2);
        room.setPrice(300.00);
        roomDAO.update(room);

        roomDAO.delete(1);

        Room room2 = new Room();
        room2.setBreakfastIncluded(0);
        room2.setDateAvailableFrom(new Date());
        room2.setNumberOfGuests(2);
        room2.setPrice(200.00);
        room2.setPetsAllowed(1);
        room2.setHotel(hotel1);


//        hotelDAO.saveEntity(hotel1);
//        hotelDAO.saveEntity(hotel2);
        roomDAO.save(room1);
        roomDAO.save(room2);

    }
}
