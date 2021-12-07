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
        //System.out.println(MultiLanguageStringGetter.getString("Registration"));
        launch();
    }
    @Override
    public void start(Stage stage) throws  Exception{

        AnchorPane mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mediaPlayer.fxml")); //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        Scene scene = new Scene(mainAppWindow);
        stage.setScene(scene);
        stage.show();

        /*
        DatabaseManager dbVideos = new DatabaseManager();
        dbVideos.createDatabase();
        dbVideos.createUsersTable();
        dbVideos.createVideosTable();
        dbVideos.createUser("'tak'", "'nie'", "'halo'", false);
        dbVideos.upgradeToAdmin("'tak'");
         */
    }
}
