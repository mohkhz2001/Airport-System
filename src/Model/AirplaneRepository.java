package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AirplaneRepository {

    Connection connection = new Connection();

    public List<Airplane> airplaneList() {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_AIRPLANE);

            ArrayList<Airplane> flightArrayList = new ArrayList<>();
            while (resultSet.next()){

                Airplane airplane = new Airplane();

                airplane.setRegister(resultSet.getString(Tables.TABLE_AIRPLANE_REGISTER));
                airplane.setType(resultSet.getString(Tables.TABLE_AIRPLANE_TYPE));
                airplane.setSeats(resultSet.getInt(Tables.TABLE_AIRPLANE_SEAT));

                flightArrayList.add(airplane);
            }

            return flightArrayList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

}
