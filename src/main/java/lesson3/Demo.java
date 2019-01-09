package lesson3;

import lesson2.hw.Product;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
//        Product product = new Product(10, "testNew", "test description New", 99);
//
//        productDAO.save(product);
//        productDAO.update(product);
//        System.out.println(productDAO.getProducts());
        productDAO.delete(111);
    }
}
