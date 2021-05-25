/*
 * Created by JFormDesigner on Sun May 23 17:33:50 EEST 2021
 */

package app.gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WelcomePanel extends JPanel {
    private final Logger logger = LogManager.getLogger(WelcomePanel.class);

    public WelcomePanel() {
        logger.debug("Creating Welcome Page");
        initComponents();

        logger.debug("Welcome page created successfully");
    }

    private void login_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == login_button) {
            AppFrame app = AppFrame.getInstance();
            app.openLoginPage();
        }
    }

    private void register_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == register_button) {
            AppFrame app = AppFrame.getInstance();
            app.openRegisterPage();
        }
    }

    private void exit_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == exit_button)
            System.exit(0);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        welcome_label = new JLabel();
        welcome_label2 = new JLabel();
        login_description_label = new JLabel();
        login_button = new JButton();
        register_button = new JButton();
        bg_label = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
                swing.border.EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border
                .TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog"
                , java.awt.Font.BOLD, 12), java.awt.Color.red), getBorder
                ()));
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java
                                               .beans.PropertyChangeEvent e) {
                if ("bord\u0065r".equals(e.getPropertyName())) throw new RuntimeException
                        ();
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

        //---- welcome_label ----
        welcome_label.setText("Champion's Food");
        welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
        welcome_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 40));
        welcome_label.setOpaque(false);
        welcome_label.setBorder(null);
        welcome_label.setForeground(Color.white);
        welcome_label.setIcon(new ImageIcon(getClass().getResource("/champions_icon_medium.png")));
        add(welcome_label);
        welcome_label.setBounds(0, 0, 800, 200);

        //---- welcome_label2 ----
        welcome_label2.setHorizontalAlignment(SwingConstants.CENTER);
        welcome_label2.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 20));
        welcome_label2.setOpaque(false);
        welcome_label2.setBorder(null);
        welcome_label2.setForeground(Color.white);
        welcome_label2.setText("Este mancarea pe care o doresti livrata acasa sau la birou!");
        add(welcome_label2);
        welcome_label2.setBounds(0, 210, 800, 100);

        //---- login_description_label ----
        login_description_label.setText("Ai deja cont?");
        login_description_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        login_description_label.setForeground(new Color(204, 204, 204));
        add(login_description_label);
        login_description_label.setBounds(300, 330, 100, 16);

        //---- login_button ----
        login_button.setIcon(new ImageIcon(getClass().getResource("/white_button3.png")));
        login_button.setText("Conectare");
        login_button.setHorizontalTextPosition(SwingConstants.CENTER);
        login_button.setIconTextGap(0);
        login_button.setOpaque(false);
        login_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        login_button.setContentAreaFilled(false);
        login_button.setBorderPainted(false);
        login_button.setBorder(null);
        login_button.setPressedIcon(new ImageIcon(getClass().getResource("/white_button3_pressed.png")));
        login_button.setForeground(Color.black);
        login_button.addActionListener(e -> login_buttonActionPerformed(e));
        add(login_button);
        login_button.setBounds(300, 350, 200, 50);

        //---- register_button ----
        register_button.setIcon(new ImageIcon(getClass().getResource("/white_button3.png")));
        register_button.setText("Creare cont");
        register_button.setHorizontalTextPosition(SwingConstants.CENTER);
        register_button.setIconTextGap(0);
        register_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        register_button.setPressedIcon(new ImageIcon(getClass().getResource("/white_button3_pressed.png")));
        register_button.setContentAreaFilled(false);
        register_button.setBorderPainted(false);
        register_button.setBorder(null);
        register_button.setForeground(Color.black);
        register_button.addActionListener(e -> register_buttonActionPerformed(e));
        add(register_button);
        register_button.setBounds(300, 425, 200, 50);

        //---- bg_label ----
        bg_label.setIcon(new ImageIcon(getClass().getResource("/welcome_page_bg.jpeg")));
        bg_label.setVerticalAlignment(SwingConstants.TOP);
        bg_label.setHorizontalAlignment(SwingConstants.LEFT);
        add(bg_label);
        bg_label.setBounds(0, 0, 800, 600);

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
    private JLabel welcome_label;
    private JLabel welcome_label2;
    private JLabel login_description_label;
    private JButton login_button;
    private JButton register_button;
    private JLabel bg_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
