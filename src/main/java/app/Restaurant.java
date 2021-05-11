package app;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private String delivery_price;
    private String score;
    private String delivery_time;
    private String description;
    private List<Product> products;

    public Restaurant(String name, String address, String delivery_price, String score, String delivery_time, String description) {
        this.name = name;
        this.address = address;
        this.delivery_price = delivery_price;
        this.score = score;
        this.delivery_time = delivery_time;
        this.description = description;
        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name + " " + address;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDelivery_price() {
        return delivery_price;
    }

    public String getScore() {
        return score;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }
}
