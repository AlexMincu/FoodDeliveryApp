package app.gui;

import app.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class RestaurantsPage extends JFrame {
    private JPanel mainPanel;
    private JLabel topLabel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;

    public RestaurantsPage() {
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

        Logger logger = LogManager.getLogger(RestaurantsPage.class);
        logger.info("Initiated page");

        service.flushCart();

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Insert restaurant panels
        for(var id : service.getLocals().keySet()) {
            RestaurantPane rest = new RestaurantPane(this, service.getLocals().get(id));
            rest.setAlignmentX(JPanel.LEFT_ALIGNMENT);
            rest.setAlignmentY(JPanel.TOP_ALIGNMENT);
            contentPanel.add(rest);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        validate();


        // Export everything when closing the window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                logger.info("Closing app");
                service.exportAll();
                dispose();
                System.exit(0);
            }
        });
    }

}
