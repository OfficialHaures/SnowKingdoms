package nl.officialhaures.snow.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

   private static  String DB_HOST = "localhost";
   private static int DB_PORT = 3306;
   private static String DB_NAME = "kingdom";
   private static String DB_USER = "root";
   private static String DB_PASSWORD = "";

     public static Connection connection;

    public static Connection getConnection() {
        if(connection == null ){
            try {
                String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
                connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
