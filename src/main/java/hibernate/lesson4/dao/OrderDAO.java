package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.exception.InternalServerException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;

import java.util.*;

public class OrderDAO extends GeneralDAO<Order> {
    private String hqlFindById = "from Order where id = ";

    public Order save(Order order) throws BadRequestException {
        Room roomRollback = new Room();
        try {
            orderValidation(order);
            Room room = order.getRoom();
            room.setDateAvailableFrom(room.getDateAvailableFrom());
            room.setDateAvailableFrom(order.getDateTo());
            saveEntity(order);
            InstanceFactory.instanceRoomDAO.update(room);
        } catch (Exception e) {
            delete(order.getId());
            InstanceFactory.instanceRoomDAO.update(roomRollback);
            throw new BadRequestException("Order  with id " + order.getId() + " save failed.");
        }
        return order;
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
        return findEntityBy(hqlFindById + id).get(0);
    }

    private void orderValidation(Order order) throws BadRequestException {
        long roomId = order.getRoom().getId();
        Room room = InstanceFactory.instanceRoomDAO.findById(roomId);
        if (room == null) {
            throw new BadRequestException("Room with id " + roomId + " doesn't exists.");
        }
        Date availableDate = room.getDateAvailableFrom();
        if (availableDate != null && availableDate.after(order.getDateFrom())) {
            throw new BadRequestException("Room with id " + roomId + " is busy in these dates.");
        }
    }

}