package hibernate.lesson3;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GeneralDAO <T> {
    private SessionFactory sessionFactory;

    public T save(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.save(t);

            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.out.println("Save is done");
        return t;
    }

    public T update(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.update(t);

            transaction.commit();

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
        System.out.println("update is done");
        return t;
    }


    public void delete(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            session.delete(t);

            transaction.commit();

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
        System.out.println("delete is done");
    }


    public T findById(String hql) {
        List<T> foundObjects = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            Query<T> query = session.createQuery(hql);

            foundObjects.addAll(query.list());

            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Search by key is failed. HQL");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.out.println("Search by key is done. HQL");
        return foundObjects.get(0);
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}
