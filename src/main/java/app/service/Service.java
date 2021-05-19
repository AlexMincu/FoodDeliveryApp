package app.service;

import app.gui.*;
import app.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class Service {
    private static Service single_instance = null;

    private Account currentAccount;
    private Map<String, Account> accounts;
    private Map<Integer, Restaurant> restaurants;
    private Map<Integer, Deliverer> deliverers;
    private List<OrderProduct> cart;

    private Logger logger = LogManager.getLogger(Service.class);
    private DatabaseService dbService = DatabaseService.getInstance();


    private Service() {
        this.currentAccount = null;
        this.accounts = new HashMap<>();
        this.restaurants = new HashMap<>();
        this.deliverers = new HashMap<>();
        this.cart = new ArrayList<>();
    }
    public static Service getInstance() {
        if(single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    // Getters
    public Account getCurrentAccount() {
        return currentAccount;
    }
    public Map<String, Account> getAccounts() {
        return accounts;
    }
    public Map<Integer, Deliverer> getDeliverers() {
        return deliverers;
    }
    public Map<Integer, Restaurant> getRestaurants() {
        return restaurants;
    }
    public List<OrderProduct> getCart() {
        return cart;
    }

    // Setters
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }
    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }
    public void setRestaurants(Map<Integer, Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    public void setDeliverers(Map<Integer, Deliverer> deliverers) {
        this.deliverers = deliverers;
    }
    public void setCart(List<OrderProduct> cart) {
        this.cart = cart;
    }


    // GUI and other useful methods
    public void startApp() {
        logger.info("Application is starting");

        dbService.connectDB();
        dbService.importFromDB();

        if(currentAccount == null) {
            new LoginPage();
        }
        else {
            new RestaurantsPage();
        }
    }


    // Account functions
    public Boolean login (String email, String password) {
        logger.debug("Logging in using the email: " + email);
        if(accounts.get(email).getPassword().equals(password)) {
            currentAccount = accounts.get(email);
            return true;
        }
        else {
            System.out.println("Wrong credentials");
            return false;
        }
    }

    public String register (String email, String name, String surname, String phoneNo, String password, String address){
        logger.debug("Trying to register an account using the email: " + email);
        char[] checks = new char[]{'1', '1', '1', '1', '1'};
        /*
            Returns:
                    0    - if the field is wrong
                    1    - if the field is correct
                "11111"  - account successfully created
         */


        // NOT FUNCTIONAL YET
//        if(email.matches("[^a-zA-Z0-9_@.]"))
//            checks[0] = '0';
//
//        if(name.matches("[^a-zA-Z ]+"))
//            checks[1] = '0';
//
//        if(surname.matches("[^a-zA-Z ]+"))
//            checks[2] = '0';
//
//        if(phoneNo.matches("[^0-9]+"))
//            checks[3] = '0';
//
//        if(password.matches("[^a-zA-Z0-9]+"))
//            checks[4] = '0';


        String check = new String(checks);
        if(check.equals("11111") && accounts.get(email) == null) {
            accounts.put(email, new Account(email, password, name, surname, phoneNo, address));
        }
        else
            System.out.println("Email already taken");

        return check;
    }


    // Cart functions
    public void addToCart(Product product) {
        logger.debug("Adding product to cart - [" + product.getName() + "]");

        boolean found = false;
        for(var p : cart)
            if(p.getProduct().equals(product)) {
                p.setQuantity(p.getQuantity() + 1);

                found = true;
                break;
            }

        if(!found)
            cart.add(new OrderProduct(product, 1));
    }

    public void removeFromCart(Product product){
        logger.debug("Removing product from cart - [" + product.getName() + "]");

        for(var p : cart)
            if(p.getProduct().equals(product)) {
                if(p.getQuantity() > 1)
                    p.setQuantity(p.getQuantity() - 1);
                else
                    cart.remove(p);

                break;
            }
    }

    public void flushCart() {
        if(!cart.isEmpty()) {
            logger.debug("Flushing cart");
            cart.clear();
        }
    }


    // Order functions
    public void createOrder(String address) {
        Order order = new Order(currentAccount.getEmail(), address, cart);
        System.out.println(order);

        logger.info("Order created successfully");
    }
}
