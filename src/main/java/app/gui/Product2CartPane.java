/*
 * Created by JFormDesigner on Sun May 23 22:11:25 EEST 2021
 */

package app.gui;

import app.model.OrderProduct;
import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class Product2CartPane extends JPanel {
    private final Logger logger = LogManager.getLogger(Product2CartPane.class);
    private OrderProduct product;

    public Product2CartPane(OrderProduct product) {
        logger.debug("Creating Product Pane for Cart Page with id '" + product.getProduct().getId_product() + "'");
        this.product = product;
        initComponents();

        setPreferredSize(new Dimension(400, 150));
        setMaximumSize(new Dimension(400, 150));


        if (new File("src/main/resources/products/" + product.getProduct().getId_product() + ".jpg").exists())
            product_icon.setIcon(new ImageIcon("src/main/resources/products/" + product.getProduct().getId_product() + ".jpg"));

        product_label.setText(product.getProduct().getName());
        description_label.setText(product.getProduct().getDescription());
        price_label.setText(product.getProduct().getPrice() + " RON");
        quantity_label.setText("x" + product.getQuantity());

        logger.debug("Product Pane Cart Page with id '" + product.getProduct().getId_product() + "' created successfully");
    }

    private void minus_buttonActionPerformed(ActionEvent e) {
        Service service = Service.getInstance();
        service.removeFromCart(product.getProduct());

        quantity_label.setText("x" + product.getQuantity());
    }

    private void plus_buttonActionPerformed(ActionEvent e) {
        Service service = Service.getInstance();
        service.addToCart(product.getProduct());

        quantity_label.setText("x" + product.getQuantity());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        product_label = new JLabel();
        description_label = new JTextArea();
        back_blur = new JLabel();
        product_icon = new JLabel();
        price_label = new JLabel();
        quantity_label = new JLabel();
        minus_button = new JButton();
        plus_button = new JButton();
        bg = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing
                .border.EmptyBorder(0, 0, 0, 0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax.swing.border.TitledBorder
                .CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.
                awt.Font.BOLD, 12), java.awt.Color.red), getBorder()))
        ;
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e
            ) {
                if ("\u0062ord\u0065r".equals(e.getPropertyName())) throw new RuntimeException();
            }
        })
        ;
        setLayout(null);

        //---- product_label ----
        product_label.setHorizontalTextPosition(SwingConstants.CENTER);
        product_label.setHorizontalAlignment(SwingConstants.CENTER);
        product_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        product_label.setForeground(Color.white);
        product_label.setText("Product");
        add(product_label);
        product_label.setBounds(0, 0, 250, 50);

        //---- description_label ----
        description_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
        description_label.setForeground(Color.white);
        description_label.setText("description");
        description_label.setEditable(false);
        description_label.setEnabled(false);
        description_label.setDisabledTextColor(Color.white);
        description_label.setLineWrap(true);
        description_label.setOpaque(false);
        description_label.setWrapStyleWord(true);
        add(description_label);
        description_label.setBounds(10, 50, 230, 100);

        //---- back_blur ----
        back_blur.setHorizontalTextPosition(SwingConstants.CENTER);
        back_blur.setHorizontalAlignment(SwingConstants.CENTER);
        back_blur.setFont(new Font("Segoe UI Semibold", Font.BOLD, 21));
        back_blur.setForeground(Color.black);
        back_blur.setIcon(new ImageIcon(getClass().getResource("/back_blur.png")));
        add(back_blur);
        back_blur.setBounds(0, 0, 250, 150);

        //---- product_icon ----
        product_icon.setHorizontalTextPosition(SwingConstants.CENTER);
        product_icon.setHorizontalAlignment(SwingConstants.CENTER);
        product_icon.setIcon(new ImageIcon(getClass().getResource("/default_product.jpg")));
        product_icon.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        product_icon.setForeground(Color.white);
        add(product_icon);
        product_icon.setBounds(0, 0, 250, 150);

        //---- price_label ----
        price_label.setText("price");
        price_label.setHorizontalAlignment(SwingConstants.CENTER);
        price_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        add(price_label);
        price_label.setBounds(250, 0, 150, 50);

        //---- quantity_label ----
        quantity_label.setHorizontalAlignment(SwingConstants.CENTER);
        quantity_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        add(quantity_label);
        quantity_label.setBounds(265, 50, 130, 45);

        //---- minus_button ----
        minus_button.setIcon(new ImageIcon(getClass().getResource("/round_button_minus.png")));
        minus_button.setAlignmentY(0.0F);
        minus_button.setContentAreaFilled(false);
        minus_button.setBorderPainted(false);
        minus_button.setPressedIcon(new ImageIcon(getClass().getResource("/round_button_minus_pressed.png")));
        minus_button.setForeground(Color.black);
        minus_button.setHorizontalTextPosition(SwingConstants.CENTER);
        minus_button.addActionListener(e -> minus_buttonActionPerformed(e));
        add(minus_button);
        minus_button.setBounds(255, 95, 50, 50);

        //---- plus_button ----
        plus_button.setIcon(new ImageIcon(getClass().getResource("/round_button_plus.png")));
        plus_button.setAlignmentY(0.0F);
        plus_button.setContentAreaFilled(false);
        plus_button.setBorderPainted(false);
        plus_button.setPressedIcon(new ImageIcon(getClass().getResource("/round_button_plus_pressed.png")));
        plus_button.setForeground(Color.black);
        plus_button.setHorizontalTextPosition(SwingConstants.CENTER);
        plus_button.addActionListener(e -> plus_buttonActionPerformed(e));
        add(plus_button);
        plus_button.setBounds(345, 95, 50, 50);

        //---- bg ----
        bg.setOpaque(true);
        bg.setBackground(Color.white);
        add(bg);
        bg.setBounds(0, 0, 400, 150);

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
    private JLabel product_label;
    private JTextArea description_label;
    private JLabel back_blur;
    private JLabel product_icon;
    private JLabel price_label;
    private JLabel quantity_label;
    private JButton minus_button;
    private JButton plus_button;
    private JLabel bg;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
