package app.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private String description;

    private double delivery_price;
    private int delivery_time;
    private double score;

    private List<Product> products;


    public Restaurant(String name, String address, String description, double delivery_price, int delivery_time, double score) {
        this.name = name;
        this.address = address;
        this.delivery_price = delivery_price;
        this.score = score;
        this.delivery_time = delivery_time;
        this.description = description;
        this.products = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public double getDelivery_price() {
        return delivery_price;
    }

    public int getDelivery_time() {
        return delivery_time;
    }

    public double getScore() {
        return score;
    }

    public List<Product> getProducts() {
        return products;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDelivery_price(double delivery_price) {
        this.delivery_price = delivery_price;
    }

    public void setDelivery_time(int delivery_time) {
        this.delivery_time = delivery_time;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return  "        Restaurant information:        \n" +
                "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Description: " + description + "\n" +
                "Delivery price: " + delivery_price + "\n" +
                "Delivery time: " + delivery_time + "\n" +
                "Score: " + score + "\n";
    }


    public void addProduct(Product product) {
        products.add(product);
    }
}
