/*
 * Created by JFormDesigner on Sun May 23 17:33:50 EEST 2021
 */

package app.gui;

import java.awt.event.*;

import app.model.Restaurant;
import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ProductsPanel extends JPanel {
    private final Logger logger = LogManager.getLogger(ProductsPanel.class);

    public ProductsPanel(Restaurant restaurant) {
        logger.debug("Creating Products Page");

        Service service = Service.getInstance();
        initComponents();

        scrollPane.getVerticalScrollBar().setPreferredSize(
                new Dimension(10, Integer.MAX_VALUE));

        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        title_label.setText(restaurant.getName());

        for (var product : restaurant.getProducts()) {
            ProductPane prod = new ProductPane(product);
            prod.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            prod.setAlignmentY(JPanel.TOP_ALIGNMENT);
            contentPane.add(prod);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        validate();


        logger.debug("Products page created successfully");
    }

    private void exit_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == exit_button)
            System.exit(0);
    }

    private void back_iconMouseClicked(MouseEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.openRestaurantsPage();
    }

    private void cart_iconMouseClicked(MouseEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.openCartPage();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        back_icon = new JLabel();
        title_label = new JLabel();
        scrollPane = new JScrollPane();
        contentPane = new JPanel();
        cart_icon = new JLabel();

        //======== this ========
        setBackground(Color.white);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                swing.border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border
                .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog"
                , java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder
                ()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java
                                               .beans.PropertyChangeEvent e) {
                if ("\u0062order".equals(e.getPropertyName())) throw new RuntimeException
                        ();
            }
        });
        setLayout(null);

        //---- exit_button ----
        exit_button.setText("x");
        exit_button.setForeground(Color.black);
        exit_button.setContentAreaFilled(false);
        exit_button.setBorderPainted(false);
        exit_button.setBorder(null);
        exit_button.setFont(new Font("Segoe UI", Font.PLAIN, 30));
        exit_button.setAlignmentY(0.0F);
        exit_button.setHorizontalAlignment(SwingConstants.LEFT);
        exit_button.setVerticalAlignment(SwingConstants.BOTTOM);
        exit_button.addActionListener(e -> exit_buttonActionPerformed(e));
        add(exit_button);
        exit_button.setBounds(770, 0, exit_button.getPreferredSize().width, 30);

        //---- back_icon ----
        back_icon.setIcon(new ImageIcon(getClass().getResource("/back_icon.png")));
        back_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                back_iconMouseClicked(e);
            }
        });
        add(back_icon);
        back_icon.setBounds(30, 5, 60, 60);

        //---- title_label ----
        title_label.setHorizontalAlignment(SwingConstants.LEFT);
        title_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        title_label.setOpaque(false);
        title_label.setBorder(null);
        title_label.setForeground(new Color(60, 0, 200));
        title_label.setText("Restaurant");
        add(title_label);
        title_label.setBounds(100, 0, 600, 75);

        //======== scrollPane ========
        {
            scrollPane.setBorder(null);
            scrollPane.setForeground(Color.white);

            //======== contentPane ========
            {
                contentPane.setBackground(Color.white);
                contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            }
            scrollPane.setViewportView(contentPane);
        }
        add(scrollPane);
        scrollPane.setBounds(25, 75, 610, 500);

        //---- cart_icon ----
        cart_icon.setIcon(new ImageIcon(getClass().getResource("/cart_icon.png")));
        cart_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cart_iconMouseClicked(e);
            }
        });
        add(cart_icon);
        cart_icon.setBounds(650, 250, 100, 100);

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
    private JButton exit_button;
    private JLabel back_icon;
    private JLabel title_label;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    private JLabel cart_icon;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
