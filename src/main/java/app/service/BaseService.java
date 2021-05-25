package app.service;

import app.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class BaseService {
    protected Account currentAccount;
    protected Map<String, Account> accounts;
    protected Map<Integer, Restaurant> restaurants;
    protected Map<Integer, Deliverer> deliverers;
    protected List<OrderProduct> cart;

    private final Logger logger = LogManager.getLogger(BaseService.class);

    protected BaseService() {
        this.currentAccount = null;
        this.accounts = new HashMap<>();
        this.restaurants = new HashMap<>();
        this.deliverers = new HashMap<>();
        this.cart = new ArrayList<>();
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
}
