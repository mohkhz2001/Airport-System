package Model;

public class Ticket extends Airplane {

    private String ID;
    private int price;
    private int cancelPrice;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCancelPrice() {
        return cancelPrice;
    }

    public void setCancelPrice(int cancelPrice) {
        this.cancelPrice = cancelPrice;
    }
}
