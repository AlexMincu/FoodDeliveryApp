package app;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadService {
    private static ReadService single_instance = null;

    private ReadService() {

    }

    public static ReadService getInstance() {
        if(single_instance == null)
            single_instance = new ReadService();
        return single_instance;
    }

    public void importAccounts(Map<String, Account> accounts){
        try {
            Scanner reader = new Scanner(new File("accounts.csv"));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] field = line.split(",");
                accounts.put(field[2], new Account(field[2], field[0], field[1], field[4], field[3], field[5]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("A problem has occurred when importing the accounts");
        }
    }

    public void importRestaurants(Map<Integer, Restaurant> locals){
        try {
            Scanner reader = new Scanner(new File("restaurants.csv"));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] field = line.split(",");
                locals.put(Integer.parseInt(field[0]), new Restaurant(field[1], field[2], field[3], field[4], field[5], field[6]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("A problem has occurred when importing the restaurants");
        }
    }

    public void importProducts(Map<Integer, Restaurant> locals) {
        try {
            Scanner reader = new Scanner(new File("products.csv"));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] field = line.split(",");
                locals.get(Integer.parseInt(field[0])).addProduct(new Product(field[1], field[2], field[3]));
            }

        } catch (FileNotFoundException e) {
            System.out.println("A problem has occurred when importing the products");
        }
    }

    public void importDeliverers(List<Deliverer> deliverers) {
        try {
            Scanner reader = new Scanner(new File("deliverers.csv"));
            while(reader.hasNextLine()) {
                String line = reader.nextLine();

                String[] field = line.split(",");

                Deliverer.Vehicle vehicle = switch (field[3]) {
                    case "BICYCLE" -> Deliverer.Vehicle.BICYCLE;
                    case "MOTORCYCLE" -> Deliverer.Vehicle.MOTORCYCLE;
                    case "CAR" -> Deliverer.Vehicle.CAR;
                    default -> Deliverer.Vehicle.NONE;
                };
                deliverers.add(new Deliverer(field[0], field[1], field[2], vehicle));
            }

        } catch (FileNotFoundException e) {
            System.out.println("A problem has occurred when importing the deliverers");
        }
    }
}