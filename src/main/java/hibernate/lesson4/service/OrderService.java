package hibernate.lesson4.service;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;

import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO = InstanceFactory.instanceOrderDAO;

    public Order save(Order order) throws BadRequestException {
        orderValidation(order);
        return orderDAO.save(order);
    }

    public Order update(Order order) {
        return orderDAO.update(order);
    }

    public void delete(long id) {
        orderDAO.delete(id);
    }

    public Order findById(long id) {
        return orderDAO.findById(id);
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
