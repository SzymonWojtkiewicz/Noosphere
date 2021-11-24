package Database;

import java.sql.*;

public class DatabaseManager {
    Connection conn = null;
    ResultSet result = null;
    String dbName = "NOOSPHERE";
    public void connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/?" + "user=root&password=");

            if(conn != null){
                System.out.println("Connected");
            }
/*
            String query = "select * from gatunki";
            var statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String nazwa = result.getString("nazwa");
                System.out.println(id + nazwa);
            }
*/
        } catch (Exception ex){
            System.out.println("Unable to connect");
            System.out.println("SQLException: " + ex.getMessage());
        }

    }
    public void createDatabase(){
        if(conn == null){
            connectToDatabase();
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

    }
    public void createVideosTable(){

    }
    public void createUser(){

    }

}
