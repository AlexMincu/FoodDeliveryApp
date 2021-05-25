package app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class setupDB {
    private static final Logger logger = LogManager.getLogger(setupDB.class);

    private setupDB() {
    }

    public static void initDB() {
        String createDB = "CREATE DATABASE IF NOT EXISTS `deliveryDB` ";

        String useDB = "USE `deliveryDB` ";

        String accountTable =
                "CREATE TABLE IF NOT EXISTS `account` (" +
                        "`email`		varchar(50) 	NOT NULL," +
                        "`password`		varchar(50) 	NOT NULL," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`surname`		varchar(50) 	NOT NULL," +
                        "`phoneNo`		varchar(15)		NOT NULL," +

                        "PRIMARY KEY (`email`)" +
                        ")";

        String delivererTable =
                "CREATE TABLE IF NOT EXISTS `deliverer` (" +
                        "`id_deliverer` int 		    NOT NULL AUTO_INCREMENT," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`surname`		varchar(50) 	NOT NULL," +
                        "`phoneNo`		varchar(15)		NOT NULL," +
                        "`vehicle`		varchar(50)		NOT NULL," +
                        "`busy`			boolean 		DEFAULT FALSE," +

                        "PRIMARY KEY (`id_deliverer`)" +
                        ")";

        String restaurantTable =
                "CREATE TABLE IF NOT EXISTS `restaurant` (" +
                        "`id_restaurant`    int 			NOT NULL AUTO_INCREMENT," +
                        "`name`			    varchar(50) 	NOT NULL," +
                        "`address`			varchar(100) 	NOT NULL," +
                        "`description`	    varchar(200) 	DEFAULT NULL," +

                        "`delivery_price`   double		    NOT NULL," +
                        "`delivery_time`	int		        NOT NULL," +
                        "`score`			double		    NOT NULL," +

                        "PRIMARY KEY (`id_restaurant`)" +
                        ")";

        String productTable =
                "CREATE TABLE IF NOT EXISTS `product` (" +
                        "`id_product` 	    int 			NOT NULL AUTO_INCREMENT," +
                        "`id_restaurant`	int				NOT NULL," +
                        "`name`			    varchar(50) 	NOT NULL," +
                        "`price`			double 	        NOT NULL," +
                        "`description`	    varchar(200) 	DEFAULT NULL," +

                        "PRIMARY KEY (`id_product`)," +

                        "CONSTRAINT `fk-product-restaurant` " +
                        "FOREIGN KEY (`id_restaurant`) " +
                        "REFERENCES `restaurant` (`id_restaurant`) " +
                        "ON UPDATE CASCADE " +
                        "ON DELETE CASCADE " +
                        ")";

        String orderTable =
                "CREATE TABLE IF NOT EXISTS `order` (" +
                        "`id_order` 		int 			NOT NULL AUTO_INCREMENT," +
                        "`email`	        varchar(50)		DEFAULT NULL," +
                        "`address`	        varchar(100)	NOT NULL," +
                        "`id_deliverer`	    int				DEFAULT NULL," +

                        "`total_price`	    double  	    NOT NULL," +
                        "`status`		    varchar(50)		NOT NULL," +

                        "PRIMARY KEY (`id_order`)," +

                        "CONSTRAINT `fk-order-account` " +
                        "FOREIGN KEY (`email`) " +
                        "REFERENCES `account` (`email`) " +
                        "ON UPDATE CASCADE " +
                        "ON DELETE CASCADE, " +

                        "CONSTRAINT `fk-order-deliverer` " +
                        "FOREIGN KEY (`id_deliverer`) " +
                        "REFERENCES `deliverer` (`id_deliverer`) " +
                        "ON UPDATE CASCADE " +
                        "ON DELETE SET NULL " +
                        ")";

        String orderProductTable =
                "CREATE TABLE IF NOT EXISTS `order_product` (" +
                        "`id_order`     int 		NOT NULL AUTO_INCREMENT," +
                        "`id_product`	int			NOT NULL," +

                        "`quantity`		int		    NOT NULL," +
                        "`total_price`	double 	    NOT NULL," +

                        "PRIMARY KEY (`id_order`, `id_product`)," +

                        "CONSTRAINT `fk-order_product-order` " +
                        "FOREIGN KEY (`id_order`) " +
                        "REFERENCES `order` (`id_order`) " +
                        "ON UPDATE CASCADE " +
                        "ON DELETE CASCADE, " +

                        "CONSTRAINT `fk-order_product-product`  " +
                        "FOREIGN KEY (`id_product`)  " +
                        "REFERENCES `product` (`id_product`) " +
                        "ON UPDATE CASCADE " +
                        "ON DELETE CASCADE " +
                        ")";

        Connection dataBaseConnection = SqlConfig.getDataBaseConnection();

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.executeUpdate(createDB);
            statement.executeUpdate(useDB);
            logger.debug("Database successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(accountTable);
            logger.debug("Account table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(delivererTable);
            logger.debug("Deliverer table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(restaurantTable);
            logger.debug("Restaurant table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(productTable);
            logger.debug("Product table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(orderTable);
            logger.debug("Order table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(orderProductTable);
            logger.debug("OrderList table successfully created");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }

        logger.info("DB successfully initiated");
    }

    public static void dropDB() {
        String dropDB = "DROP DATABASE IF EXISTS `deliveryDB` ";

        Connection dataBaseConnection = SqlConfig.getDataBaseConnection();

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.executeUpdate(dropDB);
            logger.debug("Database successfully removed");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }

    /**
     * This function should be used only once when DB is empty or it might duplicate data and cause errors
     */
    public static void addDummyData2DB() {
        String dummyDataAccounts =
                "INSERT INTO `account` (email, password, name, surname, phoneNo) " +
                        "VALUES  ('admin', 'admin', 'NumeAdmin', 'PrenumeAdmin', '0721234567'), " +
                        "('fabian_codrin@gmail.com', 'parola123', 'Fabian', 'Codrin', '0724589653'), " +
                        "('lazar_marcel@gmail.com', 'parola123', 'Lazar', 'Marcel', '0735698425'), " +
                        "('roxana_nicolae@yahoo.com', 'parola123', 'Roxana', 'Nicolae', '0739654812') ";

        String dummyDataDeliverers =
                "INSERT INTO `deliverer` (name, surname, phoneNo, vehicle, busy) " +
                        "VALUES  ('David', 'Sorina', '0735891265', 'CAR', FALSE), " +
                        "('Dumitru', 'Bogdan', '0724586951', 'MOTORCYCLE', TRUE), " +
                        "('Gavril', 'Cristian', '0734568291', 'CAR', FALSE), " +
                        "('Octavian', 'Horia', '0732569842', 'CAR', FALSE), " +
                        "('Petre', 'Radu', '0724568213', 'BICYCLE', FALSE) ";

        String dummyDataRestaurant =
                "INSERT INTO `restaurant` (name, address, description, delivery_price, delivery_time, score) " +
                        "VALUES  ('Edo Sushi', 'Calea Stirbei Voda 84A, 10117 Bucharest', 'Bucatarie Japoneza', 9.99, 60, 4.6), " +
                        "('Hello Donuts Cocor', 'Radu Calomfirescu 6, 61346 Bucharest', 'Cofetarie & Patiserie', 4.99, 45, 4.9), " +
                        "('Mediterrano', 'B-dul Pache Protopopescu nr. 28, sector 2, corp A, colț cu strada Traian. 021413, 021413 Bucharest', 'Bucatarie Mediteraneana', 8.99, 50, 4.4) ";

        String dummyDataProducts =
                "INSERT INTO `product` (id_restaurant, name, price, description) " +
                        "VALUES  (1, 'Philadelphia Maki', 33, 'somon, crema de branza, castravete - 8 pcs. - 240 g'), " +
                        "(1, 'Spicy Tuna Maki', 33, 'ton picant, avocado, susan, maio - 6 pcs. 240 g'), " +
                        "(1, 'Salmon Mix (20 pcs.)', 91, 'sake delight maki (4 p), sake maki (6 p), sake delight gunkan (1 p), sake nigiri (2 p), philadelphia maki (4 p), sake delight maki (3 p) - 545 g'), " +
                        "(1, 'Balanced Mix (16 pcs)', 79, 'Tiger roll (5 pcs.), Rainbow Maki (4 pcs.), Philadelphia Maki (4 pcs.), California ebi tobikko (3 pcs.) - 430 g'), " +
                        "(1, 'Wakame Salad', 15, 'salata alge - 100 g'), " +
                        "(1, 'Edamame', 19, 'pastai soia cu sare - 200 g'), " +
                        "" +
                        "(2, 'Chocolate Kiss', 6.08, 'Umplutura crema de ciocolata fondante si glazura de ciocolata'), " +
                        "(2, 'Strawberry Madness', 6.08, 'Umplutura fructata de capsune si glazura de capsune si sprinkles'), " +
                        "(2, 'White Sin', 7.60, 'memorabilul HelloDonuts cu glazura de ciocolata alba, umplutura de vanilie si presarat cu biscuiti Oreo'), " +
                        "(2, 'Vanilla Crunch', 6.08, 'Umplutura crema de vanilie si glazura de ciocolata lapte si alune'), " +
                        "(2, '6 Donuts', 36.48, 'Vanilla crunch, white sin, caramel supreme, strawberry, marshmallow, chocolate kiss'), " +
                        "(2, '4 Donuts', 24.32, 'Vanilla crunch, white sin, strawberry, chocolate kiss'), " +
                        "" +
                        "(3, 'Gyros de pui', 18, 'Pita grecească, carne de pui, roșii, ceapă, tzatziki, tirokafteri și cartofi prăjiți - 390g'), " +
                        "(3, 'Gyros vegetarian', 16, 'Pita grecească, roșii, ceapă, ardei gras, castraveți fresh, hummus și cartofi prăjiți - 380g'), " +
                        "(3, 'Greek grill', 49, 'Pite grecești, frigăruie de pui, frigăruie de porc, frigăruie de pui cu bacon, burger grecesc, cârnat grecesc, roșii, ceapă, tzatziki, tirokafteri, muștar și carfofi prăjiți - 840g'), " +
                        "(3, 'Cartofi prăjiți', 5, '150g'), " +
                        "(3, 'Greek kebab de vită', 20, 'Pita grecească, carne de vită, roșii, ceapă, tzatziki, tirokafteri și cartofi prăjiți - 390g') ";

        String dummyDataOrders =
                "INSERT INTO `order` (email, address, id_deliverer, total_price, status) " +
                        "VALUES  ('fabian_codrin@gmail.com', 'BD. BRĂTIANU I. C. nr. 34 bl. 15, BUCHAREST - DISTRICT 3, 30175', 2, 18.24, 'DELIVERING'), " +
                        "('lazar_marcel@gmail.com', 'BD. UNIRII nr. 33 bl. 24, BUCHAREST - DISTRICT 5', 1, 36, 'DELIVERED'), " +
                        "('roxana_nicolae@yahoo.com', 'BD. UNIRII nr. 25 bl. 14 sc. A et 4 ap. 14, BUCHAREST - DISTRICT 5, 040103', 4, 155, 'DELIVERED') ";

        String dummyDataOrderProducts =
                "INSERT INTO `order_product` (id_order, id_product, quantity, total_price) " +
                        "VALUES  (1, 7, 2, 12.16), " +
                        "(1, 8, 1, 6.08), " +
                        "(2, 13, 2, 36), " +
                        "(3, 3, 1, 91), " +
                        "(3, 5, 3, 45)," +
                        "(3, 6, 1, 19) ";

        Connection dataBaseConnection = SqlConfig.getDataBaseConnection();

        try {
            Statement statement = dataBaseConnection.createStatement();
            statement.execute(dummyDataAccounts);
            statement.execute(dummyDataDeliverers);
            statement.execute(dummyDataRestaurant);
            statement.execute(dummyDataProducts);
            statement.execute(dummyDataOrders);
            statement.execute(dummyDataOrderProducts);
            logger.debug("Dummy Data successfully inserted");
        } catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }
}
