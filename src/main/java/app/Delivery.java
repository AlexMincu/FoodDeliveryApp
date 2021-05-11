/*
        NEEDS REVAMP
 */

package app;

public class Delivery {
    private Account account;
    private String address;
    private Receipt receipt;
    private Deliverer deliverer;

    public Delivery(Account account, String address, Receipt receipt) {
        this.account = account;
        this.address = address;
        this.receipt = receipt;
    }

    public void setDeliverer(Deliverer deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public String toString() {
        return "Delivery made by: " + deliverer.getCompleteName() + "\n" + receipt.toString();
    }

    public Account getAccount() {
        return account;
    }

    public String getAddress() {
        return address;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public Deliverer getDeliverer() {
        return deliverer;
    }
}
