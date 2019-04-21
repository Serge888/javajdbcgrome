package jdbc.lesson2;

import java.sql.*;

public class JDBCExamples {
    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            boolean res = statement.execute("INSERT INTO PRODUCT (ID, NAME, DESCRIPTION, PRICE) VALUES (3, 'TOY3', 'FOR CHILDREN', 60)");
//            boolean res = statement.execute("INSERT INTO PRODUCT VALUES (2, 'TOY2', 'FOR CHILDREN', 60)");
//            System.out.println(res);

//            boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME = 'TOY'");
//            System.out.println(res);

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME = 'TOY2'");
            System.out.println(response);

        } catch (SQLException e) {
            System.err.println("Something went wrong 2");
            e.printStackTrace();
        }
    }

}
