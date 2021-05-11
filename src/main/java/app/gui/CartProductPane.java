package app.gui;

import app.Product;
import app.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CartProductPane extends JPanel{

    private JLabel productLabel;
    private JLabel priceLabel;
    private JLabel descriptionLabel;
    private JLabel quantityLabel;
    private JButton addButton;
    private JButton removeButton;

    CartProductPane(Product product) {
        this.setPreferredSize(new Dimension(430, 60));
        this.setMinimumSize(new Dimension(430, 60));
        this.setMaximumSize(new Dimension(430, 60));
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setLayout(null);

        Service service = Service.getInstance();

        productLabel = new JLabel();
        productLabel.setBounds(0, 0, 350, 30);
        productLabel.setText(product.getName());
        productLabel.setBackground(Color.red);
        productLabel.setOpaque(false);

        priceLabel = new JLabel();
        priceLabel.setBounds(350, 0, 80, 30);
        priceLabel.setText(String.valueOf(product.getPrice()));
        priceLabel.setBackground(Color.green);
        priceLabel.setOpaque(false);

        descriptionLabel = new JLabel();
        descriptionLabel.setBounds(30, 30, 370, 30);
        descriptionLabel.setText(product.getDescription());
        descriptionLabel.setBackground(Color.cyan);
        descriptionLabel.setOpaque(false);

        quantityLabel = new JLabel("x" + service.getCartSize().get(product.getName()).toString());
        quantityLabel.setBounds(370, 30, 30, 30);
        quantityLabel.setOpaque(true);
        quantityLabel.setBackground(Color.green);

        removeButton = new JButton("-");
        removeButton.setBorder(null);
        removeButton.setBounds(0, 30, 30, 30);
        removeButton.setOpaque(true);
        removeButton.setBackground(Color.yellow);

        addButton = new JButton("+");
        addButton.setBorder(null);
        addButton.setBounds(400, 30, 30, 30);
        addButton.setOpaque(true);
        addButton.setBackground(Color.yellow);

        this.add(productLabel);
        this.add(priceLabel);
        this.add(descriptionLabel);
        this.add(quantityLabel);
        this.add(addButton);
        this.add(removeButton);



        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.removeFromCart(product);

                Integer quantity = service.getCartSize().get(product.getName());
                if(quantity != null)
                    quantityLabel.setText("x" + quantity.toString());
                else
                    quantityLabel.setText("0");
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                service.addToCart(product);

                Integer quantity = service.getCartSize().get(product.getName());
                quantityLabel.setText("x" + quantity.toString());
            }
        });
    }
}
