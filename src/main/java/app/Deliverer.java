package app;

public class Deliverer {
    private String name;
    private String surname;
    private String phoneNo;
    private Vehicle vehicle;
    private boolean busy;

    public enum Vehicle {NONE, BICYCLE, MOTORCYCLE, CAR}

    public Deliverer(String name, String surname, String phoneNo, Vehicle vehicle) {
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.vehicle = vehicle;
        this.busy = false;
    }

    public String getCompleteName() {
        return this.name + " " + this.surname;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
