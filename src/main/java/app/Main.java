package app;

import app.service.Service;

public class Main {

    public static void main(String[] args){
        Service service = Service.getInstance();

        // After the first boot, start the application with the second function 'startApp()'

//        service.firstStartApp();
        service.startApp();
    }
}
