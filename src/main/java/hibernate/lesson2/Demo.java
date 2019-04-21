package hibernate.lesson2;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
                ProductDaoHql productDaoHql = new ProductDaoHql();

        System.out.println(productDaoHql.findById(5L));
//        System.out.println(productDaoHql.findByName("Table new1"));
//        System.out.println(productDaoHql.findByContainedName("new"));
//        System.out.println(productDaoHql.findByPrice(100, 15));
//        System.out.println(productDaoHql.findByNameSortedAsc("Table new1"));
//        System.out.println(productDaoHql.findByNameSortedDesc("Table new1"));
//        System.out.println(productDaoHql.findByPriceSortedDesc(100, 15));
//
//
//        ProductDaoSql productDaoSql = new ProductDaoSql();
//
//        System.out.println(productDaoSql.findEntityBy(5L));
//        System.out.println(productDaoSql.findByName("Table new1"));
//        System.out.println(productDaoSql.findByContainedName("new"));
//        System.out.println(productDaoSql.findByPrice(100, 15));
//        System.out.println(productDaoSql.findByNameSortedAsc("Table new1"));
//        System.out.println(productDaoSql.findByNameSortedDesc("Table new1"));
//        System.out.println(productDaoSql.findByPriceSortedDesc(100, 15));
//
//
//
//
//
//        ProductDAO productDAO = new ProductDAO();
//
//        Product product = new Product();
//        product.setName("Table new");
//        product.setDescription("grey & red");
//        product.setPrice(70);
//
//        Product product1 = new Product();
//        product1.setName("Table new1");
//        product1.setDescription("grey & red");
//        product1.setPrice(80);
//
//        Product product2 = new Product();
//        product2.setName("Table new2");
//        product2.setDescription("grey & red");
//        product2.setPrice(90);
//
//        Product product3 = new Product();
//        product3.setName("Table new3");
//        product3.setDescription("grey & red");
//        product3.setPrice(100);
//
//        List<Product> products = Arrays.asList(product, product1, product2, product3);
//
//        productDAO.saveAll(products);
//
//        products.get(1).setName(product1.getName() + " updated");
//        products.get(2).setName(product2.getName() + " updated");
//        products.get(3).setName(product3.getName() + " updated");
//        productDAO.updateAll(products);
//        productDAO.deleteAll(products);
//
//        productDAO.saveEntity(product1);
//        product2.setName(product2.getName() + " updated");
//        productDAO.updateEntity(product2);
//
//        productDAO.deleteEntity(product3);
//


    }
}
