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
                        "`id_account` 	int 			NOT NULL AUTO_INCREMENT," +
                        "`email`			varchar(50) 	NOT NULL," +
                        "`password`		varchar(50) 	NOT NULL," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`surname`		varchar(50) 	NOT NULL," +
                        "`phoneNo`		varchar(15)		NOT NULL," +
                        "`address`		varchar(50)		NOT NULL," +

                        "PRIMARY KEY (`id_account`) )";

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
                        "`description`	varchar(200) 	DEFAULT NULL," +

                        "`delivery_price` varchar(5)		NOT NULL," +
                        "`delivery_time`	varchar(5)		NOT NULL," +
                        "`score`			varchar(5)		NOT NULL," +

                        "PRIMARY KEY (`id_restaurant`) )";

        String productTable =
                "CREATE TABLE IF NOT EXISTS `product` (" +
                        "`id_product` 	int 			NOT NULL AUTO_INCREMENT," +
                        "`id_restaurant`	int				NOT NULL," +
                        "`name`			varchar(50) 	NOT NULL," +
                        "`price`			varchar(10) 	DEFAULT NULL," +
                        "`description`	varchar(200) 	DEFAULT NULL," +

                        "PRIMARY KEY (`id_product`)," +

                        "CONSTRAINT `fk-product-restaurant` " +
                        "FOREIGN KEY (`id_restaurant`) " +
                        "REFERENCES `restaurant` (`id_restaurant`) )";

        String orderListTable =
                "CREATE TABLE IF NOT EXISTS `order_list` (" +
                        "`id_order_list` int 			NOT NULL AUTO_INCREMENT," +
                        "`id_product`	int				NOT NULL," +

                        "`quantity`		varchar(10)		NOT NULL," +
                        "`total_price`	varchar(10) 	NOT NULL," +

                        "PRIMARY KEY (`id_order_list`)," +

                        "CONSTRAINT `fk-order_list-product` " +
                        "FOREIGN KEY (`id_product`) " +
                        "REFERENCES `product` (`id_product`))";

        String orderTable =
                "CREATE TABLE IF NOT EXISTS `order` (" +
                        "`id_order` 		int 			NOT NULL AUTO_INCREMENT," +
                        " `id_account`	int				NOT NULL," +
                        " `id_deliverer`	int				NOT NULL," +
                        "`id_order_list`	int				NOT NULL," +

                        "`total_price`	varchar(20) 	NOT NULL," +
                        "`status`		varchar(50)		NOT NULL," +

                        "PRIMARY KEY (`id_order`)," +

                        "CONSTRAINT `fk-order-account` " +
                        "FOREIGN KEY (`id_account`) " +
                        "REFERENCES `account` (`id_account`)," +

                        "CONSTRAINT `fk-order-deliverer` " +
                        "FOREIGN KEY (`id_deliverer`) " +
                        "REFERENCES `deliverer` (`id_deliverer`)," +

                        "CONSTRAINT `fk-order-order_list` " +
                        "FOREIGN KEY (`id_order_list`) " +
                        "REFERENCES `order_list` (`id_order_list`) )";

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
            statement.execute(orderListTable);
            logger.debug("OrderList table successfully created");
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

        logger.info("DB successfully initiated");
    }
}
