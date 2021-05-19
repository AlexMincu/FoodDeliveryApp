package app.model;

public class Product {
    protected int id_product;
    protected String name;
    protected double price;
    protected String description;


    public Product(int id_product, String name, double price, String description) {
        this.id_product = id_product;
        this.name = name;
        this.price = price;
        this.description = description;
    }


    public int getId_product() {
        return id_product;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }


    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Price: " + price + "\n" +
                "Description: " + description + "\n";
    }

}
