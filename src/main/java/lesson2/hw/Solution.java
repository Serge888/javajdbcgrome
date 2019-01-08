package lesson2.hw;

import java.sql.*;
import java.text.BreakIterator;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRules;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Solution {

    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "Main4309050";


    // который будет сохранять продукт в таблицу PRODUCT со значениями полей по порядку - 999, toy, for children, 60
    public static void saveProduct() {
        workWithDb("INSERT INTO PRODUCT VALUES (1, 'toy', 'for children for children for children for children " +
                "for children for children for children for children for children ', 660)");
     }

    // который будет удалять продукты из таблицы PRODUCT, с именем не toy
    public static int deleteProducts() {
        return workWithDb("DELETE FROM PRODUCT WHERE NAME = 'toy'");
    }

    // который будет удалять продукты из таблицы PRODUCT, с ценой меньше 100
    public static int deleteProductsByPrice() {
        return workWithDb("DELETE FROM PRODUCT WHERE PRICE < 100");
    }

    // список всех продуктов из таблицы PRODUCT
    public static List getAllProducts() {
        return createEntityFromDb("SELECT * FROM PRODUCT");
    }

    // список всех продуктов с ценой до 100 включительно
    public static List getProductsByPrice() {
        return createEntityFromDb("SELECT * FROM PRODUCT WHERE PRICE <= 100");
    }

    // список всех продуктов с длинной описание больше 50
    public static List getProductsByDescription() {
        return createEntityFromDb("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 50");
    }

    // увеличивает значение цены на 100, во всех продуктах, где цена меньше 970
    public static void increasePrice() {
        workWithDb("UPDATE PRODUCT SET PRICE = (PRICE + 100) WHERE PRICE < 970");
    }

    // удаляет последнее предложение с описания всех продуктов с длинной описания больше 100
    public static void changeDescription() {
        List<Product> products = createEntityFromDb("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 20");
        for (Product product : products) {
            String description = product.getDescription();
            List<String> list = splitTextIntoSentences(description);
            String forDel = list.get(list.size() - 1);
            String sql = "UPDATE PRODUCT SET DESCRIPTION = (SELECT REPLACE(DESCRIPTION, '"
                    + forDel + "') from PRODUCT WHERE ID = " + product.getId() + ") WHERE ID = " + product.getId();
            workWithDb(sql);
        }
    }

    private static List<String> splitTextIntoSentences(String text) {
        BreakIterator iterator = BreakIterator.getSentenceInstance();
        List<String> list = new ArrayList<>();
        text = text.replaceAll("(\r\n|\r|\n)", "");
        iterator.setText(text);
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            list.add(text.substring(start,end));
        }
        return list;
    }

//    private static void changeDescription() {
//        List<Product> products = createEntityFromDb("SELECT * FROM PRODUCT WHERE LENGTH(DESCRIPTION) > 20");
//        for (Product product : products) {
//            String description = product.getDescription();
//            String[] sentences = description.split("[.!?]");
//            String forDel = sentences[sentences.length - 1] + description.substring(description.length() - 1);
//            String sql = "UPDATE PRODUCT SET DESCRIPTION = (SELECT REPLACE(DESCRIPTION, '"
//                    + forDel + "') from PRODUCT WHERE ID = " + product.getId() + ") WHERE ID = " + product.getId();
//            workWithDb(sql);
//        }
//    }

    private static int workWithDb(String sql) {
        int response = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            response = statement.executeUpdate(sql);
            System.out.println(response);
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return response;
    }

    private static List createEntityFromDb(String sql) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getLong(1));
                    product.setName(resultSet.getString(2));
                    product.setDescription(resultSet.getString(3));
                    product.setPrice(resultSet.getInt(4));
                    products.add(product);
                }
            } catch (SQLException e) {
                System.err.println("Something went wrong");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
        return products;
    }

}
