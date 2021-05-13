package app.model;

import java.util.List;

public class Order {
    public enum Status {UNKNOWN, CONFIRMED, DELIVERING, DELIVERED, CANCELED}

    private Account account;
    private String address;
    private List<OrderProduct> order_list;
    private double price;
    private Deliverer deliverer;
    private Status status;


    public Order(Account account, String address, List<OrderProduct> order_list) {
        this.account = account;
        this.address = address;
        this.order_list = order_list;
        double sum = 0;
        for(var listed_product : order_list)
            sum += listed_product.getPrice();

        this.price = sum;
        this.deliverer = null;
        this.status = Status.UNKNOWN;
    }


    public Account getAccount() {
        return account;
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


    public void setAccount(Account account) {
        this.account = account;
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


    @Override
    public String toString() {
        String string =
                "        Order information:        \n" +
                "----------------------------------\n" +
                "Name: " + account.getName() + " " + account.getSurname() + "\n" +
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

}
