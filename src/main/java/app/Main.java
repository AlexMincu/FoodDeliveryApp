package app;

public class Main {

    public static void main(String[] args){
        Service service = Service.getInstance();

        service.importAll();
        service.startApp();
    }
}
