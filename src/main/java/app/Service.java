package app;

import app.gui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class Service {
    private static Service single_instance = null;
    private Account currentAccount;
    private Map<String, Account> accounts;
    private Map<Integer, Restaurant> locals;
    private List<Deliverer> deliverers;
    private Map<String, Product> cart;
    private Map<String, Integer> cartSize;

    // NEEDS REVAMP
//    private Receipt receipt;
//    private List<Delivery> deliveries;

    private Logger logger = LogManager.getLogger(Service.class);


    private Service() {
        this.currentAccount = null;
        this.accounts = new HashMap<>();
        this.locals = new HashMap<>();
        this.deliverers = new ArrayList<>();
        this.cart = new HashMap<>();
        this.cartSize = new HashMap<>();

        // NEEDS REVAMP
//        this.receipt = null;
//        this.deliveries = new ArrayList<>();
    }

    public static Service getInstance() {
        if(single_instance == null)
            single_instance = new Service();
        return single_instance;
    }

    // GUI and other useful methods
    public void startApp() {
        logger.info("Application is starting");
        if(currentAccount == null) {
            new LoginPage();
        }
        else {
            new RestaurantsPage();
        }
    }

    public void importAll() {
        logger.info("Importing settings");
        importAccounts();
        importRestaurants();
        importProducts();
        importDeliverers();
    }

    public void exportAll() {
        logger.info("Exporting settings");
        exportAccounts();
        exportRestaurants();
        exportProducts();
        exportDeliverers();
    }



    // Accounts
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
            accounts.put(email, new Account(email, name, surname, phoneNo, password, address));
        }
        else
            System.out.println("Email already taken");

        return check;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void importAccounts(){
        logger.debug("Importing accounts...");
        ReadService reader = ReadService.getInstance();
        reader.importAccounts(this.accounts);
    }

    public void exportAccounts() {
        logger.debug("Exporting accounts...");
        WriteService write = WriteService.getInstance();
        write.exportAccounts(this.accounts);
    }



    // Locals
    public void importRestaurants() {
        logger.debug("Importing locals...");
        ReadService reader = ReadService.getInstance();
        reader.importRestaurants(this.locals);
    }

    public void exportRestaurants() {
        logger.debug("Exporting locals...");
        WriteService write = WriteService.getInstance();
        write.exportRestaurants(this.locals);
    }

    public Map<Integer, Restaurant> getLocals() {
        return locals;
    }



    // Deliverers
    public void importDeliverers() {
        logger.debug("Importing deliverers...");
        ReadService reader = ReadService.getInstance();
        reader.importDeliverers(this.deliverers);
    }

    public void exportDeliverers() {
        logger.debug("Exporting deliverers...");
        WriteService write = WriteService.getInstance();
        write.exportDeliverers(this.deliverers);
    }



    // Products
    public void importProducts() {
        logger.debug("Importing products...");
        ReadService reader = ReadService.getInstance();
        reader.importProducts(this.locals);
    }

    public void exportProducts() {
        logger.debug("Exporting locals...");
        WriteService write = WriteService.getInstance();
        write.exportProducts(this.locals);
    }



    // Cart
    public void addToCart(Product product) {
        logger.debug("Adding product to cart - [" + product.getName() + "]");
        if(cart.get(product.getName()) == null){
            cart.put(product.getName(), product);
            cartSize.put(product.getName(), 1);
        }
        else {
            cartSize.replace(product.getName(), cartSize.get(product.getName()) + 1);
        }
    }

    public void removeFromCart(Product product){
        logger.debug("Removing product from cart - [" + product.getName() + "]");
        if(cart.get(product.getName()) == null) {
//            System.out.println("product doesnt appear to be in cart anymore");
        }
        else if(cartSize.get(product.getName()) <= 1) {
            cart.remove(product.getName());
            cartSize.remove(product.getName());
        }
        else {
            cartSize.replace(product.getName(), cartSize.get(product.getName()) - 1);
        }
    }

    public void flushCart() {
        if(!cart.isEmpty()) {
            logger.debug("Flushing cart");
            cart.clear();
        }
    }

    public Map<String, Product> getCart() {
        return cart;
    }
    public Map<String, Integer> getCartSize() {
        return cartSize;
    }
}
