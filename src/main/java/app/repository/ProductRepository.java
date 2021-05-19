package app.repository;

import app.config.SqlConfig;
import app.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static ProductRepository single_instance = null;
    private ProductRepository() {}
    public static ProductRepository getInstance() {
        if (single_instance == null)
            single_instance = new ProductRepository();

        return single_instance;
    }


    public int insertProduct(Product product, int id_restaurant) throws SQLException {
        String insert = "INSERT INTO `product` (id_restaurant, name, price, description)" +
                " VALUES (?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setInt(1, id_restaurant);
        statement.setString(2, product.getName());
        statement.setDouble(3, product.getPrice());
        statement.setString(4, product.getDescription());

        return statement.executeUpdate();
    }

    public Product getProductById(int id_product) throws SQLException {
        String select = "SELECT * FROM `product` WHERE id_product = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setInt(1, id_product);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return new Product(
                    result.getInt(1),
                    result.getString(3),
                    result.getDouble(4),
                    result.getString(5));
        else
            return null;
    }

    public int removeProductById(int id_product) throws SQLException {
        String remove = "DELETE FROM `product` WHERE id_product = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setInt(1, id_product);

        return statement.executeUpdate();
    }

    public List<Product> getAllProductsByRestaurantId(int id_restaurant) throws SQLException {
        String getProducts = "SELECT * FROM `product` WHERE `id_restaurant` = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        List<Product> products = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(getProducts);
        statement.setInt(1, id_restaurant);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Product currentProduct = new Product(
                    result.getInt(1),
                    result.getString(3),
                    result.getDouble(4),
                    result.getString(5));
            products.add(currentProduct);
        }

        return products;
    }


    public int updateProductName(String newName, int id_product) throws SQLException {
        String update = "UPDATE product SET name = ? WHERE id_product = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, id_product);

        return preparedStatement.executeUpdate();
    }

    public int updateProductPrice(double newPrice, int id_product) throws SQLException {
        String update = "UPDATE product SET price = ? WHERE id_product = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setDouble(1, newPrice);
        preparedStatement.setInt(2, id_product);

        return preparedStatement.executeUpdate();
    }

    public int updateProductDescription(String newDescription, int id_product) throws SQLException {
        String update = "UPDATE product SET description = ? WHERE id_product = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newDescription);
        preparedStatement.setInt(2, id_product);

        return preparedStatement.executeUpdate();
    }

}
