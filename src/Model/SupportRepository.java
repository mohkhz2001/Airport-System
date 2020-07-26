package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupportRepository {

    Connection connection = new Connection();

    public List<Support> supports() {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.connection().createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + Tables.TABLE_SUPPORT);
            ArrayList<Support> supports = new ArrayList<>();
            while (resultSet.next()) {
                Support support = new Support();
                support.setNumber(resultSet.getInt(Tables.TABLE_SUPPORT_NUMBER));
                support.setIdea(resultSet.getString(Tables.TABLE_SUPPORT_IDEA));
                if (resultSet.getInt(Tables.TABLE_SUPPORT_READ) == 0) {
                    support.setRead(true);
                } else
                    support.setRead(false);
//                support.setRead(resultSet.getInt(Tables.TABLE_SUPPORT_READ));

                supports.add(support);
            }
            return supports;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }


}
