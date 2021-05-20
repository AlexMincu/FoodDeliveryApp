package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginPage extends JFrame{
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JPanel loginPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;

    public LoginPage() {
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(450, 800);
        this.setResizable(false);
//        this.setLocationRelativeTo(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(screenSize.width/2 - this.getWidth()/2, 0);
        this.pack();
        this.setVisible(true);
        Service service = Service.getInstance();

        Logger logger = LogManager.getLogger(LoginPage.class);
        logger.info("Initiated page");


        loginButton.addActionListener(e -> {
            if(e.getSource() == loginButton) {
                // convert password to String
                String pass = "";
                for(var c : passwordField.getPassword())
                    pass = pass.concat(String.valueOf(c));

                // login action
                if(service.login(emailField.getText(), pass)) {
                    new RestaurantsPage();
                    dispose();
                }

            }
        });

        registerButton.addActionListener(e -> {
            new RegisterPage();
            dispose();
        });


        // Export everything when closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("Closing app");
                dispose();
                System.exit(0);
            }
        });
    }
}
