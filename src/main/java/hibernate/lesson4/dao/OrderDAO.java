package hibernate.lesson4.dao;

import hibernate.lesson4.exception.InternalServerException;
import hibernate.lesson4.factory.InstanceFactory;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class OrderDAO extends GeneralDAO<Order> {

    public Order findById(long id) {
        try (Session session = InstanceFactory.sessionFactory.openSession()) {
            return session.get(Order.class, id);
        }
    }

    public Order save(Order order) {
        Transaction transaction = null;
        try (Session session = InstanceFactory.sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(order);
            Room room = order.getRoom();
            room.setDateAvailableFrom(room.getDateAvailableFrom());
            room.setDateAvailableFrom(order.getDateTo());
            session.update(room);
            transaction.commit();
        } catch (InternalServerException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InternalServerException("Order  with id " + order.getId() + " save failed.");
        }
        return order;
    }


    public Order update(Order order) {
        return updateEntity(order);
    }

    public void delete(long id) {
        Transaction transaction = null;
        Order order = findById(id);
        Room room = order.getRoom();
        room.setDateAvailableFrom(order.getDateFrom());
        try (Session session = InstanceFactory.sessionFactory.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(order);
            session.update(room);
            transaction.commit();
        } catch (InternalServerException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new InternalServerException("Order  with id " + order.getId() + " delete failed.");
        }
    }


}