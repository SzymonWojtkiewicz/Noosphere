package main;

import Database.DatabaseManager;
import appSettings.AppSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import appSettings.MultiLanguageStringGetter;


public class MainNoosphere extends Application {
    public static void main(String[] args) throws  Exception{
        AppSettings appSettings = new AppSettings();
        //MultiLanguageStringGetter a = new MultiLanguageStringGetter();
        //System.out.println(MultiLanguageStringGetter.getString("Registration")); ,
        launch();
    }
    @Override
    public void start(Stage stage) throws  Exception{

        //AnchorPane mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mediaPlayer.fxml")); //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        AnchorPane mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/registrationScreen.fxml"));
        Scene scene = new Scene(mainAppWindow);
        stage.setScene(scene);
        stage.show();

        //DatabaseManager dbVideos = new DatabaseManager();
        /*
        //dbVideos.connectToDatabase();
        //dbVideos.createUsersTable();
        //dbVideos.createVideosTable();
        //dbVideos.createAccount("'tak'", "'nie'", "'halo'", false);
        //dbVideos.upgradeToAdmin("'tak'");
        //dbVideos.displayAccountName("tak");
        //dbVideos.displayAccount("tak");
        //dbVideos.deleteAccount("tak");
        //dbVideos.inputVideo("'Walka'", "'nieznany'", "'akcja'", "'nieznany'", "'https://drive.google.com/file/d/1tJc8JTCd9mdlChpFwgaEFFso8un_DjYA/view?fbclid=IwAR1_Z6D6RfH0BMob6x8-Hr_DcMj0vZGmH877xqhvs_3Gm-e6jea1h8X0HNU'");
        //dbVideos.displayInformationVideo("Walka", "nieznany");
        //System.out.println(dbVideos.displaySourceVideo("Walka", "nieznany"));
        //System.out.println(dbVideos.ifAdminExists());

         */

    }
}
