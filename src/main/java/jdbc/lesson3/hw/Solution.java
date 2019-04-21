package jdbc.lesson3.hw;

import jdbc.lesson2.hw.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";

    // - будет искать продукты с заданной ценной в диапазоне +=delta включительно.
    // Например, если нужно найти продукты с ценой 100 и дельтой 10, то ищем все от 90 до 110
    public List<Product> findProductsByPrice(int price, int delta) {
        long priceMax = price + delta;
        int priceMin = price - delta;
        String sql = "SELECT * FROM PRODUCT WHERE PRICE BETWEEN ? AND ?";
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, priceMin);
            preparedStatement.setLong(2, priceMax);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    //  - продукты, которые содержат в своем имене слово word.
    public List<Product> findProductsByName(String word) throws Exception {
        validation(word);
        String sql = "SELECT * FROM PRODUCT WHERE NAME LIKE ?";
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + word + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    //  - продукты с пустым полем описания
    public List<Product> findProductsWithEmptyDescription() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) = 0";
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Если word является некоректным (больше одного слова в стринге,
    // длина меньше 3, содержит спецсимволы), выбрасывать ошибку,
    // которая в описании обязательно должна содержать само слово и описание ошибки
    private void validation(String word) throws Exception {
        if (word.length() < 3) {
            throw new Exception("Word less than 3 characters.");
        }
        char[] characters = word.toCharArray();

        for (char character : characters) {
            if (character == ' ') {
                throw new Exception("There are more than one word in the string. The string contains a space.");
            } else if (!Character.isLetterOrDigit(character)) {
                throw new Exception("The word contains special characters.");
            }
        }

    }



}
