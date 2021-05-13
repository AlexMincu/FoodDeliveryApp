package app.gui;

import app.model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestaurantPane extends JPanel{
    private JLabel restaurantLabel;
    private JLabel scoreLabel;
    private JLabel priceLabel;
    private JLabel timeLabel;

    RestaurantPane(JFrame mainFrame, Restaurant restaurant) {
        this.setPreferredSize(new Dimension(430, 150));
        this.setMaximumSize(new Dimension(430, 150));
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setLayout(null);

        restaurantLabel = new JLabel();
        restaurantLabel.setBounds(0, 0, 300, 150);
        restaurantLabel.setText(restaurant.getName());
        restaurantLabel.setBackground(Color.red);
        restaurantLabel.setOpaque(false);

        scoreLabel = new JLabel();
        scoreLabel.setBounds(300, 0, 130, 50);
        scoreLabel.setText(String.valueOf(restaurant.getScore()));
        scoreLabel.setBackground(Color.green);
        scoreLabel.setOpaque(false);

        priceLabel = new JLabel();
        priceLabel.setBounds(300, 50, 130, 50);
        priceLabel.setText(String.valueOf(restaurant.getDelivery_price()));

        timeLabel = new JLabel();
        timeLabel.setBounds(300, 100, 130, 50);
        timeLabel.setText(String.valueOf(restaurant.getDelivery_time()));
        timeLabel.setBackground(Color.cyan);
        timeLabel.setOpaque(false);

        this.add(restaurantLabel);
        this.add(scoreLabel);
        this.add(priceLabel);
        this.add(timeLabel);

        restaurantLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.dispose();
                new ProductsPage(restaurant);
            }
        });
        scoreLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.dispose();
                new ProductsPage(restaurant);
            }
        });
        priceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.dispose();
                new ProductsPage(restaurant);
            }
        });
        timeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.dispose();
                new ProductsPage(restaurant);
            }
        });
    }
}
