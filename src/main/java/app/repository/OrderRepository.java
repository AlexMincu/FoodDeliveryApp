package app.repository;

import app.config.SqlConfig;
import app.model.Order;

import java.sql.*;

public class OrderRepository {
    private static OrderRepository single_instance = null;
    private OrderRepository() {}
    public static OrderRepository getInstance() {
        if (single_instance == null)
            single_instance = new OrderRepository();

        return single_instance;
    }


    public int insertOrder(Order order) throws SQLException {
        String insert = "INSERT INTO `order` (email, address, total_price, status)" +
                " VALUES (?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, order.getEmail());
        statement.setString(2, order.getAddress());
        statement.setDouble(3, order.getPrice());
        statement.setString(4, order.getStatus().toString());

        return statement.executeUpdate();
    }

    public int getLastOrderId() throws SQLException {
        String select = "SELECT MAX(id_order) FROM `order`";

        Connection connection = SqlConfig.getDataBaseConnection();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(select);

        result.next();
        return result.getInt(1);
    }

    public int removeOrderById(int id_order) throws SQLException {
        String remove = "DELETE FROM `order` WHERE id_order = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setInt(1, id_order);

        return statement.executeUpdate();
    }

    public int updateStatus(String newStatus, int id_order) throws SQLException {
        String update = "UPDATE `order` SET status = ? WHERE id_order = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newStatus);
        preparedStatement.setInt(2, id_order);

        return preparedStatement.executeUpdate();
    }

    public int updateIdDeliverer(int newIdDeliverer, int id_order) throws SQLException {
        String update = "UPDATE `order` SET `id_deliverer` = ? WHERE `id_order` = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, newIdDeliverer);
        preparedStatement.setInt(2, id_order);

        return preparedStatement.executeUpdate();
    }
}
