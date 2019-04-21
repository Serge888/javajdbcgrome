package hibernate.lesson4.controller;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.service.OrderService;

public class OrderController {
    private OrderService  orderService = InstanceFactory.getInstanceOrderService();

        public Order save(Order order) {
            try {
                orderService.save(order);
            } catch (BadRequestException e) {
                e.getMessage();
            }
            return order;
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
