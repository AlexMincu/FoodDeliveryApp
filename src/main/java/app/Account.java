package app;

public class Account {
    private String email;
    private String name;
    private String surname;
    private String phoneNo;
    private String password;
    private String address;

    public Account(String email, String name, String surname, String phoneNo, String password, String address) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.password = password;
        this.address = address;
    }

    public String getEmail() {
        return email;
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

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return  "        Account information:        \n" +
                "------------------------------------\n" +
                "Email: " + email + "\n" +
                "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Phone Number: " + phoneNo + "\n" +
                "Password: " + password + "\n";
    }
}
