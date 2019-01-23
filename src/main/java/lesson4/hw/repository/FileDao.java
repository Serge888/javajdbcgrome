package lesson4.hw.repository;

import lesson4.hw.model.File;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDao {
    private StorageDao storageDao = new StorageDao();
    private static final String DB_URL = "jdbc:oracle:thin:@test.cgjumrd6z5jk.us-east-1.rds.amazonaws.com:1521:ORCL";
    private static final String USER = "main";
    private static final String PASS = "Main4309050";


    private String sqlSave = "INSERT INTO FILES (FILE_NAME, FILE_FORMAT, FILE_SIZE, STORAGE_ID) VALUES (?, ?, ?, ?)";
    private String sqlUpdateList = "UPDATE FILES SET FILE_NAME = ?, FILE_FORMAT = ?, FILE_SIZE = ?, STORAGE_ID = ? WHERE ID = ?";
    private String sqlFindById = "SELECT * FROM FILES WHERE ID = ?";
    private String sqlFindAllInStorage = "SELECT * FROM FILES WHERE STORAGE_ID = ?";
    private String sqlDelete = "DELETE FROM FILES WHERE ID = ?";

    public File save(File file) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)) {
            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getFormat());
            preparedStatement.setLong(3, file.getSize());
            preparedStatement.setLong(4, file.getStorage().getId());
            int res = preparedStatement.executeUpdate();
            System.out.println("Save file with name " + file.getName() + " was finished with res: " + res);
        } catch (SQLException e) {
            System.err.println("File with name " + file.getName() + " was not saved in storage id " + file.getStorage().getId());
            e.printStackTrace();
        }
        return file;
    }

    public void update(List<File> files) {
        try (Connection connection = getConnection()) {
            updateList(files, connection);
        } catch (SQLException e) {
        System.err.println("Update was failed.");
        e.printStackTrace();
        }
    }

    private void updateList(List<File> files, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateList)) {
            connection.setAutoCommit(false);
            for (File file : files) {
                preparedStatement.setString(1, file.getName());
                preparedStatement.setString(2, file.getFormat());
                preparedStatement.setLong(3, file.getSize());
                preparedStatement.setLong(4, file.getStorage().getId());
                preparedStatement.setLong(5, file.getId());
                int res = preparedStatement.executeUpdate();
                System.out.println("Update file with id " + file.getId() + " was finished with res: " + res);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }



    public File findById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindById)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new File(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4),
                        storageDao.findById(resultSet.getLong(5)));
            }
        } catch (SQLException e) {
            System.err.println("File with id " + id + " was not found");
            e.printStackTrace();
        }
        return null;
    }

    public List<File> findAllInStorage(long storageId) {
        List<File> foundFiles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlFindAllInStorage)) {
            preparedStatement.setLong(1, storageId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                File file = new File(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4),
                        storageDao.findById(resultSet.getLong(5)));
                foundFiles.add(file);
            }
            return foundFiles;
        } catch (SQLException e) {
            System.err.println("Files in storage with id " + storageId + " were not found");
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("Delete file with id " + id + " was finished with res: " + res);
        } catch (SQLException e) {
            System.err.println("File with id " + id + " was not deleted");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
