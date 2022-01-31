package Database;

import java.sql.*;

public class DatabaseManager implements Database{
    Connection conn = null;


    //connecting to database
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


    //creating users table in database
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


    //creating videos table in database
    public void createVideosTable(){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE VIDEOS (id INTEGER not NULL AUTO_INCREMENT, title VARCHAR(255), director VARCHAR(255), " +
                    " photo VARCHAR(255), source VARCHAR(255), PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Created table");
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
            System.out.println("Inputted video successfully");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * displaying information of video
     * @param title searched title of the video
     * @param director searched director of the video
     */
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


    /**
     * creating account
     * @param name name of user
     * @param surname surname of user
     * @param username username that user wants to set
     * @param email user's email
     * @param password user's password
     * @param administrator creating normal(false) or admin account(true)
     */
    public void createAccount(String name, String surname, String username, String email, String password, boolean administrator){
        connectToDatabase();

        try {
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO USERS (name, surname, username, email, password, administrator) VALUES ( '" +name+ "', '" +surname+ "', '" +username+ "', '" +email+ "', '" +password+ "', " +administrator+ ")";
            stmt.executeUpdate(sql);
            System.out.println("Created user");
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    /**
     * if we want to upgrade normal user to admin permissions
     * @param username username of the account we want to upgrade
     */
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


    /**
     * if we want to downgrade admin to user permissions
     * @param username username of the account we want to downgrade
     */
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


    /**
     * Is this method even necessary?
     *
     * displaying account name
     * @param username username of the account we want to display
     */
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


    /**
     * displaying account name, surname, username, email
     * @param username username of the user we want to display
     */
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


    /**
     * chcecking if the account has admin permissions
     * @param username username of account we want to check
     */
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


    /**
     * deleting account method
     * @param username username of account we want to delete
     */
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


    /**
     * checking if admin account exists
     * @return returns if it exists (true) or not (false)
     */
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


    /**
     * submethod for checking if the email already is in database
     * @param checkedEmail email we want to check
     * @return returns email if it is in database
     */
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


    /**
     * submethod for checking if the username already is in database
     * @param checkedUsername username we want to check
     * @return returns username if it is in database
     */
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


    /**
     * method for checking if the email already exists
     * @param email email we want to check
     * @return returns true if email exists
     */
    public boolean emailChecked(String email){
        return checkEmail(email).equals(email);
    }


    /**
     * method for checking if the username already exists
     * @param username username we want to check
     * @return returns true if username exists
     */
    public boolean usernameChecked(String username){
        return checkUsername(username).equals(username);
    }
}
