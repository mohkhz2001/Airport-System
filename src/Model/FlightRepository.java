package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightRepository {

    Connection connection = new Connection();

    public List<Flight> flightList() {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_FLIGHT);

            ArrayList<Flight> flightArrayList = new ArrayList<>();

            while (resultSet.next()) {

                Flight flight = new Flight();

                flight.setFlightNumber(resultSet.getString(Tables.TABLE_FLIGHT_FLIGHTNUMBER));
                flight.setAirplaneRegister(resultSet.getString(Tables.TABLE_FLIGHT_AIRPLANEREGISTER));
                flight.setTicketID(resultSet.getString(Tables.TABLE_FLIGHT_TICKETID));
                flight.setDep(resultSet.getString(Tables.TABLE_FLIGHT_DEP));
                flight.setDes(resultSet.getString(Tables.TABLE_FLIGHT_DES));
                flight.setDate(resultSet.getString(Tables.TABLE_FLIGHT_DATE));
                flight.setHours(resultSet.getString(Tables.TABLE_FLIGHT_HOUR));
                flight.setFlightTime(resultSet.getString(Tables.TABLE_FLIGHT_TIMEFLIGHT));
                flight.setCapacity(resultSet.getInt(Tables.TABLE_FLIGHT_CAPACITY));
                flight.setPrice(resultSet.getInt(Tables.TABLE_FLIGHT_PRICE));
                flight.setTotalCapacity(resultSet.getInt(Tables.TABLE_FLIGHT_TOTAL_CAPACITY));

                if (resultSet.getString(Tables.TABLE_FLIGHT_STATUS).equals("open")) {
                    flight.setStatus(Flight.status.open);
                } else if (resultSet.getString(Tables.TABLE_FLIGHT_STATUS).equals("done")) {
                    flight.setStatus(Flight.status.done);
                } else {
                    flight.setStatus(Flight.status.takeoff);
                }

                flightArrayList.add(flight);
            }

            return flightArrayList;

        } catch (SQLException throwables) {

            System.out.println("theres problem in flight repo \n" + throwables);
            return null;

        }


    }

    public void capacityCorrection(int Capacity, String flightNumber) {
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("UPDATE " + Tables.TABLE_FLIGHT + " SET " + Tables.TABLE_FLIGHT_CAPACITY + "='" + Capacity + "'WHERE " + Tables.TABLE_FLIGHT_FLIGHTNUMBER + "=" + flightNumber );

        } catch (SQLException throwables) {
            System.out.println("there problem to change capacity \n" + throwables);
        }
    }
}
