/*
 * Benjamin Brown
 * DBUtil.java
 * Project part 2
 * 11/29/2021
 * 
 * A lot of this file is heavily based off of the canvas DBUtil file
 * */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {

    private static Connection connection;
    
    private DBUtil() {}

    public static synchronized Connection getConnection(String username, String password) {
        if (connection != null) {
            return connection;
        }
        else {
            try {
                // set the db url, username, and password
                String url = "jdbc:mysql://area-perimeterhw.cp79vduudv1c.us-east-1.rds.amazonaws.com/rectschema";
                // get and return connection
                connection = DriverManager.getConnection(
                        url, username, password);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }            
        }
        return connection;
    }
    
    public static synchronized void closeConnection() throws DBException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DBException(e);
            } finally {
                connection = null;                
            }
        }
    }
    
}
