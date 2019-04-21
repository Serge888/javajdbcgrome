package hibernate.lesson4.service;

import hibernate.lesson4.dao.HotelDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Hotel;

import java.util.List;

public class HotelService {
    private HotelDAO hotelDAO = InstanceFactory.getInstanceHotelDAO();

    public List<Hotel> findHotelByName(String name) {
        return hotelDAO.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) {
        return hotelDAO.findHotelByCity(city);
    }

    public Hotel save(Hotel hotel) throws BadRequestException {
        return hotelDAO.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        return hotelDAO.update(hotel);
    }

    public void delete(long id) {
        hotelDAO.delete(id);
    }

    public Hotel findById(long id) {
        return hotelDAO.findById(id);
    }
}
