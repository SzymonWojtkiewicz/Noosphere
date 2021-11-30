package Database;

import java.sql.*;

public class DatabaseManager {
    Connection conn = null;
    ResultSet result = null;
    String dbName = "NOOSPHERE";

    public void connectToMysql(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?" + "user=root&password=");

            if(conn != null){
                System.out.println("Connected");
            }

        } catch (Exception ex){
            System.out.println("Unable to connect");
            System.out.println("SQLException: " + ex.getMessage());
        }

    }

    public void connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" +dbName+ "?user=root&password=");

            if(conn != null){
                System.out.println("Connected to database");
            }

        } catch (Exception ex){
            System.out.println("Unable to connect to database");
            System.out.println("SQLException: " + ex.getMessage());
        }

    }

    public void createDatabase(){
        if(conn == null){
            connectToMysql();
        }
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE "+ dbName;
            stmt.executeUpdate(sql);
            System.out.println("Created database");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUsersTable(){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE USERS (id INTEGER not NULL AUTO_INCREMENT, username VARCHAR(255), email VARCHAR(255)," +
                    " password VARCHAR(255), administrator BIT, UNIQUE(username, email), PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createVideosTable(){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE VIDEOS (id INTEGER not NULL AUTO_INCREMENT, title VARCHAR(255), director VARCHAR(255), genre VARCHAR(255), language VARCHAR(255), PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(String username, String email, String password, boolean administrator){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO USERS (username, email, password, administrator) VALUES ( " +username+ ", " +email+ ", " +password+ ", " +administrator+ ")";
            stmt.executeUpdate(sql);
            System.out.println("Created user");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void upgradeToAdmin(String username){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE USERS SET administrator = 1 WHERE username in ( " +username+ ")";
            stmt.executeUpdate(sql);
            System.out.println("Edited user");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void downgradeToUser(String username){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "UPDATE USERS " + "SET administrator = 0 WHERE username in (" +username+ ")";
            stmt.executeUpdate(sql);
            System.out.println("Edited user");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
