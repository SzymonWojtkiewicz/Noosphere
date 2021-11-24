package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseManager {
    Connection conn = null;
    public void connectToDatabase(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dane?" + "user=root&password=");

            if(conn != null){
                System.out.println("Connected");
            }

            String query = "select * from gatunki";
            var statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("id");
                String nazwa = result.getString("nazwa");
                System.out.println(id + nazwa);
            }

        } catch (Exception ex){
            System.out.println("Unable to connect");
            System.out.println("SQLException: " + ex.getMessage());
        }

    }
}
