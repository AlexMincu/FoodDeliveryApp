package app.repository;

import app.config.SqlConfig;
import app.model.Restaurant;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {
    private static RestaurantRepository single_instance = null;
    private RestaurantRepository() {}
    public static RestaurantRepository getInstance() {
        if (single_instance == null)
            single_instance = new RestaurantRepository();

        return single_instance;
    }


    public int insertRestaurant(Restaurant restaurant) throws SQLException {
        String insert = "INSERT INTO `restaurant` (name, address, description, delivery_price, delivery_time, score)" +
                " VALUES (?, ?, ?, ?, ?, ?)";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(insert);
        statement.setString(1, restaurant.getName());
        statement.setString(2, restaurant.getAddress());
        statement.setString(3, restaurant.getDescription());
        statement.setDouble(4, restaurant.getDelivery_price());
        statement.setInt(5, restaurant.getDelivery_time());
        statement.setDouble(6, restaurant.getScore());

        return statement.executeUpdate();
    }

    public Restaurant getRestaurantById(int id_restaurant) throws SQLException {
        String select = "SELECT * FROM `restaurant` WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(select);
        statement.setInt(1, id_restaurant);
        ResultSet result = statement.executeQuery();

        if (result.next())
            return new Restaurant(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getDouble(4),
                    result.getInt(5),
                    result.getDouble(6));
        else
            return null;
    }

    public int removeRestaurantById(int id_restaurant) throws SQLException {
        String remove = "DELETE FROM `restaurant` WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement statement = connection.prepareStatement(remove);
        statement.setInt(1, id_restaurant);

        return statement.executeUpdate();
    }

    public Map<Integer, Restaurant> getAllRestaurants() throws SQLException {
        String getRestaurants = "SELECT * FROM restaurant";

        Connection connection = SqlConfig.getDataBaseConnection();

        Map<Integer, Restaurant> restaurants = new HashMap<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(getRestaurants);

        while (result.next()) {
            Restaurant currentRestaurant = new Restaurant(
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getDouble(5),
                    result.getInt(6),
                    result.getDouble(7));
            restaurants.put(result.getInt(1), currentRestaurant);
        }

        return restaurants;
    }

    public int updateRestaurantName(String newName, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET name = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newName);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }

    public int updateRestaurantAddress(String newAddress, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET address = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newAddress);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }

    public int updateRestaurantDescription(String newDescription, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET description = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setString(1, newDescription);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }

    public int updateRestaurantDeliveryPrice(double newDeliveryPrice, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET delivery_price = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setDouble(1, newDeliveryPrice);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }

    public int updateRestaurantDeliveryTime(int newDeliveryTime, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET delivery_time = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setInt(1, newDeliveryTime);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }

    public int updateRestaurantScore(double newScore, int id_restaurant) throws SQLException {
        String update = "UPDATE restaurant SET score = ? WHERE id_restaurant = ?";

        Connection connection = SqlConfig.getDataBaseConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(update);
        preparedStatement.setDouble(1, newScore);
        preparedStatement.setInt(2, id_restaurant);

        return preparedStatement.executeUpdate();
    }
}
