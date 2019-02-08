package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;

import java.util.*;

public class OrderDAO extends GeneralDAO<Order> {

    public Order save(Order order) throws BadRequestException {
        RoomDAO roomDAO = new RoomDAO();
        orderValidation(order);
        Room room = order.getRoom();
        room.setDateAvailableFrom(order.getDateTo());
        roomDAO.update(room);
        return saveEntity(order);
    }


    public Order update(Order order) {
        return updateEntity(order);
    }

    public void delete(long id) {
        Order order = findById(id);
        Room room = order.getRoom();
        room.setDateAvailableFrom(order.getDateFrom());
        deleteEntity(order);
    }

    public Order findById(long id) {
        String hqlFindById = "from Order where id = " + id;
        return findEntityBy(hqlFindById).get(0);
    }

    private void orderValidation(Order order) throws BadRequestException {
        RoomDAO roomDAO = new RoomDAO();
        long roomId = order.getRoom().getId();
        Room room = roomDAO.findById(roomId);
        if (room == null) {
            throw new BadRequestException("Room with id " + roomId + " doesn't exists.");
        }
        Date availableDate = room.getDateAvailableFrom();
        if (availableDate != null && availableDate.after(order.getDateFrom())) {
            throw new BadRequestException("Room with id " + roomId + " is busy in these dates.");
        }
    }

}