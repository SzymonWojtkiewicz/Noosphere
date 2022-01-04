package Database;

import java.sql.SQLException;
import java.sql.Statement;

public interface Database {
    public void connectToMysql();
    public void connectToDatabase();
    public void createDatabase();
    public void createUsersTable();
    public void createVideosTable();
    public void inputVideo(String title, String director, String genre, String language, String source);
    public void displayInformationVideo(String title, String director);
    public void displaySourceVideo(String title, String director);
    public void createAccount(String username, String email, String password, boolean administrator);
    public void upgradeToAdmin(String username);
    public void downgradeToUser(String username);
    public void displayAccountName(String username);
    public void displayAccount(String username);
    public void checkIfAdmin(String username);
    public void deleteAccount(String username);
}
