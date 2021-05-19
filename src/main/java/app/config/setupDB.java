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
        String accountTable =
                "CREATE TABLE IF NOT EXISTS `account` (" +
                        "`email`		varchar(50) 	NOT NULL," +
                        "`password`		varchar(50) 	NOT NULL," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`surname`		varchar(50) 	NOT NULL," +
                        "`phoneNo`		varchar(15)		NOT NULL," +
                        "`address`		varchar(50)		NOT NULL," +

                        "PRIMARY KEY (`email`) )";

        String delivererTable =
                "CREATE TABLE IF NOT EXISTS `deliverer` (" +
                        "`id_deliverer` int 		NOT NULL AUTO_INCREMENT," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`surname`		varchar(50) 	NOT NULL," +
                        "`phoneNo`		varchar(15)		NOT NULL," +
                        "`vehicle`		varchar(50)		NOT NULL," +
                        "`busy`			boolean 		DEFAULT FALSE," +

                        "PRIMARY KEY (`id_deliverer`) )";

        String restaurantTable =
                "CREATE TABLE IF NOT EXISTS `restaurant` (" +
                        "`id_restaurant` int 			NOT NULL AUTO_INCREMENT," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`address`			varchar(50) 	NOT NULL," +
                        "`description`	varchar(200) 	DEFAULT NULL," +

                        "`delivery_price` double		NOT NULL," +
                        "`delivery_time`	int		NOT NULL," +
                        "`score`			double		NOT NULL," +

                        "PRIMARY KEY (`id_restaurant`) )";

        String productTable =
                "CREATE TABLE IF NOT EXISTS `product` (" +
                        "`id_product` 	int 			NOT NULL AUTO_INCREMENT," +
                        "`id_restaurant`	int				NOT NULL," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`price`			double 	NOT NULL," +
                        "`description`	varchar(200) 	DEFAULT NULL," +

                        "PRIMARY KEY (`id_product`)," +

                        "CONSTRAINT `fk-product-restaurant` " +
                        "FOREIGN KEY (`id_restaurant`) " +
                        "REFERENCES `restaurant` (`id_restaurant`) )";

        String orderTable =
                "CREATE TABLE IF NOT EXISTS `order` (" +
                        "`id_order` 		int 			NOT NULL AUTO_INCREMENT," +
                        "`email`	        varchar(50)		NOT NULL," +
                        "`address`	        varchar(50)		NOT NULL," +
                        "`id_deliverer`	    int				DEFAULT NULL," +

                        "`total_price`	double  	NOT NULL," +
                        "`status`		varchar(50)		NOT NULL," +

                        "PRIMARY KEY (`id_order`)," +

                        "CONSTRAINT `fk-order-account` " +
                        "FOREIGN KEY (`email`) " +
                        "REFERENCES `account` (`email`)," +

                        "CONSTRAINT `fk-order-deliverer` " +
                        "FOREIGN KEY (`id_deliverer`) " +
                        "REFERENCES `deliverer` (`id_deliverer`))";

        String orderProductTable =
                "CREATE TABLE IF NOT EXISTS `order_product` (" +
                        "`id_order` int 			NOT NULL AUTO_INCREMENT," +
                        "`id_product`	int				NOT NULL," +

                        "`quantity`		int		NOT NULL," +
                        "`total_price`	double 	NOT NULL," +

                        "PRIMARY KEY (`id_order`, `id_product`)," +

                        "CONSTRAINT `fk-order_product-order` " +
                        "FOREIGN KEY (`id_order`) " +
                        "REFERENCES `order` (`id_order`)," +

                        "CONSTRAINT `fk-order_product-product`  " +
                        "FOREIGN KEY (`id_product`)  " +
                        "REFERENCES `product` (`id_product`))";

        Connection dataBaseConnection = SqlConfig.getDataBaseConnection();

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
}
