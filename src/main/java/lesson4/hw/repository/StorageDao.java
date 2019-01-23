package lesson4.hw.repository;

import lesson4.hw.model.Storage;

import java.sql.*;

import static lesson4.hw.repository.FileDao.*;

public class StorageDao {
    private String sqlSave = "INSERT INTO STORAGE (STORAGE_COUNTRY, STORAGE_MAX_SIZE, FILE_FORMAT) VALUES (?, ?, ?)";
    private String sqlUpdate = "UPDATE STORAGE SET STORAGE_COUNTRY = ?, STORAGE_MAX_SIZE = ?, FILE_FORMAT = ? WHERE ID = ?";
    private String sqlFindById = "SELECT * FROM STORAGE WHERE ID = ";
    private String sqlDelete = "DELETE FROM STORAGE WHERE ID = ?";

    public Storage save(Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSave)) {
            preparedStatement.setString(1, storage.getStorageCountry());
            preparedStatement.setLong(2, storage.getStorageMaxSize());
            preparedStatement.setString(3, formatsToString(storage.getFormatsSupported()));
            int res = preparedStatement.executeUpdate();
            System.out.println("Save storage was finished with res: " + res);
        } catch (SQLException e) {
            System.err.println("Storage was not saved");
            e.printStackTrace();
        }
        return storage;
    }

    public Storage update(Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, storage.getStorageCountry());
            preparedStatement.setLong(2, storage.getStorageMaxSize());
            preparedStatement.setString(3, formatsToString(storage.getFormatsSupported()));
            preparedStatement.setLong(4, storage.getId());
            int res = preparedStatement.executeUpdate();
            System.out.println("Update storage with id " + storage.getId() + " was finished with res: " + res);
        } catch (SQLException e) {
            System.err.println("Storage with id " + storage.getId() + " was not updated");
            e.printStackTrace();
        }
        return storage;
    }

    public Storage findById(long id) {
        sqlFindById = sqlFindById + id;
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlFindById);
            if (resultSet.next()) {
                return new Storage(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getLong(3), formatsToArray(resultSet.getString(4)));
            }
        } catch (SQLException e) {
            System.err.println("Storage with id " + id + " was not found");
            e.printStackTrace();
        }
        return null;
    }

    public void delete(long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            System.out.println("Delete storage with id " + id + " was finished with res: " + res);
        } catch (SQLException e) {
            System.err.println("Storage with id " + id + " was not deleted");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private String formatsToString(String[] formats) {
        if (formats == null) {
            return null;
        }
        String stringFormats = formats[0];
        for (int i = 1; i < formats.length; i++) {
            stringFormats = stringFormats + "," + formats[i];
        }
        return stringFormats;
    }

    private String[] formatsToArray(String formats) {
        return formats.split(",");
    }
}
