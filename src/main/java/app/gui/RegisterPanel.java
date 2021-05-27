package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;


public class RegisterPanel extends JPanel {
    Service service = Service.getInstance();
    private final Logger logger = LogManager.getLogger(RegisterPanel.class);
    private boolean clicked_email_field = false;
    private boolean clicked_password_field = false;
    private boolean clicked_name_field = false;
    private boolean clicked_surname_field = false;
    private boolean clicked_phone_field = false;

    public RegisterPanel() {
        logger.debug("Creating Register Page");
        initComponents();

        logger.debug("Register page created successfully");
    }

    private void register() {
        boolean email_check = false;
        boolean password_check = false;
        boolean name_check = false;
        boolean surname_check = false;
        boolean phoneNo_check = false;

        String pass = "";
        for (var c : password_field.getPassword())
            pass = pass.concat(String.valueOf(c));


        // Email
        if(email_field.getText().isEmpty()) {
            email_field.setBackground(Color.pink);
        }
        else {
            email_field.setBackground(Color.white);
            email_check = true;
        }

        // Password
        if(pass.contains(" ") || pass.isEmpty()) {
            password_field.setBackground(Color.pink);
        }
        else {
            password_field.setBackground(Color.white);
            password_check = true;
        }

        // Name
        if(Pattern.matches("[\\W\\d]+", name_field.getText()) || name_field.getText().isEmpty()) {
            name_field.setBackground(Color.pink);
        }
        else {
            name_field.setBackground(Color.white);
            name_check = true;
        }

        // Surname
        if(Pattern.matches("[\\W\\d]+", surname_field.getText()) || surname_field.getText().isEmpty()) {
            surname_field.setBackground(Color.pink);
        }
        else {
            surname_field.setBackground(Color.white);
            surname_check = true;
        }

        // PhoneNo
        if(Pattern.matches("[^\\d]+", phone_field.getText()) ||
                phone_field.getText().isEmpty() ||
                phone_field.getText().length() != 10) {

            phone_field.setBackground(Color.pink);
        }
        else {
            phone_field.setBackground(Color.white);
            phoneNo_check = true;
        }


        // register action
        if(email_check && password_check && name_check && surname_check && phoneNo_check) {
            if (service.register(email_field.getText(), pass, name_field.getText(), surname_field.getText(), phone_field.getText())) {
                AppFrame app = AppFrame.getInstance();
                app.openLoginPage();
            }
            else {
                error_label.setText("Emailul este deja folosit");
            }
        }
        else {
            error_label.setText("Informatii invalide, va rugam sa incercati din nou");
        }
    }


    private void exit_buttonActionPerformed(ActionEvent e) {
        System.exit(0);
    }


    private void email_fieldFocusGained(FocusEvent e) {
        if (!clicked_email_field) {
            email_field.setText("");
            email_field.setForeground(Color.black);

            clicked_email_field = true;
        }
    }

    private void email_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }

    private void email_fieldFocusLost(FocusEvent e) {
        if (email_field.getText().equals("")) {
            email_field.setText("E-mail");
            clicked_email_field = false;
        }
    }


    private void password_fieldFocusGained(FocusEvent e) {
        if (!clicked_password_field) {
            password_field.setText("");
            password_field.setForeground(Color.black);

            clicked_password_field = true;
        }
    }

    private void password_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
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


    private void name_fieldFocusGained(FocusEvent e) {
        if (!clicked_name_field) {
            name_field.setText("");
            name_field.setForeground(Color.black);

            clicked_name_field = true;
        }
    }

    private void name_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }

    private void name_fieldFocusLost(FocusEvent e) {
        if (name_field.getText().equals("")) {
            name_field.setText("Nume");
            clicked_name_field = false;
        }
    }


    private void surname_fieldFocusGained(FocusEvent e) {
        if (!clicked_surname_field) {
            surname_field.setText("");
            surname_field.setForeground(Color.black);

            clicked_surname_field = true;
        }
    }

    private void surname_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }

    private void surname_fieldFocusLost(FocusEvent e) {
        if (surname_field.getText().equals("")) {
            surname_field.setText("Prenume");
            clicked_surname_field = false;
        }
    }


    private void phone_fieldFocusGained(FocusEvent e) {
        if (!clicked_phone_field) {
            phone_field.setText("");
            phone_field.setForeground(Color.black);

            clicked_phone_field = true;
        }
    }

    private void phone_fieldKeyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            register();
        }
    }

    private void phone_fieldFocusLost(FocusEvent e) {
        if (phone_field.getText().equals("")) {
            phone_field.setText("Numar de telefon");
            clicked_phone_field = false;
        }
    }


    private void register_buttonActionPerformed(ActionEvent e) {
        register();
    }


    private void login_buttonActionPerformed(ActionEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.openLoginPage();
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
        name_icon = new JLabel();
        name_field = new JTextField();
        surname_icon = new JLabel();
        surname_field = new JTextField();
        phone_icon = new JLabel();
        phone_field = new JTextField();
        register_button = new JButton();
        login_description_label = new JLabel();
        login_button = new JButton();
        error_label = new JLabel();
        bg_label = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.
        swing.border.EmptyBorder(0,0,0,0), "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn",javax.swing.border
        .TitledBorder.CENTER,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog"
        ,java.awt.Font.BOLD,12),java.awt.Color.red), getBorder
        ())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java
        .beans.PropertyChangeEvent e){if("\u0062ord\u0065r".equals(e.getPropertyName()))throw new RuntimeException
        ();}});
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
        login_label.setText("Inregistrare");
        add(login_label);
        login_label.setBounds(0, 175, 210, 55);

        //---- email_icon ----
        email_icon.setIcon(new ImageIcon(getClass().getResource("/email_icon.png")));
        email_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(email_icon);
        email_icon.setBounds(250, 90, 50, 50);

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
        email_field.setBounds(300, 100, 300, email_field.getPreferredSize().height);

        //---- password_icon ----
        password_icon.setIcon(new ImageIcon(getClass().getResource("/password_icon.png")));
        password_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(password_icon);
        password_icon.setBounds(250, 140, 50, 50);

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
        password_field.setBounds(300, 150, 300, 33);

        //---- name_icon ----
        name_icon.setIcon(new ImageIcon(getClass().getResource("/name_icon.png")));
        name_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(name_icon);
        name_icon.setBounds(250, 190, 50, 50);

        //---- name_field ----
        name_field.setText("Nume");
        name_field.setBackground(Color.white);
        name_field.setBorder(new EtchedBorder());
        name_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        name_field.setForeground(new Color(153, 153, 153));
        name_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                name_fieldFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                name_fieldFocusLost(e);
            }
        });
        name_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                name_fieldKeyReleased(e);
            }
        });
        add(name_field);
        name_field.setBounds(300, 200, 300, 33);

        //---- surname_icon ----
        surname_icon.setIcon(new ImageIcon(getClass().getResource("/surname_icon.png")));
        surname_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(surname_icon);
        surname_icon.setBounds(250, 240, 50, 50);

        //---- surname_field ----
        surname_field.setText("Prenume");
        surname_field.setBackground(Color.white);
        surname_field.setBorder(new EtchedBorder());
        surname_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        surname_field.setForeground(new Color(153, 153, 153));
        surname_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                surname_fieldFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                surname_fieldFocusLost(e);
            }
        });
        surname_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                surname_fieldKeyReleased(e);
            }
        });
        add(surname_field);
        surname_field.setBounds(300, 250, 300, 33);

        //---- phone_icon ----
        phone_icon.setIcon(new ImageIcon(getClass().getResource("/phone_icon.png")));
        phone_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(phone_icon);
        phone_icon.setBounds(250, 290, 50, 50);

        //---- phone_field ----
        phone_field.setText("Numar de telefon");
        phone_field.setBackground(Color.white);
        phone_field.setBorder(new EtchedBorder());
        phone_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        phone_field.setForeground(new Color(153, 153, 153));
        phone_field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                phone_fieldFocusGained(e);
            }
            @Override
            public void focusLost(FocusEvent e) {
                phone_fieldFocusLost(e);
            }
        });
        phone_field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                phone_fieldKeyReleased(e);
            }
        });
        add(phone_field);
        phone_field.setBounds(300, 300, 300, 33);

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
        register_button.setHorizontalAlignment(SwingConstants.LEFT);
        register_button.setVerticalAlignment(SwingConstants.TOP);
        register_button.setIcon(new ImageIcon(getClass().getResource("/blue_button.png")));
        register_button.setForeground(Color.white);
        register_button.addActionListener(e -> register_buttonActionPerformed(e));
        add(register_button);
        register_button.setBounds(400, 350, 200, 50);

        //---- login_description_label ----
        login_description_label.setText("Ai deja cont?");
        login_description_label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        login_description_label.setForeground(new Color(102, 102, 102));
        add(login_description_label);
        login_description_label.setBounds(580, 525, 100, 16);

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
        login_button.setForeground(new Color(0, 0, 204));
        login_button.addActionListener(e -> login_buttonActionPerformed(e));
        add(login_button);
        login_button.setBounds(580, 530, 200, 50);

        //---- error_label ----
        error_label.setHorizontalAlignment(SwingConstants.CENTER);
        error_label.setForeground(Color.red);
        error_label.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 14));
        add(error_label);
        error_label.setBounds(225, 450, 400, 30);

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
    private JLabel name_icon;
    private JTextField name_field;
    private JLabel surname_icon;
    private JTextField surname_field;
    private JLabel phone_icon;
    private JTextField phone_field;
    private JButton register_button;
    private JLabel login_description_label;
    private JButton login_button;
    private JLabel error_label;
    private JLabel bg_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
