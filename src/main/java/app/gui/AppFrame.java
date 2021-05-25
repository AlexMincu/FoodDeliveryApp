/*
 * Created by JFormDesigner on Sun May 23 17:16:38 EEST 2021
 */

package app.gui;


import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class AppFrame extends JFrame {
    private static AppFrame single_instance = null;
    private final Logger logger = LogManager.getLogger(AppFrame.class);

    public static AppFrame getInstance() {
        if (single_instance == null)
            single_instance = new AppFrame();
        return single_instance;
    }

    private int selected_restaurant_id = -1;
    boolean welcome_page_created = false;
    boolean login_page_created = false;
    boolean register_page_created = false;
    boolean restaurants_page_created = false;
    boolean products_pages_created = false;
    boolean cart_page_created = false;
    boolean account_page_created = false;

    private JPanel contentPane = new JPanel();
    private CardLayout layout = new CardLayout();

    private AppFrame() {
        logger.debug("Creating Application Frame");
        initComponents();

        setPreferredSize(new Dimension(800, 600));

        setIconImage(new ImageIcon("src/main/resources/champions_icon_small.png").getImage());

        contentPane.setLayout(layout);
        createWelcomePage();
        add(contentPane);
        pack();
        setVisible(true);

        logger.debug("Application frame created successfully");
    }

    public int getSelected_restaurant_id() {
        return selected_restaurant_id;
    }

    public void setSelected_restaurant_id(int selected_restaurant_id) {
        this.selected_restaurant_id = selected_restaurant_id;
    }


    public void createWelcomePage() {
        contentPane.add(new WelcomePanel(), "welcome");
        welcome_page_created = true;
    }

    public void createLoginPage() {
        contentPane.add(new LoginPanel(), "login");
        login_page_created = true;
    }

    public void createRegisterPage() {
        contentPane.add(new RegisterPanel(), "register");
        register_page_created = true;
    }

    public void createRestaurantsPage() {
        contentPane.add(new RestaurantsPanel(), "restaurants");
        restaurants_page_created = true;
    }

    public void createProductsPages() {
        Service service = Service.getInstance();
        for (var id_restaurant : service.getRestaurants().keySet()) {
            contentPane.add(new ProductsPanel(service.getRestaurants().get(id_restaurant)), "products_" + id_restaurant);
        }
        products_pages_created = true;
    }

    public void createCartPage() {
        contentPane.add(new CartPanel(), "cart");
        cart_page_created = true;
    }

    public void createAccountPage() {
        contentPane.add(new AccountPanel(), "account");
        account_page_created = true;
    }


    public void openWelcomePage() {
        if (!welcome_page_created)
            createWelcomePage();

        layout.show(contentPane, "welcome");
    }

    public void openLoginPage() {
        if (!login_page_created)
            createLoginPage();

        layout.show(contentPane, "login");
    }

    public void openRegisterPage() {
        if (!register_page_created)
            createRegisterPage();

        layout.show(contentPane, "register");
    }

    public void openRestaurantsPage() {
        if (!restaurants_page_created)
            createRestaurantsPage();

        layout.show(contentPane, "restaurants");
    }

    public void openProductsPage() {
        if (!products_pages_created)
            createProductsPages();

        layout.show(contentPane, "products_" + selected_restaurant_id);
    }

    public void openCartPage() {
        if (!cart_page_created)
            createCartPage();

        layout.show(contentPane, "cart");
    }

    public void openAccountPage() {
        if (!account_page_created)
            createAccountPage();

        layout.show(contentPane, "account");
    }


    public void closeWelcomePage() {
        welcome_page_created = false;
    }

    public void closeLoginPage() {
        login_page_created = false;
    }

    public void closeRegisterPage() {
        register_page_created = false;
    }

    public void closeRestaurantsPage() {
        restaurants_page_created = false;
    }

    public void closeProductsPage() {
        products_pages_created = false;
    }

    public void closeCartPage() {
        cart_page_created = false;
    }

    public void closeAccountPage() {
        account_page_created = false;
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown

        //======== this ========
        setTitle("Champion's Food");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setSize(800, 600);
        setLocationRelativeTo(null);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
