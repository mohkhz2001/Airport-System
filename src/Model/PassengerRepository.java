package Model;

import javax.swing.table.TableColumn;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepository {


    Connection connection = new Connection();


    public List<passenger> passengerList() {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_PASSENGER);
//            System.out.println("Done");
            ArrayList<passenger> passengerArrayList = new ArrayList<>();

            while (resultSet.next()) {
                passenger passenger = new passenger();

                passenger.setFirstName(resultSet.getString(Tables.TABLE_PASSENGER_FIRSTNAME));
                passenger.setLastName(resultSet.getString(Tables.TABLE_PASSENGER_LASTNAME));
                passenger.setUsername(resultSet.getString(Tables.TABLE_PASSENGER_USERNAME));
                passenger.setPassword(resultSet.getString(Tables.TABLE_PASSENGER_PASSWORD));
                passenger.setEmail(resultSet.getString(Tables.TABLE_PASSENGER_EMAIL));
                passenger.setMoney(resultSet.getString(Tables.TABLE_PASSENGER_MONEY));
                passenger.setID(resultSet.getString(Tables.TABLE_PASSENGER_PASSID));
                passenger.job(Person.Job.Passenger);

                passengerArrayList.add(passenger);
            }
            return passengerArrayList;

        } catch (SQLException throwables) {
            System.out.println("there's problem to executeQuery in pass repo...\n" + throwables);
            return null;
        }


    }

}
