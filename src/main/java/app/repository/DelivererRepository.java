package app.repository;

import app.config.SqlConfig;
import app.model.Deliverer;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DelivererRepository {
    private static DelivererRepository single_instance = null;

    private DelivererRepository() {
    }

    public static DelivererRepository getInstance() {
        if (single_instance == null)
            single_instance = new DelivererRepository();

        return single_instance;
    }


    public int insertDeliverer(Deliverer deliverer) throws SQLException {
        String insert = "INSERT INTO `deliverer` (name, surname, phoneNo, vehicle)" +
                " VALUES (?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, deliverer.getName());
        statement.setString(2, deliverer.getSurname());
        statement.setString(3, deliverer.getPhoneNo());
        statement.setString(4, deliverer.getVehicle().toString());

        return statement.executeUpdate();
    }

    public Deliverer getDelivererById(int id_deliverer) throws SQLException {
        String select = "SELECT * FROM `deliverer` WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setInt(1, id_deliverer);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return new Deliverer(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    Deliverer.toVehicle(result.getString(5)));
        else
            return null;
    }

    public int removeDelivererById(int id_deliverer) throws SQLException {
        String remove = "DELETE FROM `deliverer` WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();
        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setInt(1, id_deliverer);

        return statement.executeUpdate();
    }

    public Map<Integer, Deliverer> getAllDeliverers() throws SQLException {
        String getDeliverers = "SELECT * FROM deliverer";

        Connection connection = SqlConfig.getDataBaseConnection();

        Map<Integer, Deliverer> deliverers = new HashMap<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getDeliverers);

        while (result.next()) {
            Deliverer currentDeliverer = new Deliverer(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    Deliverer.toVehicle(result.getString(5)));

            deliverers.put(result.getInt(1), currentDeliverer);
        }

        return deliverers;
    }

    public int updateDelivererName(String newName, int id_deliverer) throws SQLException {
        String update = "UPDATE deliverer SET name = ? WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, id_deliverer);

        return preparedStatement.executeUpdate();
    }

    public int updateDelivererSurname(String newSurname, int id_deliverer) throws SQLException {
        String update = "UPDATE deliverer SET surname = ? WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newSurname);
        preparedStatement.setInt(2, id_deliverer);

        return preparedStatement.executeUpdate();
    }

    public int updateDelivererPhoneNo(String newPhoneNo, int id_deliverer) throws SQLException {
        String update = "UPDATE deliverer SET phoneNo = ? WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newPhoneNo);
        preparedStatement.setInt(2, id_deliverer);

        return preparedStatement.executeUpdate();
    }

    public int updateDelivererVehicle(String newVehicle, int id_deliverer) throws SQLException {
        String update = "UPDATE deliverer SET vehicle = ? WHERE id_deliverer = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newVehicle);
        preparedStatement.setInt(2, id_deliverer);

        return preparedStatement.executeUpdate();
    }

}
