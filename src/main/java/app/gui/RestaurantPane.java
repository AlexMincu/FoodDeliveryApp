package app.gui;

import java.awt.event.*;

import app.model.Restaurant;
import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;
import javax.swing.*;


public class RestaurantPane extends JPanel {
    private final Logger logger = LogManager.getLogger(RestaurantPane.class);
    private final int id_restaurant;

    public RestaurantPane(int id_restaurant) {
        logger.debug("Creating Restaurant Pane for id '" + id_restaurant + "'");
        this.id_restaurant = id_restaurant;

        initComponents();

        setPreferredSize(new Dimension(600, 150));
        setMaximumSize(new Dimension(600, 150));

        Service service = Service.getInstance();
        Restaurant restaurant = service.getRestaurants().get(id_restaurant);

        restaurant_label.setText(restaurant.getName());
        // Change size and positioning
        int new_x = restaurant_icon.getWidth() / 2 - restaurant_label.getWidth() / 2;
        int new_y = restaurant_icon.getHeight() / 2 - restaurant_label.getHeight() / 2;
        restaurant_label.setBounds(new_x, new_y, restaurant_label.getWidth(), restaurant_label.getHeight());
        back_blur.setBounds(new_x - 10, new_y - 10,
                restaurant_label.getWidth() + 20, restaurant_label.getHeight() + 20);

        if (new File("src/main/resources/" + restaurant.getName() + ".jpg").exists())
            restaurant_icon.setIcon(new ImageIcon("src/main/resources/" + restaurant.getName() + ".jpg"));

        delivery_time_label.setText(restaurant.getDelivery_time() + " min.");
        delivery_price_label.setText(restaurant.getDelivery_price() + " RON");
        score_label.setText(restaurant.getScore() + "/5");


        logger.debug("Restaurant Pane for id '" + id_restaurant + "' created successfully");
    }

    private void restaurant_labelMouseClicked(MouseEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.setSelected_restaurant_id(id_restaurant);
        app.openProductsPage();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        restaurant_label = new JLabel();
        back_blur = new JLabel();
        restaurant_icon = new JLabel();
        delivery_time_icon = new JLabel();
        delivery_time_label = new JLabel();
        delivery_price_icon = new JLabel();
        delivery_price_label = new JLabel();
        score_icon = new JLabel();
        score_label = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing
                .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
        ), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("\u0062ord\u0065r".equals(e.getPropertyName(
                ))) throw new RuntimeException();
            }
        });
        setLayout(null);

        //---- restaurant_label ----
        restaurant_label.setHorizontalTextPosition(SwingConstants.CENTER);
        restaurant_label.setHorizontalAlignment(SwingConstants.CENTER);
        restaurant_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        restaurant_label.setForeground(Color.white);
        restaurant_label.setText("Restaurant");
        restaurant_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(restaurant_label);
        restaurant_label.setBounds(0, 0, 250, 45);

        //---- back_blur ----
        back_blur.setHorizontalTextPosition(SwingConstants.CENTER);
        back_blur.setHorizontalAlignment(SwingConstants.CENTER);
        back_blur.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
        back_blur.setForeground(Color.black);
        back_blur.setIcon(new ImageIcon(getClass().getResource("/back_blur.png")));
        back_blur.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(back_blur);
        back_blur.setBounds(0, 0, 265, 65);

        //---- restaurant_icon ----
        restaurant_icon.setHorizontalTextPosition(SwingConstants.CENTER);
        restaurant_icon.setHorizontalAlignment(SwingConstants.CENTER);
        restaurant_icon.setIcon(new ImageIcon(getClass().getResource("/default_restaurant.jpg")));
        restaurant_icon.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        restaurant_icon.setForeground(Color.white);
        restaurant_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(restaurant_icon);
        restaurant_icon.setBounds(0, 0, 400, 150);

        //---- delivery_time_icon ----
        delivery_time_icon.setIcon(new ImageIcon(getClass().getResource("/delivery_time_icon.png")));
        delivery_time_icon.setHorizontalAlignment(SwingConstants.CENTER);
        delivery_time_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(delivery_time_icon);
        delivery_time_icon.setBounds(400, 0, 50, 50);

        //---- delivery_time_label ----
        delivery_time_label.setText("time");
        delivery_time_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(delivery_time_label);
        delivery_time_label.setBounds(460, 0, 140, 50);

        //---- delivery_price_icon ----
        delivery_price_icon.setHorizontalAlignment(SwingConstants.CENTER);
        delivery_price_icon.setIcon(new ImageIcon(getClass().getResource("/delivery_price_icon.png")));
        delivery_price_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(delivery_price_icon);
        delivery_price_icon.setBounds(400, 50, 50, 50);

        //---- delivery_price_label ----
        delivery_price_label.setText("price");
        delivery_price_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(delivery_price_label);
        delivery_price_label.setBounds(460, 50, 140, 50);

        //---- score_icon ----
        score_icon.setIcon(new ImageIcon(getClass().getResource("/score_icon.png")));
        score_icon.setHorizontalAlignment(SwingConstants.CENTER);
        score_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(score_icon);
        score_icon.setBounds(400, 100, 50, 50);

        //---- score_label ----
        score_label.setText("score");
        score_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                restaurant_labelMouseClicked(e);
            }
        });
        add(score_label);
        score_label.setBounds(460, 100, 140, 50);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel restaurant_label;
    private JLabel back_blur;
    private JLabel restaurant_icon;
    private JLabel delivery_time_icon;
    private JLabel delivery_time_label;
    private JLabel delivery_price_icon;
    private JLabel delivery_price_label;
    private JLabel score_icon;
    private JLabel score_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
