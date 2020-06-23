package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    Connection connection = new Connection();

    public List<employee> employer() {

        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_USER);

            ArrayList<employee> arrayList = new ArrayList<>();

            while (resultSet.next()) {

                employee employee = new employee();
                employee.setFirstName(resultSet.getString(Tables.TABLE_USER_FIRSTNAME));
                employee.setLastName(resultSet.getString(Tables.TABLE_USER_LASTNAME));
                employee.setUsername(resultSet.getString(Tables.TABLE_USER_USERNAME));
                employee.setPassword(resultSet.getString(Tables.TABLE_USER_PASSWORD));
                employee.setID(resultSet.getString(Tables.TABLE_USER_ID));
                employee.setAddress(resultSet.getString(Tables.TABLE_USER_ADDRESS));
                employee.setSalary(resultSet.getString(Tables.TABLE_USER_SALARY));
                employee.setEmail(resultSet.getString(Tables.TABLE_USER_EMAIL));
                employee.setPhoneNumber(resultSet.getString(Tables.TABLE_USER_PHONENUMBER));


                if (resultSet.getString(Tables.TABLE_USER_JOB).equals("Employee")) {
                    employee.setJob(Person.Job.Employee);
                } else if (resultSet.getString(Tables.TABLE_USER_JOB).equals("Management"))
                    employee.setJob(Person.Job.Management);


                arrayList.add(employee);

            }

            return arrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }
}
