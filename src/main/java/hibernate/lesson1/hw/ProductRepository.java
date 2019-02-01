package hibernate.lesson1.hw;

import hibernate.lesson1.HibernateUtils;
import hibernate.lesson1.Product;
import org.hibernate.Session;

public class ProductRepository {
    Session session = new HibernateUtils().createSessionFactory().openSession();


    public void save(Product product) {
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        session.close();
    }


    void delete(long id) {
        session.getTransaction().begin();
        Product product = session.load(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }


    void update(Product product) {
        session.getTransaction().begin();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }

}
