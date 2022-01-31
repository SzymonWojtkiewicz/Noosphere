package Database;

import java.sql.*;

public class DatabaseManager implements Database{
    Connection conn = null;
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
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connecting to local database
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/" +dbName+ "?user=root&password=");
            //connecting to remote database
            String url = "jdbc:mysql://192.166.219.220/bigflex";
            conn = DriverManager.getConnection(url, "bigflex", "f5IyhvPF..");

            if(conn != null){
                System.out.println("Connected to database");
            }

        } catch (Exception ex){
            System.out.println("Unable to connect to database");
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
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
            String sql = "CREATE TABLE USERS (id INTEGER not NULL AUTO_INCREMENT, name VARCHAR(255), surname VARCHAR(255), username VARCHAR(255), email VARCHAR(255)," +
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
            String sql = "CREATE TABLE VIDEOS (id INTEGER not NULL AUTO_INCREMENT, title VARCHAR(255), director VARCHAR(255), genre VARCHAR(255)," +
                    " language VARCHAR(255), source VARCHAR(255), PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void inputVideo(String title, String director, String genre, String language, String source){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO VIDEOS (title, director, genre, language, source) VALUES ( " +title+ ", " +director+ ", " +genre+ ", " +language+ ", " +source+ ")";
            stmt.executeUpdate(sql);
            System.out.println("Inputted video successfully");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayInformationVideo(String title, String director){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT title, director, genre, language FROM VIDEOS WHERE title = '" + title + "' AND director = '" + director + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("title"));
            System.out.println(rs.getString("director"));
            System.out.println(rs.getString("genre"));
            System.out.println(rs.getString("language"));
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String displaySourceVideo(String title, String director){
        connectToDatabase();
        String source = null;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT source FROM VIDEOS WHERE title = '" + title + "' AND director = '" + director + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            source = rs.getString("source");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return source;
    }

    public void createAccount(String name, String surname, String username, String email, String password, boolean administrator){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO USERS (name, surname, username, email, password, administrator) VALUES ( " +name+ ", " +surname+ ", " +username+ ", " +email+ ", " +password+ ", " +administrator+ ")";
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

    public void displayAccountName(String username){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT username FROM USERS WHERE USERNAME = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("username"));
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayAccount(String username){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT name, surname, username, email FROM USERS WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            System.out.println(rs.getString("name"));
            System.out.println(rs.getString("surname"));
            System.out.println(rs.getString("username"));
            System.out.println(rs.getString("email"));
            checkIfAdmin(username);
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void checkIfAdmin(String username){
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT administrator FROM USERS WHERE USERNAME = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            if(rs.getBoolean("administrator") == true) System.out.println("Administrator");
            else System.out.println("No administrator rights");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteAccount(String username) {
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM USERS WHERE USERNAME = '" + username + "'";
            stmt.executeUpdate(sql);
            System.out.println("User deleted");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean ifAdminExists(){
        connectToDatabase();
        boolean adminExists = false;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT administrator FROM USERS ";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getBoolean("administrator") == true){
                    adminExists = true;
                    break;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adminExists;
    }

    public String checkEmail(String checkedEmail){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT email FROM USERS WHERE email = '" + checkedEmail + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString("email");

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

    public String checkUsername(String checkedUsername){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT username FROM USERS WHERE username = '" + checkedUsername + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString("username");

        } catch (SQLException e) {
            return e.getMessage();
        }
    }

}
