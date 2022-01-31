package Database;

public interface Database {
    public void connectToDatabase();
    public void createUsersTable();
    public void createVideosTable();
    public void inputVideo(String title, String director, String photo, String source);
    public void displayInformationVideo(String title, String director);
    public String displaySourceVideo(String title, String director);
    public void createAccount(String name, String surname, String username, String email, String password, boolean administrator);
    public void upgradeToAdmin(String username);
    public void downgradeToUser(String username);
    public void displayAccountName(String username);
    public void displayAccount(String username);
    public void checkIfAdmin(String username);
    public void deleteAccount(String username);
    public boolean ifAdminExists();
}
