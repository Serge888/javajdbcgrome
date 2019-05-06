package hibernate.lesson4.service;

import hibernate.lesson4.dao.RoomDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;

import java.util.*;

public class RoomService {
    private RoomDAO roomDAO = InstanceFactory.instanceRoomDAO;

    public List<Room> findRooms(Filter filter) throws Exception {
        return roomDAO.findRooms(filter);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException {
        roomDAO.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long orderId) {
        roomDAO.cancelReservation(orderId);
    }


    public Room save(Room room) throws BadRequestException {
        return roomDAO.save(room);
    }

    public Room update(Room room) {
        return roomDAO.update(room);
    }

    public void delete(long id) {
        roomDAO.delete(id);
    }

    public Room findById(long id) {
        return roomDAO.findById(id);
    }

}
