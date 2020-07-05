package Controller;

import Model.Flight;
import Model.FlightRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FlightsListBTNController implements Initializable {

    @FXML
    TableColumn<String, Flight> flightNumberColumn;
    @FXML
    TableColumn<String, Flight> hoursColumn;
    @FXML
    TableColumn<String, Flight> dateColumn;
    @FXML
    TableColumn<String, Flight> statusColumn;
    @FXML
    TableColumn<String, Flight> availableColumn;
    @FXML
    TableColumn<String, Flight> TCapacityColumn;
    @FXML
    TableColumn<String, Flight> depColumn;
    @FXML
    TableColumn<String, Flight> desColumn;
    @FXML
    TableColumn<String, Flight> registerColumn;
    @FXML
    TableView<Flight> flightTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableShow();
        ActionOnTable();
    }

    private void TableShow() {

        flightNumberColumn.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        depColumn.setCellValueFactory(new PropertyValueFactory<>("dep"));
        desColumn.setCellValueFactory(new PropertyValueFactory<>("des"));
        registerColumn.setCellValueFactory(new PropertyValueFactory<>("airplaneRegister"));
        TCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("totalCapacity"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        hoursColumn.setCellValueFactory(new PropertyValueFactory<>("hours"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        FlightRepository flightRepository = new FlightRepository();
        List<Flight> flights = flightRepository.flightList();

        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getStatus() == Flight.status.open) {
                flightTable.getItems().add(flights.get(i));
            }
        }

    }

    private void ActionOnTable() {
        Flight flight = new Flight();
        {
            flightTable.setRowFactory(tv -> {
                TableRow<Flight> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                            && event.getClickCount() == 1) {

                        Flight clickedRow = row.getItem();
                        System.out.println("Done");
                    }

                });
                return row;
            });
        }
    }

}

