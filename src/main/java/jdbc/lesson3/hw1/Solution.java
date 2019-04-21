package jdbc.lesson3.hw1;

import java.sql.*;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";


    // - который будет успешно добавлять 1000 записей в таблицу TEST_SPEED c произвольными значениями
    // 120449 msec
    public long testSavePerformance() {
        String sql = "INSERT INTO TEST_SPEED VALUES (?, ?, ?)";
        long start = System.currentTimeMillis();
        try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            for (int i = 0; i < 1000; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setString(2, "someString" + i);
                preparedStatement.setLong(3, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }




    // - будет удалять 1000 добавленных перед этим записей, отдельными запросами по полю ID
    // 123476 msec
    public long testDeleteByIdPerformance() {
        String sql = "DELETE FROM TEST_SPEED WHERE ID = ?";
        return testRequestWithId(sql);
    }

    //  - будет удалять 1000, одним SQL запросом()
    // 2978 msec
    public long testDeletePerformance() {
        String sql = "DELETE FROM TEST_SPEED";
        return testRequestConst(sql);
    }

    // - будет выбирать по очереди 1000 добавленных перед этим записей, отдельными запросами по полю ID
    // 119404 msec
    public long testSelectByIdPerformance() {
        String sql = "SELECT * FROM TEST_SPEED WHERE ID = ?";
        return testRequestWithId(sql);
    }

    // - будет выбирать 1000 записей, одним SQL запросом
    // 2967 msec
    public long testSelectPerformance() {
        String sql = "SELECT * FROM TEST_SPEED";
        return testRequestConst(sql);
    }


    private long testRequestWithId(String sql) {
        long start = System.currentTimeMillis();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < 1000; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }


    private long testRequestConst(String sql) {
        long start = System.currentTimeMillis();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


}
