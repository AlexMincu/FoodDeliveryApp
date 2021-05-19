package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterPage extends JFrame{

    private JPanel mainPanel;
    private JLabel registerLabel;
    private JPanel loginPanel;
    private JTextField nameField;
    private JButton registerButton;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel passwordLabel;
    private JLabel phoneLabel;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JTextField surnameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JButton backButton;
    private JLabel registerMessageLabel;

    public RegisterPage() {
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

        Logger logger = LogManager.getLogger(RegisterPage.class);
        logger.info("Initiated page");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == registerButton) {
                    // Convert char[] to String
                    String pass = "";
                    for (var c : passwordField.getPassword())
                        pass = pass.concat(String.valueOf(c));

                    // Create account
                    String conditions = service.register(emailField.getText(),
                            nameField.getText(),
                            surnameField.getText(),
                            phoneField.getText(),
                            pass,
                            "");

                    if (conditions.equals("11111")) {
                        registerMessageLabel.setText("Account created successfully");
                        registerMessageLabel.setForeground(Color.green);
                    } else {
                        registerMessageLabel.setText("Account cannot be created");
                        registerMessageLabel.setForeground(Color.red);
                    }
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose();
            }
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
