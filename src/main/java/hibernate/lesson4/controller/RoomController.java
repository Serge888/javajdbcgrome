package hibernate.lesson4.controller;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Filter;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.service.RoomService;

import java.util.Date;
import java.util.List;

public class RoomController {
    RoomService roomService = new RoomService();

    public List<Room> findRooms(Filter filter) throws Exception {
        return roomService.findRooms(filter);
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo)
            throws BadRequestException {
        roomService.bookRoom(roomId, userId, dateFrom, dateTo);
    }

    public void cancelReservation(long orderId) {
        roomService.cancelReservation(orderId);
    }


    public Room save(Room room) {
        return roomService.save(room);
    }

    public Room update(Room room) {
        return roomService.update(room);
    }

    public void delete(long id) {
        roomService.delete(id);
    }

    public Room findById(long id) {
        return roomService.findById(id);
    }

}
