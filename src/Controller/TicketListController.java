package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.locks.Lock;

public class TicketListController implements Initializable {

    private String ID;
    private int prices;

    PassengerRepository passengerRepository = new PassengerRepository();
    TicketRepository ticketRepository = new TicketRepository();
    FlightRepository flightRepository = new FlightRepository();

    @FXML
    ImageView airplane;
    @FXML
    TableColumn<String, Flight> flightNumberColumn;
    @FXML
    TableColumn<String, Flight> DateColumn;
    @FXML
    TableColumn<String, Flight> depColumn;
    @FXML
    TableColumn<String, Flight> desColumn;
    @FXML
    TableColumn<String, Flight> priceColumn;
    @FXML
    TableColumn<String, Flight> hourColumn;
    @FXML
    TableColumn<String, Flight> numColumn;
    @FXML
    TableView<Flight> tableView;
    @FXML
    TextField flightNumberField;
    @FXML
    TextField depField;
    @FXML
    TextField desField;
    @FXML
    TextField dateField;
    @FXML
    TextField priceField;
    @FXML
    TextField hourField;
    @FXML
    Button buyBTN;
    @FXML
    ComboBox<Integer> numberChoose;
    @FXML
    Label priceLBL;
    @FXML
    Label errorLBL;
    @FXML
    ProgressBar progressBar;
    @FXML
    Label percentLBL;


    public void buyBTN() {

        if (moneyCheck() && capacityCheck()) {
            addTicket();
            decreaseCapacity();
            decreaseMoney();
        }

    }

    public void buyBTNEnter() {
        buyBTN.setGraphic(new ImageView("file:Icons/buy.png"));
        buyBTN.setText(null);
    }

    public void buyBTNExit() {
        buyBTN.setStyle("-fx-background-color:  #03e203");
        buyBTN.setGraphic(null);
        buyBTN.setText("buy");
    }

    public void numberChoose(ActionEvent a) {

        priceLBL.setText(Integer.toString(numberChoose.getSelectionModel().getSelectedItem() * prices));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(getID());

        numberChoose.getItems().addAll(0, 1, 2, 3, 4, 5);
        numberChoose.getSelectionModel().selectFirst();

        tableList();

        Flight flight = new Flight();
        {
            tableView.setRowFactory(tv -> {
                TableRow<Flight> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                            && event.getClickCount() == 1) {

                        Flight clickedRow = row.getItem();
                        prices = clickedRow.getPrice();
                        show(clickedRow.getFlightNumber());

                    }
                });
                return row;
            });
        }
    }

    private void tableList() {

        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        depColumn.setCellValueFactory(new PropertyValueFactory<>("dep"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("des"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        numColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        FlightRepository flightRepository = new FlightRepository();
        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getStatus() == Flight.status.open) {
                tableView.getItems().add(flights.get(i));
            }
        }

    }

    private void show(String flightNumber) {
        FlightRepository flightRepository = new FlightRepository();

        List<Flight> flights = flightRepository.flightList();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equals(flightNumber)) {
                progressBarShow(flights.get(i).getCapacity(), flights.get(i).getTotalCapacity());
                flightNumberField.setText(flights.get(i).getFlightNumber());
                depField.setText(flights.get(i).getDep());
                desField.setText(flights.get(i).getDes());
                dateField.setText(flights.get(i).getDate());
                priceField.setText(Integer.toString(flights.get(i).getPrice()));
                hourField.setText(flights.get(i).getHours());
            }
        }


    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    private void decreaseMoney() {

        List<passenger> passengerList = passengerRepository.passengerList();

        for (int i = 0; i < passengerList.size(); i++) {

            if (passengerList.get(i).getID().equals(getID())) {
                passengerRepository.increaseMoney(Integer.toString(Integer.parseInt(passengerList.get(i).getMoney()) - Integer.parseInt(priceLBL.getText())), getID());
            }

        }
    }

    private void decreaseCapacity() {
        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equals(flightNumberField.getText())) {
                String lineWithoutSpaces = flightNumberField.getText();
//                        /w
                flightRepository.capacityCorrection(flights.get(i).getCapacity() - numberChoose.getValue(), lineWithoutSpaces);
            }
        }
    }

    private boolean capacityCheck() {
        List<Flight> flights = flightRepository.flightList();
        Boolean check = false;

        for (int i = 0; i < flights.size(); i++) {

            if (flights.get(i).getFlightNumber().equals(flightNumberField.getText()) &&
                    flights.get(i).getCapacity() >= numberChoose.getValue()) {

                check = true;
                break;
            } else if (i == (flights.size() - 1)) {
                check = false;
            }
        }
        return check;
    }

    private boolean moneyCheck() {
        List<passenger> passengerList = passengerRepository.passengerList();
        boolean check = false;
        for (int i = 0; i < passengerList.size(); i++) {
            if (passengerList.get(i).getID().equals(getID()) && Integer.parseInt(passengerList.get(i).getMoney())
                    >= Integer.parseInt(priceLBL.getText())) {
                check = true;
            } else if (i == (passengerList.size() - 1))
                check = false;

        }
        return check;
    }

    private void addTicket() {

        Random rnd = new Random();

        for (int j = 0; j < numberChoose.getValue(); j++) {
            ticketRepository.TicketAdder(getID(), flightNumberField.getText(), Integer.parseInt(priceField.getText()), Integer.toString(rnd.nextInt(999999)));
        }
    }

    private void progressBarShow(int capacity, int totalCapacity) {

        progressBar.setProgress(capacity % totalCapacity);

        int a = capacity / totalCapacity;

        percentLBL.setText(Integer.toString(a) + "%");
    }
}
