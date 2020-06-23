package Model;

public class Flight {

    public enum status{open , takeoff, done}

    private String flightNumber;
    private String airplaneRegister;
    private String ticketID;
    private String dep;
    private String des;
    private String date;
    private String hours;
    private String flightTime;
    private status status;
    private int capacity;
    private int price;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirplaneRegister() {
        return airplaneRegister;
    }

    public void setAirplaneRegister(String airplaneRegister) {
        this.airplaneRegister = airplaneRegister;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public Flight.status getStatus() {
        return status;
    }

    public void setStatus(Flight.status status) {
        this.status = status;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
