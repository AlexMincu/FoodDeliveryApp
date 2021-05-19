//package app.repository;
//
//import app.config.SqlConfig;
//import app.model.OrderProduct;
//
//import java.sql.*;
//
//public class OrderProductRepository {
//    private static OrderProductRepository single_instance = null;
//    private OrderProductRepository() {}
//    public static OrderProductRepository getInstance() {
//        if (single_instance == null)
//            single_instance = new OrderProductRepository();
//
//        return single_instance;
//    }
//
//
//    public int insertOrderProduct(OrderProduct orderProduct, int order_id) throws SQLException {
//
//        String insert = "INSERT INTO `order_product` (id_order_product, id_product, quantiy, total_price)" +
//                " VALUES (?, ?, ?, ?)";
//
//        Connection connection = SqlConfig.getDataBaseConnection();
//
//        PreparedStatement statement = connection.prepareStatement(insert);
//        statement.setString(1, orderProduct.getEmail());
//        statement.setString(2, account.getPassword());
//        statement.setString(3, account.getName());
//        statement.setString(4, account.getSurname());
//
//        return statement.executeUpdate();
//    }
//
//    public int removeAccountByEmail(String email) throws SQLException {
//
//        String remove = "DELETE FROM `account` WHERE email = ?";
//
//        Connection connection = SqlConfig.getDataBaseConnection();
//
//        PreparedStatement statement = connection.prepareStatement(remove);
//        statement.setString(1, email);
//
//        return statement.executeUpdate();
//    }
//
//
//    public int updateAccountName(String newName, String email) throws SQLException {
//
//        String update = "UPDATE account SET name=? WHERE email=?";
//        Connection connection = SqlConfig.getDataBaseConnection();
//
//        PreparedStatement preparedStatement = connection.prepareStatement(update);
//
//        preparedStatement.setString(1, newName);
//        preparedStatement.setString(2, email);
//
//        return preparedStatement.executeUpdate();
//    }
//}
