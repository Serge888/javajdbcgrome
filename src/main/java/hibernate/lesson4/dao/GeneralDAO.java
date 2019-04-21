package hibernate.lesson4.dao;

import hibernate.lesson4.exception.BadRequestException;
import hibernate.lesson4.factory.InstanceFactory;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class GeneralDAO <T> {
    private SessionFactory sessionFactory = InstanceFactory.getInstanceSessionFactory();

    public T saveEntity(T t) throws BadRequestException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.save(t);

            transaction.commit();
            System.out.println("save is done");
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
            throw new BadRequestException("save " + t + "is failed");
        }
        return t;
    }

    public T updateEntity(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.update(t);

            transaction.commit();
            System.out.println("update is done");
        } catch (HibernateException e) {
            System.err.println("update is failed");
            System.err.println(e.getMessage());
        }
        return t;
    }


    public void deleteEntity(T t) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.delete(t);

            transaction.commit();
            System.out.println("delete is done");
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());
        }
    }

    List<T> findEntityBy(String hql) {
        List<T> foundObjects = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            foundObjects.addAll(session.createQuery(hql).list());

            transaction.commit();
            System.out.println("Search by id is done.");
        } catch (HibernateException e) {
            System.err.println("Search by id is failed.");
            System.err.println(e.getMessage());
        }
        return foundObjects;
    }

}
