package hibernate.lesson3;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GeneralDAO <T> {
    private SessionFactory sessionFactory;

    public T saveEntity(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(t);

            transaction.commit();
            System.out.println("save is done");
        } catch (HibernateException e) {
            System.err.println("save is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return t;
    }

    public T updateEntity(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(t);

            transaction.commit();
            System.out.println("update is done");
        } catch (HibernateException e) {
            System.err.println("update is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return t;
    }


    public void deleteEntity(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(t);

            transaction.commit();
            System.out.println("delete is done");
        } catch (HibernateException e) {
            System.err.println("delete is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    public T findEntityById(String hql) {
        List<T> foundObjects = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            Query query = session.createQuery(hql);

            foundObjects.addAll(query.list());

            transaction.commit();
            System.out.println("Search by id is done.");
        } catch (HibernateException e) {
            System.err.println("Search by id is failed.");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return foundObjects.get(0);
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
