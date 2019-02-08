package hibernate.lesson4.controller;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.service.OrderService;

public class OrderController {
    OrderService  orderService = new OrderService();

        public Order save(Order order) throws BadRequestException {
            return orderService.save(order);
        }

        public Order update(Order order) {
            return orderService.update(order);
        }

        public void delete(long id) {
            orderService.delete(id);
        }

        public Order findById(long id) {
            return orderService.findById(id);
        }
}
