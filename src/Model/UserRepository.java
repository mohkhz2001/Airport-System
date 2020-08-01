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
                else if (resultSet.getString(Tables.TABLE_USER_JOB).equals("superAdmin"))
                    employee.setJob(Person.Job.superAdmin);
                
                arrayList.add(employee);

            }

            return arrayList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

    public boolean newEmployee(String firstName, String lastName, String ID, String username, String password, String email, String phoneNumber, String Job, String salary) {

        Statement statement;
        try {
            statement = connection.connection().createStatement();
            statement.execute("INSERT INTO " + Tables.TABLE_USER + "(" + Tables.TABLE_USER_FIRSTNAME + " , " + Tables.TABLE_USER_LASTNAME + " , " + Tables.TABLE_USER_ID + " , "
                    + Tables.TABLE_USER_USERNAME + " , " + Tables.TABLE_USER_PASSWORD + " , " + Tables.TABLE_USER_EMAIL + " , " + Tables.TABLE_USER_PHONENUMBER + " , " + Tables.TABLE_USER_JOB + " , " + Tables.TABLE_USER_SALARY + " )" +
                    "VALUES ( '" + firstName + "' , '" + lastName + "' , '" + ID + "' , '" + username + "' , '" + password + "' , '" + email + "' , '" + phoneNumber + "' , '" + Job + "' , '" + salary + "'   )");
            return true;
        } catch (SQLException throwables) {
            System.out.println("can't add new employee ....\n" + throwables);
            return false;
        }

    }

    public boolean updateEmployee(String ID, String Job, String salary) {
        Statement statement = null;
        try {
            statement = connection.connection().createStatement();
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_SALARY + "='" + salary + "'WHERE " + Tables.TABLE_USER_ID + "=" + ID);
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_JOB + "='" + Job + "'WHERE " + Tables.TABLE_USER_ID + "=" + ID);
            return true;
        } catch (SQLException throwables) {
            System.out.println("cant update the salary or job\n" + throwables);
            return false;
        }
    }

    public Boolean removeEmployee(String ID) {
        Statement statement;

        try {
            statement = connection.connection().createStatement();
            statement.execute("DELETE FROM " + Tables.TABLE_USER + " WHERE " + Tables.TABLE_USER_ID + "='" + ID + "'");
            return true;
        } catch (SQLException throwables) {
            System.out.println("there's problem to remove \n" + throwables);
            return false;
        }
    }

    public boolean infoUpdate(String Id, String address, String email, String phoneNumber, String username, String password) {
        Statement statement;
        try {
            statement = connection.connection().createStatement();

            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_USERNAME + "='" + username + "'WHERE " + Tables.TABLE_USER_ID + "='" + Id + "'");
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_PASSWORD + "='" + password + "'WHERE " + Tables.TABLE_USER_ID + "='" + Id + "'");
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_PHONENUMBER + "='" + phoneNumber + "'WHERE " + Tables.TABLE_USER_ID + "='" + Id + "'");
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_EMAIL + "='" + email + "'WHERE " + Tables.TABLE_USER_ID + "='" + Id + "'");
            statement.execute("UPDATE " + Tables.TABLE_USER + " SET " + Tables.TABLE_USER_ADDRESS + "='" + address + "'WHERE " + Tables.TABLE_USER_ID + "='" + Id + "'");
            return true;
        } catch (SQLException throwables) {
            System.out.println("theres problem to UPDATE INFO \n" + throwables);
            return false;
        }
    }
}
