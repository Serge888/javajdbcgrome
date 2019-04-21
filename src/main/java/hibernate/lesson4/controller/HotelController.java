package hibernate.lesson4.controller;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.service.HotelService;

import java.util.List;

public class HotelController {
    private HotelService hotelService = InstanceFactory.getInstanceHotelService();

    public List<Hotel> findHoteByName(String name) {
        return hotelService.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) {
        return hotelService.findHotelByCity(city);
    }

    public Hotel save(Hotel hotel) {

        try {
            hotelService.save(hotel);
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        return hotel;
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
