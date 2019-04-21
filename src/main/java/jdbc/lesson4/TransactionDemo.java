package jdbc.lesson4;

import jdbc.lesson2.hw.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {
    // 1. saveEntity order - pay money - receive money
    // 2. saveEntity order - pay money - receive money

    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";

    public static void main(String[] args) {
        Product product1 = new Product(55, "testNew1", "test description New", 222);
        Product product2 = new Product(66, "testNew2", "test description New", 333);
        Product product3 = new Product(55, "testNew3", "test description New", 444);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        save(products);
    }


    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        String sql = "INSERT INTO PRODUCT VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();
                System.out.println("Save of the product with id " + product.getId() + " was finished with res: " + res);
            }
            connection.commit();

        } catch (SQLException e) {
            System.err.println("");
            connection.rollback();
            throw e;
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
