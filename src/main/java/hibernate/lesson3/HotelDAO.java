package hibernate.lesson3;

public class HotelDAO extends GeneralDAO<Hotel> {
    GeneralDAO<Hotel> generalDAO = new GeneralDAO<>();

    public Hotel save(Hotel hotel) {
        return generalDAO.save(hotel);
    }

    public Hotel update(Hotel hotel) {
        return generalDAO.update(hotel);
    }

    public void delete(long id) {
        generalDAO.delete(findById(id));
    }

    public Hotel findById(long id) {
        String hqlFindById = "from Hotel where id = " + id;
        return generalDAO.findById(hqlFindById);
    }

}
