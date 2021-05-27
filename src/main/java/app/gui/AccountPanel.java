package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class AccountPanel extends JPanel {
    Service service = Service.getInstance();
    private final Logger logger = LogManager.getLogger(AccountPanel.class);

    public AccountPanel() {
        logger.debug("Creating Account Page");
        initComponents();

        var account = service.getCurrentAccount();

        email_field.setText(account.getEmail());
        password_field.setText(account.getPassword());
        password_field2.setText(account.getPassword());
        name_field.setText(account.getName());
        surname_field.setText(account.getSurname());
        phone_field.setText(account.getPhoneNo());

        logger.debug("Account page created successfully");
    }

    private void exit_buttonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void confirm_buttonActionPerformed(ActionEvent e) {
        boolean email_check = false;
        boolean password_check = false;
        boolean name_check = false;
        boolean surname_check = false;
        boolean phoneNo_check = false;

        var account = service.getCurrentAccount();


        String pass1 = "";
        for (var c : password_field.getPassword())
            pass1 = pass1.concat(String.valueOf(c));
        String pass2 = "";
        for (var c : password_field2.getPassword())
            pass2 = pass2.concat(String.valueOf(c));

        
        // Check account password
        if (pass1.contains(" ") ||
                !pass1.equals(pass2)) {

            password_field.setBackground(Color.pink);
            password_field2.setBackground(Color.pink);
        }
        else {
            password_field.setBackground(Color.white);
            password_field2.setBackground(Color.white);
            password_check = true;
        }

        // Check account name
        if (Pattern.matches("[\\W\\d]+", name_field.getText())) {
            name_field.setBackground(Color.pink);
        }
        else {
            name_field.setBackground(Color.white);
            name_check = true;
        }

        // Check account surname
        if (Pattern.matches("[\\W\\d]+", surname_field.getText())) {
            surname_field.setBackground(Color.pink);
        }
        else {
            surname_field.setBackground(Color.white);
            surname_check = true;
        }

        // Check account Phone Number
        if (Pattern.matches("[^\\d]+", phone_field.getText()) ||
                phone_field.getText().length() != 10) {
            phone_field.setBackground(Color.pink);
        }
        else {
            phone_field.setBackground(Color.white);
            phoneNo_check = true;
        }

        if(password_check && name_check && surname_check && phoneNo_check) {
            error_label.setText("");

            if(!pass1.equals(account.getPassword()) && !pass1.isEmpty()) {
                account.setPassword(pass1);
                service.updateAccountPasswordFromDB(account.getPassword(), account.getEmail());
            }

            if(!name_field.getText().equals(account.getName()) && !name_field.getText().isEmpty()) {
                account.setName(name_field.getText());
                service.updateAccountNameFromDB(account.getName(), account.getEmail());
            }

            if(!surname_field.getText().equals(account.getName()) && !surname_field.getText().isEmpty()) {
                account.setSurname(surname_field.getText());
                service.updateAccountSurnameFromDB(account.getSurname(), account.getEmail());
            }

            if(!phone_field.getText().equals(account.getPhoneNo()) && !phone_field.getText().isEmpty()) {
                account.setPhoneNo(phone_field.getText());
                service.updateAccountPhoneNoFromDB(account.getPhoneNo(), account.getEmail());
            }

            AppFrame app = AppFrame.getInstance();
            app.openRestaurantsPage();
        }
        else {
            error_label.setText("Informatii invalide, va rugam sa incercati din nou");
        }
    }

    private void disconnect_buttonActionPerformed(ActionEvent e) {
        AppFrame app = AppFrame.getInstance();
        app.closeAccountPage();
        service.setCurrentAccount(null);
        app.openWelcomePage();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        exit_button = new JButton();
        icon_label = new JLabel();
        title_label = new JLabel();
        title_label2 = new JLabel();
        email_icon = new JLabel();
        email_field = new JTextField();
        password_icon = new JLabel();
        password_field = new JPasswordField();
        password_field2 = new JPasswordField();
        name_icon = new JLabel();
        name_field = new JTextField();
        surname_icon = new JLabel();
        surname_field = new JTextField();
        phone_icon = new JLabel();
        phone_field = new JTextField();
        confirm_button = new JButton();
        disconnect_button = new JButton();
        error_label = new JLabel();
        bg_label = new JLabel();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing.
        border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border . TitledBorder. CENTER
        ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog", java .awt . Font
        . BOLD ,12 ) ,java . awt. Color .red ) , getBorder () ) );  addPropertyChangeListener(
        new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r"
        .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
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

        //---- title_label ----
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        title_label.setOpaque(false);
        title_label.setBorder(null);
        title_label.setForeground(Color.white);
        title_label.setText("Informatiile");
        add(title_label);
        title_label.setBounds(0, 175, 210, 55);

        //---- title_label2 ----
        title_label2.setHorizontalAlignment(SwingConstants.CENTER);
        title_label2.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
        title_label2.setOpaque(false);
        title_label2.setBorder(null);
        title_label2.setForeground(Color.white);
        title_label2.setText("mele");
        add(title_label2);
        title_label2.setBounds(0, 215, 210, 35);

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
        email_field.setEnabled(false);
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
        add(password_field);
        password_field.setBounds(300, 150, 300, 33);

        //---- password_field2 ----
        password_field2.setText("Parola");
        password_field2.setBackground(Color.white);
        password_field2.setBorder(new EtchedBorder());
        password_field2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        password_field2.setForeground(new Color(153, 153, 153));
        add(password_field2);
        password_field2.setBounds(300, 195, 300, 33);

        //---- name_icon ----
        name_icon.setIcon(new ImageIcon(getClass().getResource("/name_icon.png")));
        name_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(name_icon);
        name_icon.setBounds(250, 265, 50, 50);

        //---- name_field ----
        name_field.setText("Nume");
        name_field.setBackground(Color.white);
        name_field.setBorder(new EtchedBorder());
        name_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        name_field.setForeground(new Color(153, 153, 153));
        add(name_field);
        name_field.setBounds(300, 275, 300, 33);

        //---- surname_icon ----
        surname_icon.setIcon(new ImageIcon(getClass().getResource("/surname_icon.png")));
        surname_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(surname_icon);
        surname_icon.setBounds(250, 315, 50, 50);

        //---- surname_field ----
        surname_field.setText("Prenume");
        surname_field.setBackground(Color.white);
        surname_field.setBorder(new EtchedBorder());
        surname_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        surname_field.setForeground(new Color(153, 153, 153));
        add(surname_field);
        surname_field.setBounds(300, 325, 300, 33);

        //---- phone_icon ----
        phone_icon.setIcon(new ImageIcon(getClass().getResource("/phone_icon.png")));
        phone_icon.setHorizontalAlignment(SwingConstants.CENTER);
        add(phone_icon);
        phone_icon.setBounds(250, 365, 50, 50);

        //---- phone_field ----
        phone_field.setText("Numar de telefon");
        phone_field.setBackground(Color.white);
        phone_field.setBorder(new EtchedBorder());
        phone_field.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        phone_field.setForeground(new Color(153, 153, 153));
        add(phone_field);
        phone_field.setBounds(300, 375, 300, 33);

        //---- confirm_button ----
        confirm_button.setText("Confirmare");
        confirm_button.setHorizontalTextPosition(SwingConstants.CENTER);
        confirm_button.setIconTextGap(0);
        confirm_button.setOpaque(false);
        confirm_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        confirm_button.setContentAreaFilled(false);
        confirm_button.setBorderPainted(false);
        confirm_button.setBorder(null);
        confirm_button.setPressedIcon(new ImageIcon(getClass().getResource("/blue_button_pressed.png")));
        confirm_button.setHorizontalAlignment(SwingConstants.LEFT);
        confirm_button.setVerticalAlignment(SwingConstants.TOP);
        confirm_button.setIcon(new ImageIcon(getClass().getResource("/blue_button.png")));
        confirm_button.setForeground(Color.white);
        confirm_button.addActionListener(e -> confirm_buttonActionPerformed(e));
        add(confirm_button);
        confirm_button.setBounds(400, 425, 200, 50);

        //---- disconnect_button ----
        disconnect_button.setText("Deconectare");
        disconnect_button.setHorizontalTextPosition(SwingConstants.CENTER);
        disconnect_button.setIconTextGap(0);
        disconnect_button.setOpaque(false);
        disconnect_button.setFont(new Font("Segoe UI Semibold", Font.BOLD, 18));
        disconnect_button.setContentAreaFilled(false);
        disconnect_button.setBorderPainted(false);
        disconnect_button.setBorder(null);
        disconnect_button.setPressedIcon(new ImageIcon(getClass().getResource("/white_button3_pressed.png")));
        disconnect_button.setHorizontalAlignment(SwingConstants.LEFT);
        disconnect_button.setVerticalAlignment(SwingConstants.TOP);
        disconnect_button.setIcon(new ImageIcon(getClass().getResource("/white_button3.png")));
        disconnect_button.setForeground(Color.darkGray);
        disconnect_button.addActionListener(e -> disconnect_buttonActionPerformed(e));
        add(disconnect_button);
        disconnect_button.setBounds(5, 540, 200, 50);

        //---- error_label ----
        error_label.setHorizontalAlignment(SwingConstants.CENTER);
        error_label.setForeground(Color.red);
        error_label.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 14));
        add(error_label);
        error_label.setBounds(230, 495, 400, 30);

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
    private JLabel title_label;
    private JLabel title_label2;
    private JLabel email_icon;
    private JTextField email_field;
    private JLabel password_icon;
    private JPasswordField password_field;
    private JPasswordField password_field2;
    private JLabel name_icon;
    private JTextField name_field;
    private JLabel surname_icon;
    private JTextField surname_field;
    private JLabel phone_icon;
    private JTextField phone_field;
    private JButton confirm_button;
    private JButton disconnect_button;
    private JLabel error_label;
    private JLabel bg_label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
