package hibernate.lesson4.dao;


import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Hotel;
import org.hibernate.Session;

import java.util.List;

public class HotelDAO extends GeneralDAO<Hotel> {
    private String hqlFindByName = "from Hotel where name = ";
    private String hqlFindByCity = "from Hotel where CITY = '";
    private String hqlFindById = "from Hotel where id = ";


    public List<Hotel> findHotelByName(String name) {
        return findEntityByHql(hqlFindByName + "'" + name + "'");
    }

    public List<Hotel> findHotelByCity(String city) {
        return findEntityByHql(hqlFindByCity + "'" +  city + "'");
    }

    public Hotel save(Hotel hotel) throws BadRequestException {
        return saveEntity(hotel);
    }

    public Hotel update(Hotel hotel) {
        return updateEntity(hotel);
    }

    public void delete(long id) {
        deleteEntity(findById(id));
    }

    public Hotel findById(long id) {
        try (Session session = InstanceFactory.sessionFactory.openSession()) {
            return session.get(Hotel.class, id);
        }
    }


}
