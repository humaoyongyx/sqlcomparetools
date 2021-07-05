package issac.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author humy6
 */
public class BaseConnectionUtils {

    protected static String driverClass = "com.mysql.cj.jdbc.Driver";


    protected static Connection getConnection(String url,String username,String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, username, password);
    }




    protected static void closeRsAndPst(ResultSet resultSet, PreparedStatement preparedStatement) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void closePrintWriter(PrintWriter printWriter) throws IOException {
        if (printWriter != null) {
            printWriter.close();
        }
    }
}
