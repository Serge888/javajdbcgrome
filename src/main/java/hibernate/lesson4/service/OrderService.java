package hibernate.lesson4.service;

import hibernate.lesson4.dao.OrderDAO;
import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Order;

public class OrderService {
    private OrderDAO orderDAO = InstanceFactory.instanceOrderDAO;

    public Order save(Order order) throws BadRequestException {
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
}
