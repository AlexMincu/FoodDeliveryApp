package app.model;

public class Product {
    protected String name;
    protected double price;
    protected String description;


    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
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
