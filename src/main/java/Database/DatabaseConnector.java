package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnector {
    Connection conn = null;
    String dbName = "bigflex";

    public void connectToSqlAndCreateDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/?";
            conn = DriverManager.getConnection(url, "root", "");

            Statement stmt = conn.createStatement();
            String sql = "CREATE DATABASE " +dbName+ "";
            stmt.executeUpdate(sql);

            if(conn != null){
                //System.out.println("Created database");
            }

        } catch (Exception ex){
            System.out.println("Unable to connect to database");
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    //connecting to database
    public void connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            //connecting to local database
            //conn = DriverManager.getConnection("jdbc:mysql://localhost/" +dbName+ "?user=root&password=");
            String url = "jdbc:mysql://localhost/" +dbName;
            conn = DriverManager.getConnection(url, "root", "");

            //connecting to remote database
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            //String url = "jdbc:mysql://192.166.219.220/bigflex";
            //conn = DriverManager.getConnection(url, "bigflex", "f5IyhvPF..");

            if(conn != null){
                //System.out.println("Connected to database");
            }

        } catch (Exception ex){
            System.out.println("Unable to connect to database");
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
