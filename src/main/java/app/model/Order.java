package app.model;

import java.util.List;

public class Order {
    public enum Status {UNKNOWN, CONFIRMED, DELIVERING, DELIVERED, CANCELED}

    private int id_order;
    private String email;
    private String address;
    private List<OrderProduct> order_list;
    private double price;
    private Deliverer deliverer;
    private Status status;


    public Order(String email, String address, List<OrderProduct> order_list) {
        this.email = email;
        this.address = address;
        this.order_list = order_list;
        double sum = 0;
        for(var listed_product : order_list)
            sum += listed_product.getPrice();

        this.price = sum;
        this.deliverer = null;
        this.status = Status.UNKNOWN;
    }


    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public List<OrderProduct> getOrder_list() {
        return order_list;
    }

    public double getPrice() {
        return price;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }

    public Status getStatus() {
        return status;
    }

    public int getId_order() {
        return id_order;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOrder_list(List<OrderProduct> order_list) {
        this.order_list = order_list;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    @Override
    public String toString() {
        String string =
                "        Order information:        \n" +
                "----------------------------------\n" +
                "Order ID: " + id_order + "\n" +
                "Email: " + email + "\n" +
                "Address: " + address + "\n" +
                "Price: " + price + "\n" +
                "Status: " + status + "\n" +
                "Deliverer: " + deliverer + "\n" +
                "Products: \n";

        for(var product : order_list)
            string = string.concat(product.toString() + "\n");

        string = string.concat("----------------------------------\n");

        return string;
    }

    public static Order.Status toStatus(String statusString) {
        return switch (statusString) {
            case "CONFIRMED" -> Status.CONFIRMED;
            case "DELIVERING" -> Status.DELIVERING;
            case "DELIVERED" -> Status.DELIVERED;
            case "CANCELED" -> Status.CANCELED;
            default -> Status.UNKNOWN;
        };
    }

}
