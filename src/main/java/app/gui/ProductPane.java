package app.gui;

import app.model.Product;
import app.service.Service;

import javax.swing.*;
import java.awt.*;

public class ProductPane extends JPanel{

    private JLabel productLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;
    private JButton addButton;

    ProductPane(Product product) {
        this.setPreferredSize(new Dimension(380, 100));
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        Service service = Service.getInstance();

        productLabel = new JLabel();
        productLabel.setBounds(0, 0, 305, 40);
        productLabel.setText(product.getName());
        productLabel.setBackground(Color.red);
        productLabel.setOpaque(false);

        priceLabel = new JLabel();
        priceLabel.setBounds(305, 0, 80, 40);
        priceLabel.setText(String.valueOf(product.getPrice()));
        priceLabel.setBackground(Color.green);
        priceLabel.setOpaque(false);

        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(0, 40, 335, 60);
        descriptionLabel.setText(product.getDescription());
        descriptionLabel.setBackground(Color.cyan);
        descriptionLabel.setOpaque(false);

        addButton = new JButton("+");
        addButton.setBorder(null);
        addButton.setBounds(345, 52, 30, 30);
        addButton.setOpaque(true);
        addButton.setBackground(Color.yellow);

        this.add(Box.createRigidArea(new Dimension(50, 100)));
        this.add(productLabel);
        this.add(priceLabel);
        this.add(descriptionLabel);
        this.add(addButton);


        addButton.addActionListener(e -> service.addToCart(product));
    }
}
