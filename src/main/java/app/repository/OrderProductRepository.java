package app.repository;

import app.config.SqlConfig;
import app.model.OrderProduct;

import java.sql.*;

public class OrderProductRepository {
    private static OrderProductRepository single_instance = null;
    private OrderProductRepository() {}
    public static OrderProductRepository getInstance() {
        if (single_instance == null)
            single_instance = new OrderProductRepository();

        return single_instance;
    }


    public int insertOrderProduct(OrderProduct orderProduct, int id_order) throws SQLException {
        String insert = "INSERT INTO `order_product` (id_order, id_product, quantity, total_price)" +
                " VALUES (?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setInt(1, id_order);
        statement.setInt(2, orderProduct.getProduct().getId_product());
        statement.setInt(3, orderProduct.getQuantity());
        statement.setDouble(4, orderProduct.getPrice());

        return statement.executeUpdate();
    }

    public int removeOrderProductByIds(int id_product, int id_order) throws SQLException {
        String remove = "DELETE FROM `order_product` WHERE id_product = ? AND id_order = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setInt(1, id_product);
        statement.setInt(2, id_product);

        return statement.executeUpdate();
    }


    public int updateOrderProductQuantity(int newQuantity, int id_product, int id_order) throws SQLException {
        String update = "UPDATE order_product SET quantity = ? WHERE id_product = ? AND id_order = ?";
        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, newQuantity);
        preparedStatement.setInt(2, id_product);
        preparedStatement.setInt(3, id_order);

        return preparedStatement.executeUpdate();
    }

    public int updateOrderProductPrice(double newPrice, int id_product, int id_order) throws SQLException {
        String update = "UPDATE order_product SET total_price = ? WHERE id_product = ? AND id_order = ?";
        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setDouble(1, newPrice);
        preparedStatement.setInt(2, id_product);
        preparedStatement.setInt(3, id_order);

        return preparedStatement.executeUpdate();
    }
}
