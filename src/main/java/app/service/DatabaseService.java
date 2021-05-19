package app.service;

import app.config.setupDB;
import app.model.*;
import app.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DatabaseService {
    private static DatabaseService single_instance = new DatabaseService();
    private Logger logger = LogManager.getLogger(DatabaseService.class);
    
    private Service service = Service.getInstance();
    private AccountRepository accRep = AccountRepository.getInstance();
    private DelivererRepository delRep = DelivererRepository.getInstance();
    private RestaurantRepository restRep = RestaurantRepository.getInstance();
    private ProductRepository prodRep = ProductRepository.getInstance();
    private OrderRepository ordRep = OrderRepository.getInstance();

    private DatabaseService() {}

    public static DatabaseService getInstance() {
        if(single_instance == null)
            single_instance = new DatabaseService();
        return single_instance;
    }


    // Database
    public void connectDB() {
        setupDB.initDB();
    }
    public void importFromDB() {
        logger.info("Importing fields from DB");

        logger.debug("Importing accounts from DB...");
        service.setAccounts(getAccountsFromDB());

        logger.debug("Importing restaurants from DB...");
        service.setRestaurants(getRestaurantsFromDB());

        logger.debug("Importing deliverers from DB...");
        service.setDeliverers(getDeliverersFromDB());

        logger.debug("Importing products from DB...");
        for(var id_restaurant : service.getRestaurants().keySet()) {
            service.getRestaurants().get(id_restaurant).
                    setProducts(getAllProductsByRestaurantIdFromDB(id_restaurant));
        }
    }


    // Account DB Functions
    public void addAccountToDB(Account account) {
        int rows_affected = 0;
        try {
            rows_affected = accRep.insertAccount(account);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Account with email '" + account.getEmail() + "' added to DB");
        else
            logger.warn("Couldn't add account with email '" + account.getEmail() + "' to DB");
    }

    public void deleteAccountByEmailFromDB(String email) {
        int rows_affected = 0;
        try {
            rows_affected = accRep.removeAccountByEmail(email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Account with email '" + email + "' deleted from DB");
        else
            logger.warn("Couldn't delete account with email '" + email + "' from DB");
    }

    public Account getAccountByEmailFromDB(String email){
        Account account = null;

        try {
            account = accRep.getAccountByEmail(email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(account == null)
            logger.warn("Account with email '" + email + "' doesn't exist inside the DB");

        return account;
    }

    public Map<String, Account> getAccountsFromDB() {
        Map<String, Account> accounts = null;

        try {
            accounts = accRep.getAllAccounts();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return accounts;
    }

    public void updateAccountPasswordFromDB(String newPassword, String email) {
        int rows_affected = 0;

        try {
            rows_affected = accRep.updateAccountPassword(newPassword, email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Password for the account with email '" + email + "'");
        else
            logger.warn("Couldn't update Password for the account with email '" + email + "'");
    }

    public void updateAccountNameFromDB(String newName, String email) {
        int rows_affected = 0;

        try {
            rows_affected = accRep.updateAccountName(newName, email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Name for the account with email '" + email + "'");
        else
            logger.warn("Couldn't update Name for the account with email '" + email + "'");
    }

    public void updateAccountSurnameFromDB(String newSurname, String email) {
        int rows_affected = 0;

        try {
            rows_affected = accRep.updateAccountSurname(newSurname, email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Surname for the account with email '" + email + "'");
        else
            logger.warn("Couldn't update Surname for the account with email '" + email + "'");
    }

    public void updateAccountPhoneNoFromDB(String newPhoneNo, String email) {
        int rows_affected = 0;

        try {
            rows_affected = accRep.updateAccountPhoneNo(newPhoneNo, email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated PhoneNo for the account with email '" + email + "'");
        else
            logger.warn("Couldn't update PhoneNo for the account with email '" + email + "'");
    }

    public void updateAccountAddressFromDB(String newAddress, String email) {
        int rows_affected = 0;

        try {
            rows_affected = accRep.updateAccountAddress(newAddress, email);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Address for the account with email '" + email + "'");
        else
            logger.warn("Couldn't update Address for the account with email '" + email + "'");
    }



    // Deliverer DB Functions
    public void addDelivererToDB(Deliverer deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.insertDeliverer(deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Deliverer '" + deliverer.getFullName() + "' added to DB");
        else
            logger.warn("Couldn't add deliverer '" + deliverer.getFullName() + "' to DB");
    }

    public void deleteDelivererByIdFromDB(int id_deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.removeDelivererById(id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Deliverer '" + service.getDeliverers().get(id_deliverer).getFullName() + "' deleted from DB");
        else
            logger.warn("Couldn't delete deliverer '" + service.getDeliverers().get(id_deliverer).getFullName() + "' from DB");
    }

    public Deliverer getDelivererByIdFromDB(int id_deliverer){
        Deliverer deliverer = null;

        try {
            deliverer = delRep.getDelivererById(id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(deliverer == null)
            logger.warn("Deliverer with id '" + id_deliverer + "' doesn't exist");

        return deliverer;
    }

    public Map<Integer, Deliverer> getDeliverersFromDB() {
        Map<Integer, Deliverer> deliverers = null;

        try {
            deliverers = delRep.getAllDeliverers();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return deliverers;
    }

    public void updateDelivererNameFromDB(String newName, int id_deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.updateDelivererName(newName, id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Name for deliverer with id '" + id_deliverer + "'");
        else
            logger.warn("Couldn't update Name for deliverer with id '" + id_deliverer + "'");
    }

    public void updateDelivererSurnameFromDB(String newSurname, int id_deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.updateDelivererSurname(newSurname, id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Surname for deliverer with id '" + id_deliverer + "'");
        else
            logger.warn("Couldn't update Surname for deliverer with id '" + id_deliverer + "'");
    }

    public void updateDelivererPhoneNoFromDB(String newPhoneNo, int id_deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.updateDelivererPhoneNo(newPhoneNo, id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Phone Number for deliverer with id '" + id_deliverer + "'");
        else
            logger.warn("Couldn't update Phone Number for deliverer with id '" + id_deliverer + "'");
    }

    public void updateDelivererVehicleFromDB(Deliverer.Vehicle newVehicle, int id_deliverer) {
        int rows_affected = 0;

        try {
            rows_affected = delRep.updateDelivererVehicle(newVehicle.toString(), id_deliverer);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Vehicle for deliverer with id '" + id_deliverer + "'");
        else
            logger.warn("Couldn't update Vehicle for deliverer with id '" + id_deliverer + "'");
    }



    // Restaurant DB Functions
    public void addRestaurantToDB(Restaurant restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.insertRestaurant(restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Restaurant '" + restaurant.getName() + "' added to DB");
        else
            logger.warn("Couldn't add restaurant '" + restaurant.getName() + "' to DB");
    }

    public void deleteRestaurantByIdFromDB(int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.removeRestaurantById(id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "' deleted");
        else
            logger.warn("Couldn't delete restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }

    public Restaurant getRestaurantByIdFromDB(int id_restaurant){
        Restaurant restaurant = null;

        try {
            restaurant = restRep.getRestaurantById(id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(restaurant == null)
            logger.warn("Restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "' doesn't exist");

        return restaurant;
    }

    public Map<Integer, Restaurant> getRestaurantsFromDB() {
        Map<Integer, Restaurant> restaurants = null;

        try {
            restaurants = restRep.getAllRestaurants();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(restaurants == null)
            logger.warn("DB Restaurants table is empty");

        return restaurants;
    }

    public void updateRestaurantNameFromDB(String newName, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantName(newName, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Name for restaurant with id '" + id_restaurant + "'");
        else
            logger.warn("Couldn't update Name for restaurant with id '" + id_restaurant + "'");
    }

    public void updateRestaurantAddressFromDB(String newAddress, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantAddress(newAddress, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Address for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
        else
            logger.warn("Couldn't update Address for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }

    public void updateRestaurantDescriptionFromDB(String newDescription, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantDescription(newDescription, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Description for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
        else
            logger.warn("Couldn't update Description for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }

    public void updateRestaurantDeliveryPriceFromDB(double newDeliveryPrice, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantDeliveryPrice(newDeliveryPrice, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Delivery Price for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
        else
            logger.warn("Couldn't update Delivery Price for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }

    public void updateRestaurantScoreFromDB(double newScore, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantScore(newScore, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Score for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
        else
            logger.warn("Couldn't update Score for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }

    public void updateRestaurantDeliveryTimeFromDB(int newDeliveryTime, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = restRep.updateRestaurantDeliveryTime(newDeliveryTime, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Delivery Time for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
        else
            logger.warn("Couldn't update Delivery Time for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");
    }



    // Product DB Functions
    public void addProductToDB(Product product, int id_restaurant) {
        int rows_affected = 0;

        try {
            rows_affected = prodRep.insertProduct(product, id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Product '" + product.getName() + "' added to DB");
        else
            logger.warn("Couldn't add product '" + product.getName() + "' to DB");
    }

    public void deleteProductByIdFromDB(int id_product) {
        int rows_affected = 0;

        try {
            rows_affected = prodRep.removeProductById(id_product);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Product with id '" + id_product + "' deleted");
        else
            logger.warn("Couldn't delete product with id '" + id_product + "'");
    }

    public Product getProductByIdFromDB(int id_product){
        Product product = null;

        try {
            product = prodRep.getProductById(id_product);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(product == null)
            logger.warn("Product with id '" + id_product + "' doesn't exist");

        return product;
    }

    public List<Product> getAllProductsByRestaurantIdFromDB(int id_restaurant) {
        List<Product> products = null;

        try {
            products = prodRep.getAllProductsByRestaurantId(id_restaurant);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(products == null)
            logger.warn("There are no products inside DB for restaurant '" + service.getRestaurants().get(id_restaurant).getName() + "'");

        return products;
    }

    public void updateProductNameFromDB(String newName, int id_product) {
        int rows_affected = 0;

        try {
            rows_affected = prodRep.updateProductName(newName, id_product);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Name for the product with id '" + id_product + "'");
        else
            logger.warn("Couldn't update Name for the product with id '" + id_product + "'");
    }

    public void updateProductPriceFromDB(double newPrice, int id_product) {
        int rows_affected = 0;

        try {
            rows_affected = prodRep.updateProductPrice(newPrice, id_product);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Price for the product with id '" + id_product + "'");
        else
            logger.warn("Couldn't update Price for the product with id '" + id_product + "'");
    }

    public void updateProductDescriptionFromDB(String newDescription, int id_product) {
        int rows_affected = 0;

        try {
            rows_affected = prodRep.updateProductDescription(newDescription, id_product);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Description for the product with id '" + id_product + "'");
        else
            logger.warn("Couldn't update Description for the product with id '" + id_product + "'");
    }



    // Order DB Functions
    public void addOrderToDB(Order order) {
        int rows_affected = 0;

        try {
            rows_affected = ordRep.insertOrder(order);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("New order for email '" + order.getEmail() + "' added to DB");
        else
            logger.warn("Couldn't add new order for email '" + order.getEmail() + "' to DB");
    }

    public int getLastOrderIdFromDB(){
        int id = -1;

        try {
            id = ordRep.getLastOrderId();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(id == -1)
            logger.warn("Database order table is empty");

        return id;
    }

    public void updateOrderStatusFromDB(Order.Status newStatus, int id_order) {
        int rows_affected = 0;

        try {
            rows_affected = ordRep.updateStatus(newStatus.toString(), id_order);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Status for order with id '" + id_order + "'");
        else
            logger.warn("Couldn't update Status for order with id '" + id_order + "'");
    }

    public void updateOrderDelivererFromDB(int newIdDeliverer, int id_order) {
        int rows_affected = 0;

        try {
            rows_affected = ordRep.updateIdDeliverer(newIdDeliverer, id_order);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if(rows_affected == 1)
            logger.info("Updated Deliverer for order with id '" + id_order + "'");
        else
            logger.warn("Couldn't update Deliverer for order with id '" + id_order + "'");
    }

}
