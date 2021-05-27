package app.gui;

import java.awt.event.*;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class RestaurantsPanel extends JPanel {
    Service service = Service.getInstance();
    private final Logger logger = LogManager.getLogger(RestaurantsPanel.class);

    public RestaurantsPanel() {
        logger.debug("Creating Restaurants Page");

        initComponents();

        scrollPane.getVerticalScrollBar().setPreferredSize(
                new Dimension(10, Integer.MAX_VALUE));

        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        for (var id_restaurant : service.getRestaurants().keySet()) {
            RestaurantPane rest = new RestaurantPane(id_restaurant);
            rest.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            rest.setAlignmentY(JPanel.TOP_ALIGNMENT);
            contentPane.add(rest);
            contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        validate();

        logger.debug("Restaurants page created successfully");
    }

    private void exit_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == exit_button)
            System.exit(0);
    }

    private void account_iconMouseClicked(MouseEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.openAccountPage();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        account_icon = new JLabel();
        title_label = new JLabel();
        scrollPane = new JScrollPane();
        contentPane = new JPanel();

        //======== this ========
        setBackground(Color.white);
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax
                .swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing
                .border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.
                Font("Dia\u006cog", java.awt.Font.BOLD, 12), java.awt.Color.red
        ), getBorder()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("bord\u0065r".equals(e.getPropertyName(
                ))) throw new RuntimeException();
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

        //---- account_icon ----
        account_icon.setIcon(new ImageIcon(getClass().getResource("/account_icon.png")));
        account_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                account_iconMouseClicked(e);
            }
        });
        add(account_icon);
        account_icon.setBounds(10, 5, 60, 60);

        //---- title_label ----
        title_label.setText("Restaurante");
        title_label.setHorizontalAlignment(SwingConstants.LEFT);
        title_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        title_label.setOpaque(false);
        title_label.setBorder(null);
        title_label.setForeground(new Color(60, 0, 200));
        add(title_label);
        title_label.setBounds(100, 0, 175, 75);

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
        scrollPane.setBounds(95, 75, 610, 500);

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
    private JLabel account_icon;
    private JLabel title_label;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
