package app.gui;

import app.model.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProductsPage extends JFrame{
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JLabel timeLabel;
    private JLabel priceLabel;
    private JLabel scoreLabel;
    private JLabel descriptionLabel;
    private JPanel localPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JButton cartButton;

    public ProductsPage(Restaurant restaurant) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(450, 800);
        this.setResizable(false);
//        this.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2 - this.getWidth()/2, 0);
        this.pack();
        this.setVisible(true);

        Logger logger = LogManager.getLogger(ProductsPage.class);
        logger.info("Initiated page of restaurant: " + restaurant.getName());


        titleLabel.setText(restaurant.getName());
        timeLabel.setText(String.valueOf(restaurant.getDelivery_time()));
        priceLabel.setText(String.valueOf(restaurant.getDelivery_price()));
        scoreLabel.setText(String.valueOf(restaurant.getScore()));
        descriptionLabel.setText(restaurant.getDescription());


        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Insert products panels
        for(var product : restaurant.getProducts()) {
            ProductPane p = new ProductPane(product);
            contentPanel.add(p);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        validate();


        backButton.addActionListener(e -> {
            new RestaurantsPage();
            dispose();
        });

        cartButton.addActionListener(e -> {
            new CartPage(restaurant);
            dispose();
        });


        // Export everything when closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("Closing app");
                dispose();
                System.exit(0);
            }
        });
    }
}
