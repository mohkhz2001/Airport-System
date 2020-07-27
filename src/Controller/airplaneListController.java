package Controller;

import Model.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class airplaneListController implements Initializable {

    AirplaneRepository airplaneRepository = new AirplaneRepository();
    FlightRepository flightRepository = new FlightRepository();
    TicketRepository ticketRepository = new TicketRepository();
    UserRepository userRepository;


    private ContextMenu flightList;
    private MenuItem passList;
    private MenuItem edit;
    private MenuItem remove;
    private String Job;
    private ContextMenu airplaneList;
    private MenuItem airplaneRemove;
    private MenuItem airplaneEdit;


    private Flight flight;
    private Airplane airplane;


    @FXML
    TableView<Airplane> airplaneListTable;
    @FXML
    TableColumn<String, Airplane> registerColumn;
    @FXML
    TableColumn<String, Airplane> seatColumn;
    @FXML
    TableColumn<String, Airplane> typeColumn;
    @FXML
    TableColumn<String, Flight> flightNumberColumn;
    @FXML
    TableColumn<String, Flight> dateColumn;
    @FXML
    TableColumn<String, Flight> depColumn;
    @FXML
    TableColumn<String, Flight> desColumn;
    @FXML
    TableColumn<String, Flight> statusColumn;
    @FXML
    TableColumn<String, Flight> hoursColumn;
    @FXML
    TableView<Flight> flightListTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableShow();
        ActionOnTable();
        flightListAction();
    }

    public void loginAs() {
        if (getJob().equals("Management")) {
            management();
            managementAction();
        } else {
            employee();
            employeeAction();
        }
    }

    private void employee() {

        flightList = new ContextMenu();
        passList = new MenuItem("List of passenger");
        edit = new MenuItem("Edit");
        remove = new MenuItem("Remove");

        flightList.getItems().addAll(passList, edit, remove);

        flightListTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    flightList.show(flightListTable, t.getScreenX(), t.getScreenY());
                }
            }
        });


    }

    private void management() {
        employee();
        employeeAction();

        airplaneList = new ContextMenu();
        airplaneEdit = new MenuItem("Edit");
        airplaneRemove = new MenuItem("Remove");
        airplaneList.getItems().addAll(airplaneEdit, airplaneRemove);

        airplaneListTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (t.getButton() == MouseButton.SECONDARY) {
                    airplaneList.show(airplaneListTable, t.getScreenX(), t.getScreenY());
                }
            }
        });

    }

    public void TableShow() {

        registerColumn.setCellValueFactory(new PropertyValueFactory<>("register"));
        seatColumn.setCellValueFactory(new PropertyValueFactory<>("seats"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        List<Airplane> airplaneList = airplaneRepository.airplaneList();

        for (int i = 0; i < airplaneList.size(); i++) {
            airplaneListTable.getItems().addAll(airplaneList.get(i));
        }
    }

    private void ActionOnTable() {

        airplaneListTable.setRowFactory(tv -> {
            TableRow<Airplane> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY
                        && event.getClickCount() == 1) {

                    Airplane clickedRow = row.getItem();
                    airplane = clickedRow;
                }
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    Airplane clickedRow = row.getItem();
                    flightsList(clickedRow);
                }

            });
            return row;
        });

    }

    private void flightsList(Airplane info) {

        flightListTable.getItems().clear();

        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        depColumn.setCellValueFactory(new PropertyValueFactory<>("dep"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("des"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));

        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getAirplaneRegister().equals(info.getRegister())) {
                flightListTable.getItems().addAll(flights.get(i));
            }
        }

    }

    private void flightListAction() {

        flightListTable.setRowFactory(tv -> {
            TableRow<Flight> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.SECONDARY
                        && event.getClickCount() == 1) {

                    Flight clickedRow = row.getItem();
                    flight = clickedRow;

                    System.out.println("Done");
                }

            });
            return row;
        });
    }

    private void employeeAction() {

        passList.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PassengerList.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PassengerListController passengerListController = loader.getController();
            passengerListController.TableShow(flight);
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();

        });

        edit.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EditFlight.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            EditFlightController editFlightController = loader.getController();
            editFlightController.showFlight(flight);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
//            stage.setResizable(false);
            stage.show();
        });

        remove.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + flight.getFlightNumber() + " and delete all tickets soled " + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                boolean done = flightRepository.removeFlight(flight.getFlightNumber());

                if (done) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "removed " + flight.getFlightNumber(), ButtonType.CLOSE);
                    alert1.showAndWait();
                    ticketRepository.removeTicket(flight.getFlightNumber());
                }
            } else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR, "cant remove " + flight.getFlightNumber(), ButtonType.CLOSE);
                alert1.showAndWait();
            }

        });
    }

    private void managementAction() {

        airplaneRemove.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "do you really want to remove this airplane with all flight ??\n(this will remove all the ticket of this airplane)", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                boolean remove = airplaneRepository.removeAirplane(airplane.getRegister());
                removeFlight();
                airplaneList.getItems().clear();
                tableRefresh();
            }
        });

        airplaneEdit.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/editAirplane.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            editAirplaneController editAirplaneController = loader.getController();
            editAirplaneController.setFields(airplane);

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.getRoot()));
            stage.show();
        });

    }

    private void removeFlight() {
        List<Flight> flights = flightRepository.flightList();
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getAirplaneRegister().equals(airplane.getRegister())) {
                flightRepository.removeFlight(flights.get(i).getFlightNumber());
                ticketRepository.removeTicket(flights.get(i).getFlightNumber());
            }
        }
    }

    private void tableRefresh() {

        TableShow();
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }
}
