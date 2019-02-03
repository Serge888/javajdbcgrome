package hibernate.lesson3;

public class RoomDAO extends GeneralDAO<Room> {
    GeneralDAO<Room> generalDAO = new GeneralDAO<>();

    public Room save(Room room) {
        return generalDAO.save(room);
    }

    public Room update(Room room) {
        return generalDAO.update(room);
    }

    public void delete(long id) {
        generalDAO.delete(findById(id));
    }

    public Room findById(long id) {
        String hqlFindById = "from Room where id = " + id;
        return generalDAO.findById(hqlFindById);
    }
}
