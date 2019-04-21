package hibernate.lesson2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoHql {
    private SessionFactory sessionFactory;

    // поиск продукта по id
    public Product findById(Long id) {
        String hqlFindById = "from Product where id = " + id;
        return findBy(hqlFindById).get(0);
    }

    // поиск продуктов по имени
    public List<Product> findByName(String name) {
        String hqlFindByName = "from Product where name = " + name;
        return findBy(hqlFindByName);
    }

    // поиск продуктов, которые в своем имени содержать слово name
    public List<Product> findByContainedName(String name) {
        String hqlFindByContainedName = "from Product where name like %" + name + "%";
        return findBy(hqlFindByContainedName);
    }

    // поиск продуктов по вилке цен price+-delta включительно
    public List<Product> findByPrice(int price, int delta) {
        String hqlFindByPrice = "from Product where price between " + (price - delta) + " and " + (price + delta);
        return findBy(hqlFindByPrice);
    }

    // поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
    public List<Product> findByNameSortedAsc(String name) {
        String hqlFindByNameSortedAsc = "from Product where name = " + name + " order by name asc";
        return findBy(hqlFindByNameSortedAsc);
    }

    // поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
    public List<Product> findByNameSortedDesc(String name) {
        String hqlFindByNameSortedDesc = "from Product where name = " + name + " order by name desc";
        return findBy(hqlFindByNameSortedDesc);
    }

    // поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен
    public List<Product> findByPriceSortedDesc(int price, int delta) {
        String hqlFindByPriceSortedDesc = "from Product where price between "
                + (price - delta) + " and " + (price + delta) + " order by price desc";
        return findBy(hqlFindByPriceSortedDesc);
    }

    private SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    private List<Product> findBy(String hql) {
        List<Product> foundProducts = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            Query<Product> query = session.createQuery(hql, Product.class);
            foundProducts.addAll(query.list());

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
        return foundProducts;
    }


}
