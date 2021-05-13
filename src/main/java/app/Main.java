package app;

import app.config.SqlConfig;
import app.service.Service;

public class Main {

    public static void main(String[] args){
        Service service = Service.getInstance();

        service.connectDB();
        service.importAll();
        service.startApp();
    }
}
