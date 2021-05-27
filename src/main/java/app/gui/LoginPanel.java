package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.*;


public class LoginPanel extends JPanel {
    Service service = Service.getInstance();
    private final Logger logger = LogManager.getLogger(LoginPanel.class);
    private boolean clicked_email_field = false;
    private boolean clicked_password_field = false;

    public LoginPanel() {
        logger.debug("Creating Login Page");
        initComponents();

        logger.debug("Login page created successfully");
    }

    private void login() {
        String pass = "";
        for (var c : password_field.getPassword())
            pass = pass.concat(String.valueOf(c));

        // login action
        if (service.login(email_field.getText(), pass)) {
            AppFrame app = AppFrame.getInstance();
            app.openRestaurantsPage();
        } else {
            error_label.setText("Informatii invalide, va rugam sa incercati din nou");
        }
    }


    private void exit_buttonActionPerformed(ActionEvent e) {
        System.exit(0);
    }


    private void email_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    private void email_fieldFocusGained(FocusEvent e) {
        if (!clicked_email_field) {
            email_field.setText("");
            email_field.setForeground(Color.black);

            clicked_email_field = true;
        }
    }

    private void email_fieldFocusLost(FocusEvent e) {
        if (email_field.getText().equals("")) {
            email_field.setText("E-mail");
            clicked_email_field = false;
        }
    }


    private void password_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            login();
        }
    }

    private void password_fieldFocusGained(FocusEvent e) {
        if (!clicked_password_field) {
            password_field.setText("");
            password_field.setForeground(Color.black);

            clicked_password_field = true;
        }
    }

    private void password_fieldFocusLost(FocusEvent e) {
        String pass = "";
        for (var c : password_field.getPassword())
            pass = pass.concat(String.valueOf(c));

        if (pass.equals("")) {
            password_field.setText("Parola");
            clicked_password_field = false;
        }
    }


    private void login_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == login_button)
            login();
    }


    private void register_buttonActionPerformed(ActionEvent e) {
        if (e.getSource() == register_button) {
            AppFrame app = AppFrame.getInstance();
            app.openRegisterPage();
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        icon_label = new JLabel();
        login_label = new JLabel();
        email_icon = new JLabel();
        email_field = new JTextField();
        password_icon = new JLabel();
        password_field = new JPasswordField();
        login_button = new JButton();
        register_description_label = new JLabel();
        register_button = new JButton();
        error_label = new JLabel();
        bg_label = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder ( 0
        , 0 ,0 , 0) ,  "JFor\u006dDesi\u0067ner \u0045valu\u0061tion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM
        , new java. awt .Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,
         getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e
        ) { if( "bord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
        setLayout(null);

        //---- exit_button ----
        exit_button.setText("x");
        exit_button.setForeground(Color.blue);
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

        //---- icon_label ----
        icon_label.setHorizontalAlignment(SwingConstants.CENTER);
        icon_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 40));
        icon_label.setOpaque(false);
        icon_label.setBorder(null);
        icon_label.setForeground(Color.white);
        icon_label.setIcon(new ImageIcon(getClass().getResource("/champions_icon_medium.png")));
        add(icon_label);
        icon_label.setBounds(0, 0, 210, 175);

        //---- login_label ----
        login_label.setHorizontalAlignment(SwingConstants.CENTER);
        login_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        login_label.setOpaque(false);
        login_label.setBorder(null);
        login_label.setForeground(Color.white);
        login_label.setText("Conectare");
        add(login_label);
        login_label.setBounds(0, 175, 210, 55);

        //---- email_icon ----
        email_icon.setIcon(new ImageIcon(getClass().getResource("/email_icon.png")));
        email_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(email_icon);
        email_icon.setBounds(250, 190, 50, 50);

        //---- email_field ----
        email_field.setText("E-mail");
        email_field.setBackground(Color.white);
        email_field.setBorder(new EtchedBorder());
        email_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        email_field.setForeground(new Color(153, 153, 153));
        email_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                email_fieldFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                email_fieldFocusLost(e);
            }
        });
        email_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                email_fieldKeyReleased(e);
            }
        });
        add(email_field);
        email_field.setBounds(300, 200, 300, email_field.getPreferredSize().height);

        //---- password_icon ----
        password_icon.setIcon(new ImageIcon(getClass().getResource("/password_icon.png")));
        password_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(password_icon);
        password_icon.setBounds(250, 240, 50, 50);

        //---- password_field ----
        password_field.setText("Parola");
        password_field.setBackground(Color.white);
        password_field.setBorder(new EtchedBorder());
        password_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        password_field.setForeground(new Color(153, 153, 153));
        password_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                password_fieldFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                password_fieldFocusLost(e);
            }
        });
        password_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                password_fieldKeyReleased(e);
            }
        });
        add(password_field);
        password_field.setBounds(300, 250, 300, 33);

        //---- login_button ----
        login_button.setText("Conectare");
        login_button.setHorizontalTextPosition(SwingConstants.CENTER);
        login_button.setIconTextGap(0);
        login_button.setOpaque(false);
        login_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        login_button.setContentAreaFilled(false);
        login_button.setBorderPainted(false);
        login_button.setBorder(null);
        login_button.setPressedIcon(new ImageIcon(getClass().getResource("/blue_button_pressed.png")));
        login_button.setHorizontalAlignment(SwingConstants.LEFT);
        login_button.setVerticalAlignment(SwingConstants.TOP);
        login_button.setIcon(new ImageIcon(getClass().getResource("/blue_button.png")));
        login_button.setForeground(Color.white);
        login_button.addActionListener(e -> login_buttonActionPerformed(e));
        add(login_button);
        login_button.setBounds(400, 300, 200, 50);

        //---- register_description_label ----
        register_description_label.setText("Nu ai cont?");
        register_description_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        register_description_label.setForeground(new Color(102, 102, 102));
        add(register_description_label);
        register_description_label.setBounds(580, 525, 100, 16);

        //---- register_button ----
        register_button.setText("Inregistrare");
        register_button.setHorizontalTextPosition(SwingConstants.CENTER);
        register_button.setIconTextGap(0);
        register_button.setOpaque(false);
        register_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        register_button.setContentAreaFilled(false);
        register_button.setBorderPainted(false);
        register_button.setBorder(null);
        register_button.setPressedIcon(new ImageIcon(getClass().getResource("/blue_button_pressed.png")));
        register_button.setForeground(new Color(0, 0, 204));
        register_button.addActionListener(e -> register_buttonActionPerformed(e));
        add(register_button);
        register_button.setBounds(580, 530, 200, 50);

        //---- error_label ----
        error_label.setHorizontalAlignment(SwingConstants.CENTER);
        error_label.setForeground(Color.red);
        error_label.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 14));
        add(error_label);
        error_label.setBounds(235, 400, 400, 30);

        //---- bg_label ----
        bg_label.setIcon(new ImageIcon(getClass().getResource("/login_page_bg.jpeg")));
        bg_label.setVerticalAlignment(SwingConstants.TOP);
        bg_label.setHorizontalAlignment(SwingConstants.LEFT);
        add(bg_label);
        bg_label.setBounds(0, 0, 800, 600);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
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
    private JLabel icon_label;
    private JLabel login_label;
    private JLabel email_icon;
    private JTextField email_field;
    private JLabel password_icon;
    private JPasswordField password_field;
    private JButton login_button;
    private JLabel register_description_label;
    private JButton register_button;
    private JLabel error_label;
    private JLabel bg_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
