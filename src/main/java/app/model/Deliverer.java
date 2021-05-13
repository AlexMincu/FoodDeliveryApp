package app.model;

public class Deliverer {
    public enum Vehicle {NONE, BICYCLE, MOTORCYCLE, CAR}

    private String name;
    private String surname;
    private String phoneNo;
    private Vehicle vehicle;
    private boolean busy;

    public Deliverer(String name, String surname, String phoneNo, Vehicle vehicle) {
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.vehicle = vehicle;
        this.busy = false;
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

    public boolean isBusy() {
        return busy;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }


    @Override
    public String toString() {
        return  "        Deliverer information:        \n" +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Phone Number: " + phoneNo + "\n" +
                "Vehicle: " + vehicle.toString() + "\n" +
                "Busy: " + busy + "\n";
    }
}