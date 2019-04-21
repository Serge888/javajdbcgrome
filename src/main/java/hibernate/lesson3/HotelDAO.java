//package hibernate.lesson3;
//
//public class HotelDAO extends GeneralDAO<Hotel> {
//
//    public Hotel save(Hotel hotel) {
//        return saveEntity(hotel);
//    }
//
//    public Hotel update(Hotel hotel) {
//        return updateEntity(hotel);
//    }
//
//    public void delete(long id) {
//        deleteEntity(findById(id));
//    }
//
//    public Hotel findById(long id) {
//        String hqlFindById = "from Hotel where id = " + id;
//        return findEntityById(hqlFindById);
//    }
//
//}
