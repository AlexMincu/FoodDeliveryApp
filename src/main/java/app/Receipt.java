/*
        NEEDS REVAMP
 */

package app;

import java.util.List;

public class Receipt {
    private Restaurant restaurant;
    private List<Product> products;

    public Receipt(Restaurant restaurant, List<Product> products) {
        this.restaurant = restaurant;
        this.products = products;
    }

    @Override
    public String toString() {
        String s =  "--------------------------------------------\n" +
                    "       Receipt: \n" +
                    "       From " + restaurant.getName() + "\n" +
                    "          " + restaurant.getAddress() + "\n" +
                    "--------------------------------------------\n";

        double totalPrice = 0;
        for(int productIndex = 0; productIndex < products.size(); productIndex++) {
            s = s.concat((productIndex + 1) + ": " + products.get(productIndex).toString() + "\n");
            totalPrice += Double.parseDouble(products.get(productIndex).getPrice());
        }

        s = s.concat("              Total: " + totalPrice + " RON\n" +
                     "--------------------------------------------\n");

        return s;
    }
}
