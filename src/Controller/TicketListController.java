package Controller;

import Model.Flight;
import Model.FlightRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TicketListController implements Initializable {

    private String ID;
    private int prices;

    @FXML
    ImageView takeoff;
    @FXML
    ImageView landing;
    @FXML
    ImageView flightNumber;
    @FXML
    ImageView date;
    @FXML
    ImageView price;
    @FXML
    ImageView airplane;
    @FXML
    ImageView timeImage;
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

    public void buyBTN() {

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

        takeoff.setImage(new Image("file:Icons/takeoff.png"));
        landing.setImage(new Image("file:Icons/landing.png"));
        flightNumber.setImage(new Image("file:Icons/flightNumber.png"));
        date.setImage(new Image("file:Icons/date.png"));
        price.setImage(new Image("file:Icons/price.png"));
        airplane.setImage(new Image("file:Icons/airplane.png"));
        timeImage.setImage(new Image("file:Icons/time.png"));

        numberChoose.getItems().addAll(1, 2, 3, 4, 5);
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
}
