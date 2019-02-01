package hibernate.lesson2;

import hibernate.lesson1.Product;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();

        Product product = new Product();
        product.setName("Table new");
        product.setDescription("grey & red");
        product.setPrice(70);

        Product product1 = new Product();
        product1.setName("Table new1");
        product1.setDescription("grey & red");
        product1.setPrice(80);

        Product product2 = new Product();
        product2.setName("Table new2");
        product2.setDescription("grey & red");
        product2.setPrice(90);

        Product product3 = new Product();
        product3.setName("Table new3");
        product3.setDescription("grey & red");
        product3.setPrice(100);

        List<Product> products = Arrays.asList(product, product1, product2, product3);

        productDAO.saveAll(products);

        products.get(1).setName(product1.getName() + " updated");
        products.get(2).setName(product2.getName() + " updated");
        products.get(3).setName(product3.getName() + " updated");
        productDAO.updateAll(products);
        productDAO.deleteAll(products);

        productDAO.save(product1);
        product2.setName(product2.getName() + " updated");
        productDAO.update(product2);

        productDAO.delete(product3);



    }
}
