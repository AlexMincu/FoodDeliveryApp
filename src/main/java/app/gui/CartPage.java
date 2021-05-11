package app.gui;

import app.Restaurant;
import app.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CartPage extends JFrame{
    private JPanel contentPanel;
    private JPanel mainPanel;
    private JPanel deliveryPanel;
    private JLabel kartLabel;
    private JLabel deliveryLabel;
    private JComboBox addressComboBox;
    private JTextField newAddressField;
    private JLabel addressLabel;
    private JLabel paymentLabel;
    private JComboBox paymentComboBox;
    private JButton backButton;
    private JLabel paymentProductsLabel;
    private JLabel paymentDeliveryLabel;
    private JLabel paymentTotalLabel;
    private JLabel paymentProductsValueLabel;
    private JLabel paymentDeliveryValueLabel;
    private JLabel paymentTotalValueLabel;
    private JButton confirmButton;
    private JButton refreshButton;

    CartPage(Restaurant restaurant) {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(450, 800);
        this.setResizable(false);
//        this.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2 - this.getWidth()/2, 0);
        this.pack();
        this.setVisible(true);
        Service service = Service.getInstance();

        Logger logger = LogManager.getLogger(CartPage.class);
        logger.info("Initiated page");

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        newAddressField.setText(service.getCurrentAccount().getAddress());

        var cart = service.getCart();
        var cartSize = service.getCartSize();

        for(var productName : cart.keySet()){
            CartProductPane p = new CartProductPane(cart.get(productName));
            contentPanel.add(p);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        validate();

        updatePrices(restaurant);

        addressComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addressComboBox) {
                    if(addressComboBox.getSelectedIndex() == 0) {
                        newAddressField.setEnabled(false);
                        newAddressField.setText(service.getCurrentAccount().getAddress());
                    }
                    else if(addressComboBox.getSelectedIndex() == 1) {
                        newAddressField.setEnabled(true);
                        newAddressField.setText("");
                    }
                }
            }
        });

        paymentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CartPage(restaurant);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductsPage(restaurant);
                dispose();
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Delivery created successfully");
                new RestaurantsPage();
                dispose();
            }
        });


        // Export everything when closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("Closing app");
                service.exportAll();
                dispose();
                System.exit(0);
            }
        });
    }



    // Update the price of delivery
    void updatePrices(Restaurant restaurant) {
        Service service = Service.getInstance();
        Logger logger = LogManager.getLogger(CartPage.class);
        logger.debug("Refresh page");

        var cart = service.getCart();
        var cartSize = service.getCartSize();

        double sum = 0;
        for(var productName : cart.keySet())
            for(var quantity = 0; quantity < cartSize.get(productName); quantity++)
                sum += Double.parseDouble(cart.get(productName).getPrice());
        paymentProductsValueLabel.setText(sum + " LEI     ");

        paymentDeliveryValueLabel.setText(restaurant.getDelivery_price() + " LEI     ");

        sum += Double.parseDouble(restaurant.getDelivery_price());
        paymentTotalValueLabel.setText(sum + " LEI     ");
    }

}
