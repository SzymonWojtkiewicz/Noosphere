package main;

import Database.DatabaseManager;
import appSettings.AppSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MainNoosphere extends Application {
    public static void main(String[] args){
        AppSettings appSettings = new AppSettings();
        launch();
    }
    @Override
    public void start(Stage stage) throws  Exception{

        HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        Scene scene = new Scene(mainAppWindow);
        stage.setScene(scene);
        stage.show();
        DatabaseManager dbVideos = new DatabaseManager();
        dbVideos.connectToDatabase();
    }
}
