package hibernate.lesson4.dao;


import hibernate.lesson4.model.Hotel;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {

    public List<Hotel> findHoteByName(String name) {
        String hqlFindByName = "from Hotel where NAME = '" + name + "'";
        return findEntityBy(hqlFindByName);
    }

    public List<Hotel> findHotelByCity(String city) {
        String hqlFindByCity = "from Hotel where CITY = '" + city + "'";
        return findEntityBy(hqlFindByCity);
    }

    public Hotel save(Hotel hotel) {
        return saveEntity(hotel);
    }

    public Hotel update(Hotel hotel) {
        return updateEntity(hotel);
    }

    public void delete(long id) {
        deleteEntity(findById(id));
    }

    public Hotel findById(long id) {
        String hqlFindById = "from Hotel where id = " + id;
        return findEntityBy(hqlFindById).get(0);
    }


}
