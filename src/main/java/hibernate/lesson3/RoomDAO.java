//package hibernate.lesson3;
//
//public class RoomDAO extends GeneralDAO<Room> {
//
//    public Room save(Room room) {
//        return saveEntity(room);
//    }
//
//    public Room update(Room room) {
//        return updateEntity(room);
//    }
//
//    public void delete(long id) {
//        deleteEntity(findById(id));
//    }
//
//    public Room findById(long id) {
//        String hqlFindById = "from Room where id = " + id;
//        return findEntityById(hqlFindById);
//    }
//}
