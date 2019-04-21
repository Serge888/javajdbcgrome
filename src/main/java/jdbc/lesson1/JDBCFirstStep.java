package jdbc.lesson1;

import jdbc.lesson2.Order;

import java.sql.*;
import java.util.Date;

public class JDBCFirstStep {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try {
                Class.forName(JDBC_DRIVER); // driver initialization
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                return;
            }
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS")) {
                while (resultSet.next()) {
                    long id = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    Date dateOrdered = resultSet.getDate(4);
                    Date dateConfirmed = resultSet.getDate(5);
                    Order order = new Order(id, name, price, dateOrdered, dateConfirmed);
                    System.out.println("jdbc.lesson2.JDBCExamples.Order" + order);
                }
            } catch (SQLException e) {
                System.err.println("Something went wrong 1");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong 2");
            e.printStackTrace();
        }
    }
}
