/*
 * Created by JFormDesigner on Sun May 23 17:33:50 EEST 2021
 */

package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CartPanel extends JPanel {
    private final Logger logger = LogManager.getLogger(CartPanel.class);
    private int products_displayed;

    public CartPanel() {
        logger.debug("Creating Cart Page");
        Service service = Service.getInstance();

        initComponents();

        scrollPane.getVerticalScrollBar().setPreferredSize(
                new Dimension(10, Integer.MAX_VALUE));

        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        updatePage();
        logger.debug("Cart page created successfully");
    }

    private void exit_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == exit_button)
            System.exit(0);
    }

    private void back_iconMouseClicked(MouseEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.openProductsPage();
    }

    private void updatePage() {
        Service service = Service.getInstance();

        if (service.getCartSize() != products_displayed) {

            // Update product panels
            products_displayed = 0;
            contentPane.removeAll();
            repaint();

            for (var product : service.getCart()) {
                products_displayed += product.getQuantity();

                Product2CartPane prod = new Product2CartPane(product);
                prod.setAlignmentX(JPanel.LEFT_ALIGNMENT);
                prod.setAlignmentY(JPanel.TOP_ALIGNMENT);
                contentPane.add(prod);
                contentPane.add(Box.createRigidArea(new Dimension(0, 10)));
            }
            validate();


            // Update prices
            double sum = 0;
            for (var listed_product : service.getCart())
                sum += listed_product.getPrice();
            products_value_label.setText(sum + " RON   ");

            delivery_value_label.setText(service.getRestaurants().get(service.getSelected_restaurant_id()).getDelivery_price() + " RON   ");

            sum += service.getRestaurants().get(service.getSelected_restaurant_id()).getDelivery_price();
            total_value_label.setText(sum + " RON   ");
        }
    }


    private void thisMouseEntered(MouseEvent e) {
        updatePage();
    }

    private void thisMouseExited(MouseEvent e) {
        updatePage();
    }

    private void confirm_buttonActionPerformed(ActionEvent e) {
        Service service = Service.getInstance();
        service.createOrder(address_field.getText());

        service.flushCart();

        AppFrame app = AppFrame.getInstance();
        app.openRestaurantsPage();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        back_icon = new JLabel();
        title_label = new JLabel();
        scrollPane = new JScrollPane();
        contentPane = new JPanel();
        delivery_title = new JLabel();
        address_label = new JLabel();
        address_field = new JTextField();
        payment_label = new JLabel();
        payment_box = new JComboBox<>();
        products_label = new JLabel();
        delivery_label = new JLabel();
        total_label = new JLabel();
        products_value_label = new JLabel();
        delivery_value_label = new JLabel();
        total_value_label = new JLabel();
        payment_bg = new JLabel();
        confirm_button = new JButton();
        bg_label = new JLabel();

        //======== this ========
        setBackground(Color.white);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                thisMouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                thisMouseExited(e);
            }
        });
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
        exit_button.setForeground(Color.white);
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
        back_icon.setIcon(new ImageIcon(getClass().getResource("/back_icon2.png")));
        back_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                back_iconMouseClicked(e);
            }
        });
        add(back_icon);
        back_icon.setBounds(30, 5, 60, 60);

        //---- title_label ----
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        title_label.setOpaque(false);
        title_label.setBorder(null);
        title_label.setForeground(Color.white);
        title_label.setText("Cos de cumparaturi");
        add(title_label);
        title_label.setBounds(100, 0, 600, 75);

        //======== scrollPane ========
        {
            scrollPane.setBorder(null);
            scrollPane.setOpaque(false);
            scrollPane.setEnabled(false);

            //======== contentPane ========
            {
                contentPane.setBackground(Color.darkGray);
                contentPane.setOpaque(false);
                contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            }
            scrollPane.setViewportView(contentPane);
        }
        add(scrollPane);
        scrollPane.setBounds(20, 90, 410, 500);

        //---- delivery_title ----
        delivery_title.setHorizontalAlignment(SwingConstants.CENTER);
        delivery_title.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
        delivery_title.setOpaque(false);
        delivery_title.setBorder(null);
        delivery_title.setForeground(Color.white);
        delivery_title.setText("Livrare");
        add(delivery_title);
        delivery_title.setBounds(420, 90, 380, 50);

        //---- address_label ----
        address_label.setHorizontalAlignment(SwingConstants.CENTER);
        address_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        address_label.setOpaque(false);
        address_label.setBorder(null);
        address_label.setForeground(Color.white);
        address_label.setText("Adresa de livrare");
        add(address_label);
        address_label.setBounds(420, 155, 380, 35);

        //---- address_field ----
        address_field.setFont(new Font("Segoe UI Light", Font.BOLD, 16));
        address_field.setForeground(Color.black);
        address_field.setBackground(Color.white);
        address_field.setBorder(null);
        add(address_field);
        address_field.setBounds(445, 190, 345, 40);

        //---- payment_label ----
        payment_label.setHorizontalAlignment(SwingConstants.CENTER);
        payment_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        payment_label.setOpaque(false);
        payment_label.setBorder(null);
        payment_label.setForeground(Color.white);
        payment_label.setText("Metoda de plata");
        add(payment_label);
        payment_label.setBounds(420, 265, 380, 35);

        //---- payment_box ----
        payment_box.setOpaque(false);
        payment_box.setModel(new DefaultComboBoxModel<>(new String[]{
                "Numerar",
                "Card bancar (indisponibil)"
        }));
        payment_box.setBackground(Color.white);
        payment_box.setBorder(null);
        add(payment_box);
        payment_box.setBounds(445, 300, 345, 40);

        //---- products_label ----
        products_label.setHorizontalAlignment(SwingConstants.LEFT);
        products_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        products_label.setOpaque(false);
        products_label.setBorder(null);
        products_label.setForeground(Color.white);
        products_label.setText("Produse");
        add(products_label);
        products_label.setBounds(455, 360, 165, 35);

        //---- delivery_label ----
        delivery_label.setHorizontalAlignment(SwingConstants.LEFT);
        delivery_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        delivery_label.setOpaque(false);
        delivery_label.setBorder(null);
        delivery_label.setForeground(Color.white);
        delivery_label.setText("Livrare");
        add(delivery_label);
        delivery_label.setBounds(455, 395, 165, 35);

        //---- total_label ----
        total_label.setHorizontalAlignment(SwingConstants.LEFT);
        total_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        total_label.setOpaque(false);
        total_label.setBorder(null);
        total_label.setForeground(Color.white);
        total_label.setText("Total");
        add(total_label);
        total_label.setBounds(455, 430, 165, 35);

        //---- products_value_label ----
        products_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
        products_value_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        products_value_label.setOpaque(false);
        products_value_label.setBorder(null);
        products_value_label.setForeground(Color.white);
        products_value_label.setText("0.0 RON");
        add(products_value_label);
        products_value_label.setBounds(620, 360, 160, 35);

        //---- delivery_value_label ----
        delivery_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
        delivery_value_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        delivery_value_label.setOpaque(false);
        delivery_value_label.setBorder(null);
        delivery_value_label.setForeground(Color.white);
        delivery_value_label.setText("0.0 RON");
        add(delivery_value_label);
        delivery_value_label.setBounds(620, 395, 160, 35);

        //---- total_value_label ----
        total_value_label.setHorizontalAlignment(SwingConstants.RIGHT);
        total_value_label.setFont(new Font("Segoe UI Semilight", Font.BOLD, 14));
        total_value_label.setOpaque(false);
        total_value_label.setBorder(null);
        total_value_label.setForeground(Color.white);
        total_value_label.setText("0.0 RON");
        add(total_value_label);
        total_value_label.setBounds(620, 430, 160, 35);

        //---- payment_bg ----
        payment_bg.setIcon(new ImageIcon(getClass().getResource("/back_blur.png")));
        add(payment_bg);
        payment_bg.setBounds(450, 350, 340, 125);

        //---- confirm_button ----
        confirm_button.setText("Confirma comanda");
        confirm_button.setIcon(new ImageIcon(getClass().getResource("/white_button3.png")));
        confirm_button.setOpaque(false);
        confirm_button.setHorizontalTextPosition(SwingConstants.CENTER);
        confirm_button.setContentAreaFilled(false);
        confirm_button.setBorderPainted(false);
        confirm_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
        confirm_button.setForeground(Color.black);
        confirm_button.setPressedIcon(new ImageIcon(getClass().getResource("/white_button3_pressed.png")));
        confirm_button.addActionListener(e -> confirm_buttonActionPerformed(e));
        add(confirm_button);
        confirm_button.setBounds(510, 500, 215, 60);

        //---- bg_label ----
        bg_label.setIcon(new ImageIcon(getClass().getResource("/cart_page_bg.jpeg")));
        bg_label.setVerticalAlignment(SwingConstants.TOP);
        bg_label.setHorizontalAlignment(SwingConstants.LEFT);
        add(bg_label);
        bg_label.setBounds(new Rectangle(new Point(0, 0), bg_label.getPreferredSize()));

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
    private JLabel delivery_title;
    private JLabel address_label;
    private JTextField address_field;
    private JLabel payment_label;
    private JComboBox<String> payment_box;
    private JLabel products_label;
    private JLabel delivery_label;
    private JLabel total_label;
    private JLabel products_value_label;
    private JLabel delivery_value_label;
    private JLabel total_value_label;
    private JLabel payment_bg;
    private JButton confirm_button;
    private JLabel bg_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
