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

    public boolean PassengerAdder(String firstName, String lastName, String username, String email, String password, String money, String ID) {

        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("INSERT INTO " + Tables.TABLE_PASSENGER + "(" + Tables.TABLE_PASSENGER_FIRSTNAME + " , " + Tables.TABLE_PASSENGER_LASTNAME + " , " + Tables.TABLE_PASSENGER_USERNAME + " , "
                    + Tables.TABLE_PASSENGER_EMAIL + " , " + Tables.TABLE_PASSENGER_PASSWORD + " , " + Tables.TABLE_PASSENGER_MONEY + " , " + Tables.TABLE_PASSENGER_PASSID + " )" +
                    "VALUES ( '" + firstName + "' , '" + lastName + "' , '" + username + "' , '" + email + "' , '" + password + "' , '" + money + "' , '" + ID + "'  )");
            return true;
        } catch (SQLException e) {
            System.out.println("Error in add the new passenger " + e);
            return false;
        }
    }

    public boolean PassengerUpdate(String email , String password , String ID) {
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("UPDATE " + Tables.TABLE_PASSENGER + " SET " + Tables.TABLE_PASSENGER_EMAIL + "='" + email + "'WHERE " + Tables.TABLE_PASSENGER_PASSID + "=" + ID);
            statement.execute("UPDATE " +Tables.TABLE_PASSENGER + " SET " + Tables.TABLE_PASSENGER_PASSWORD + "='" + password + "'WHERE " + Tables.TABLE_PASSENGER_PASSID + "=" + ID);
            return true;
        } catch (SQLException e) {
            System.out.println("error in update the passenger information" + e);
            return false;
        }
    }

    public void increaseMoney(String money , String ID) {
        // this money its total (before plus now wan tto add).....
        try {
            Statement statement = connection.connection().createStatement();
            statement.execute("UPDATE " + Tables.TABLE_PASSENGER+ " SET " + Tables.TABLE_PASSENGER_MONEY + "='" + money + "'WHERE " + Tables.TABLE_PASSENGER_PASSID + "='" + ID+ "'" );


        } catch (SQLException e) {
            System.out.println("error in update the passenger information" + e);
        }
    }

}
