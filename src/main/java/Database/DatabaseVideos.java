package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseVideos extends DatabaseConnector {

    //creating videos table in database
    public void createVideosTable(){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE VIDEOS (id INTEGER not NULL AUTO_INCREMENT, title VARCHAR(255), director VARCHAR(255), " +
                    " photo VARCHAR(255), source VARCHAR(255), PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            //System.out.println("Created table");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * inputting video into videos table in database
     * @param title title of the video
     * @param director video director
     * @param photo video image
     * @param source video source
     */
    public void inputVideo(String title, String director, String photo, String source){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO VIDEOS (title, director, photo, source) VALUES ( '" +title+ "', '" +director+ "', '" +photo+ "', '" +source+ "')";
            stmt.executeUpdate(sql);
            //System.out.println("Inputted video successfully");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * displaying source video
     * @param title searched title of the video source
     * @param director searched director of video source
     * @return returns string source of video
     */
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


    /**
     * displaying video image source
     * @param title searched title of the video image
     * @param director searched director of video image
     * @return returns string source of video image
     */
    public String displayImageVideo(String title, String director){
        connectToDatabase();
        String image = null;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT photo FROM VIDEOS WHERE title = '" + title + "' AND director = '" + director + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            image = rs.getString("photo");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return image;
    }
}
