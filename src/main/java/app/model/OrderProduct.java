package app.model;

public class OrderProduct {
    private Product product;
    private int quantity;
    private double price;

    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getPrice() * quantity;
    }


    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.price = product.getPrice() * quantity;
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Name: " + product.getName() + "\n" +
                "Price: " + product.getPrice() + " * " + quantity + " = " + price + "\n";
    }

}
