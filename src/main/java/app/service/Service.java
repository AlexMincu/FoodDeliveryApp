package app.service;

import app.config.setupDB;
import app.gui.LoginPage;
import app.gui.RestaurantsPage;
import app.model.*;
import app.repository.OrderProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Service extends DatabaseService {
    private static Service single_instance = null;
    private final Logger logger = LogManager.getLogger(Service.class);

    private Service() {
        super();
    }

    public static Service getInstance() {
        if (single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    // GUI and other useful methods
    public void firstStartApp() {
        logger.info("Application is starting");

        setupDB.dropDB();
        setupDB.initDB();
        setupDB.addDummyData2DB();
        importFromDB();

        if (currentAccount == null) {
            new LoginPage();
        } else {
            new RestaurantsPage();
        }
    }

    public void startApp() {
        logger.info("Application is starting");

        setupDB.initDB();
        importFromDB();

        if (currentAccount == null) {
            new LoginPage();
        } else {
            new RestaurantsPage();
        }
    }


    // Account functions
    public Boolean login(String email, String password) {
        logger.debug("Logging in using the email: " + email);

        if(accounts == null)
            logger.warn("There are no accounts imported");

        if (accounts.get(email).getPassword().equals(password)) {
            currentAccount = accounts.get(email);
            return true;
        } else {
            System.out.println("Wrong credentials");
            return false;
        }
    }

    public boolean register(String email, String name, String surname, String phoneNo, String password, String address) {
        logger.debug("Trying to register an account using the email: " + email);

        if (accounts.get(email) == null) {
            accounts.put(email, new Account(email, password, name, surname, phoneNo, address));
            return true;
        } else {
            logger.warn("Email already taken");
            return false;
        }
    }


    // Cart functions
    public void addToCart(Product product) {
        logger.debug("Adding product to cart - [" + product.getName() + "]");

        boolean found = false;
        for (var p : cart)
            if (p.getProduct().equals(product)) {
                p.setQuantity(p.getQuantity() + 1);

                found = true;
                break;
            }

        if (!found)
            cart.add(new OrderProduct(product, 1));
    }

    public void removeFromCart(Product product) {
        logger.debug("Removing product from cart - [" + product.getName() + "]");

        for (var p : cart)
            if (p.getProduct().equals(product)) {
                if (p.getQuantity() > 1)
                    p.setQuantity(p.getQuantity() - 1);
                else
                    cart.remove(p);

                break;
            }
    }

    public void flushCart() {
        if (!cart.isEmpty()) {
            logger.debug("Flushing cart");
            cart.clear();
        }
    }


    // Order functions
    public void createOrder(String address) {
        Order order = new Order(currentAccount.getEmail(), address, cart);

        addOrderToDB(order);
        order.setId_order(getLastOrderIdFromDB());

        //createOrderProductList
        try {
            OrderProductRepository rep = OrderProductRepository.getInstance();
            for (var product : order.getOrder_list())
                rep.insertOrderProduct(product, order.getId_order());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        findAvailableDeliverer(order);
    }


    /**
     * A simple function to search the first deliverer available
     */
    private void findAvailableDeliverer(Order order) {
        for(var id_deliverer : deliverers.keySet()) {
            if(!deliverers.get(id_deliverer).isBusy()) {
                order.setDeliverer(deliverers.get(id_deliverer));
                break;
            }
        }

        if(order.getDeliverer() == null) {
            logger.warn("No deliverer available for order with id '" + order.getId_order() + "'");
        }
        else {
            logger.debug("Deliverer '" + order.getDeliverer().getFullName() +
                    "' will deliver the order with id '" + order.getId_order() + "'");
            order.getDeliverer().setBusy(true);
        }
    }


}

