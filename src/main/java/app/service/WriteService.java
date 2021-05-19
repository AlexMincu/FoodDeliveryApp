//package app.service;
//
//import app.model.Account;
//import app.model.Deliverer;
//import app.model.Restaurant;
//
//import java.io.*;
//import java.util.List;
//import java.util.Map;
//
//public class WriteService {
//    private static WriteService single_instance = null;
//
//    private WriteService () {
//    }
//
//    public static WriteService getInstance() {
//        if(single_instance == null)
//            single_instance = new WriteService();
//        return single_instance;
//    }
//
//
//    public void exportAccounts(Map<String, Account> accounts){
//        try {
//            FileWriter writer = new FileWriter("accounts.csv");
//
//            for(var email : accounts.keySet()) {
//                writer.write(accounts.get(email).getEmail() + ",");
//                writer.write(accounts.get(email).getPassword() + ",");
//                writer.write(accounts.get(email).getName() + ",");
//                writer.write(accounts.get(email).getSurname() + ",");
//                writer.write(accounts.get(email).getPhoneNo() + ",");
//                writer.write(accounts.get(email).getAddress() + "\n");
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("A problem has occurred when exporting the accounts");
//        }
//    }
//
//    public void exportRestaurants(Map<Integer, Restaurant> locals){
//        try {
//            FileWriter writer = new FileWriter("restaurants.csv");
//
//            for(var id : locals.keySet()) {
//                writer.write(id.toString() + ",");
//                writer.write(locals.get(id).getName() + ",");
//                writer.write(locals.get(id).getAddress() + ",");
//                writer.write(locals.get(id).getDescription() + ",");
//                writer.write(locals.get(id).getDelivery_price() + ",");
//                writer.write(locals.get(id).getDelivery_time() + ",");
//                writer.write(locals.get(id).getScore() + "\n");
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("A problem has occurred when exporting the restaurants");
//        }
//    }
//
//    public void exportProducts(Map<Integer, Restaurant> locals) {
//        try {
//            FileWriter writer = new FileWriter("products.csv");
//
//            for(var id : locals.keySet()) {
//                for(var product : locals.get(id).getProducts()) {
//                    writer.write(id.toString() + ",");
//                    writer.write(product.getName() + ",");
//                    writer.write(product.getPrice() + ",");
//                    writer.write(product.getDescription() + "\n");
//                }
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("A problem has occurred when exporting the products");
//        }
//    }
//
//    public void exportDeliverers(List<Deliverer> deliverers) {
//        try {
//            FileWriter writer = new FileWriter("deliverers.csv");
//
//            for(var deliverer : deliverers) {
//                writer.write(deliverer.getName() + ",");
//                writer.write(deliverer.getSurname() + ",");
//                writer.write(deliverer.getPhoneNo() + ",");
//                writer.write(deliverer.getVehicle().toString() + "\n");
//            }
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("A problem has occurred when exporting the deliverers");
//        }
//    }
//}