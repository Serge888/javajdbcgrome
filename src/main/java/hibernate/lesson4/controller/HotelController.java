package hibernate.lesson4.controller;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.service.HotelService;

import java.util.List;

public class HotelController {
    HotelService hotelService = new HotelService();

    public List<Hotel> findHoteByName(String name) {
        return hotelService.findHoteByName(name);
    }

    public List<Hotel> findHotelByCity(String city) {
        return hotelService.findHotelByCity(city);
    }

    public Hotel save(Hotel hotel) {
        return hotelService.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        return hotelService.update(hotel);
    }

    public void delete(long id) {
        hotelService.delete(id);
    }

    public Hotel findById(long id) {
        return hotelService.findById(id);
    }
}
