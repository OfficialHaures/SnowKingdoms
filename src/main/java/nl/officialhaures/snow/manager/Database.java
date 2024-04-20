package nl.officialhaures.snow.manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static String DB_HOST = "localhost";
    private static int DB_PORT = 3306;
    private static String DB_NAME = "kingdom";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "";

    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
                connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);
            } catch (SQLException e) {
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

    public static void executeQuery(String query) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getKingdoms() {
        List<String> kingdoms = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM kingdoms");
            while (resultSet.next()) {
                kingdoms.add(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kingdoms;
    }

    public static void initialize() {


        try {
            getConnection();
            String createKingdomsTable = "CREATE TABLE IF NOT EXISTS kingdoms (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT UNIQUE NOT NULL," +
                    "king TEXT NOT NULL" +
                    ")";
            executeUpdate(createKingdomsTable);

            String createPlayersTable = "CREATE TABLE IF NOT EXISTS players (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT UNIQUE NOT NULL," +
                    "kingdom TEXT," +
                    "FOREIGN KEY(kingdom) REFERENCES kingdoms(name) ON DELETE SET NULL" +
                    ")";
            executeUpdate(createPlayersTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void executeUpdate(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }
}
