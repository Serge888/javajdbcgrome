package hibernate.lesson2;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoSql {
    private SessionFactory sessionFactory;
    private String sqlFindById = "SELECT * FROM PRODUCT WHERE ID = ?";
    private String sqlFindByName = "SELECT * FROM PRODUCT WHERE NAME = ?";
    private String sqlFindByContainedName = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
    private String sqlFindByPrice = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
    private String sqlFindByNameSortedAsc = "SELECT * FROM PRODUCT WHERE NAME = ? ORDER BY NAME ASC";
    private String sqlFindByNameSortedDesc = "SELECT * FROM PRODUCT WHERE NAME = ? ORDER BY NAME DESC";
    private String sqlFindByPriceSortedDesc = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ? ORDER BY PRICE DESC";

    // поиск продукта по id
    public Product findById(Long id) {
        return findBy(sqlFindById, Long.toString(id), null).get(0);
    }

    // поиск продуктов по имени
    public List<Product> findByName(String name) {
        return findBy(sqlFindByName, name, null);
    }

    // поиск продуктов, которые в своем имени содержать слово name
    public List<Product> findByContainedName(String name) {
        return findBy(sqlFindByContainedName, "%" + name + "%", null);
    }

    // поиск продуктов по вилке цен price+-delta включительно
    public List<Product> findByPrice(int price, int delta) {
        int minPrice = price - delta;
        int maxPrice = price + delta;
        return findBy(sqlFindByPrice, Integer.toString(minPrice), Integer.toString(maxPrice));
    }

    // поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
    public List<Product> findByNameSortedAsc(String name) {
        return findBy(sqlFindByNameSortedAsc, name, null);
    }

    // поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
    public List<Product> findByNameSortedDesc(String name) {
        return findBy(sqlFindByNameSortedDesc, name, null);
    }

    // поиск продуктов по вилке цен price+-delta включительно, результат отсортирован по убыванию цен
    public List<Product> findByPriceSortedDesc(int price, int delta) {
        int minPrice = price - delta;
        int maxPrice = price + delta;
        return findBy(sqlFindByPriceSortedDesc, Integer.toString(minPrice), Integer.toString(maxPrice));
    }

    private SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }


    private List<Product> findBy(String sql, String parameter1, String parameter2) {
        List<Product> foundProducts = new ArrayList<>();
        Session session = null;
        Transaction transaction = null;
        try {
            session = createSessionFactory().openSession();
            transaction = session.getTransaction();
            transaction.begin();

            SQLQuery query = session.createSQLQuery(sql);
            query.setParameter(1, parameter1);
            if (parameter2 != null) {
                query.setParameter(2, parameter2);
            }
            query.addEntity(Product.class);
            foundProducts.addAll(query.list());

            transaction.commit();

        } catch (HibernateException e) {
            System.err.println("Search by key - " + parameter1 + " - is failed");
            System.err.println(e.getMessage());

            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        System.out.println("Search by key - " + parameter1 + " - is done");
        return foundProducts;
    }

}
