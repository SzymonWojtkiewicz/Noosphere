package main;

import Database.DatabaseConnector;
import Database.DatabaseVideos;
import Database.DatabaseUsers;
import appSettings.AppSettings;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import appSettings.MultiLanguageStringGetter;

import java.io.IOException;


public class MainNoosphere extends Application {
    public static void main(String[] args) throws  Exception{
        AppSettings appSettings = new AppSettings();
        //MultiLanguageStringGetter a = new MultiLanguageStringGetter();
        //System.out.println(MultiLanguageStringGetter.getString("Registration")); ,
        launch();
    }
    @Override
    public void start(Stage stage) throws  Exception{

        AnchorPane loginWindow = FXMLLoader.load(getClass().getResource("/view/fxml/loginScreen.fxml"));
        //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        Scene scene = new Scene(loginWindow);//mainAppWindow
        //AnchorPane mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mediaPlayer.fxml")); //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        //HBox mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/mainAppWindow.fxml"));
        //AnchorPane mainAppWindow = FXMLLoader.load(getClass().getResource("/view/fxml/registrationScreen.fxml"));
        //Scene scene = new Scene(mainAppWindow);
        stage.setScene(scene);
        stage.show();

        /*
        DatabaseConnector dbVideos = new DatabaseConnector();
        dbVideos.connectToSqlAndCreateDB();
        dbVideos.connectToDatabase();

        dbVideos = new DatabaseUsers();
        ((DatabaseUsers) dbVideos).createUsersTable();
        ((DatabaseUsers) dbVideos).createAccount("Jan", "Pawel", "Papiez", "JeszczeJak123@tak.tak", "nie", false);
        ((DatabaseUsers) dbVideos).upgradeToAdmin("tak");
        ((DatabaseUsers) dbVideos).deleteAccount("tak");
        System.out.println(((DatabaseUsers) dbVideos).ifAdminExists());

        dbVideos = new DatabaseVideos();
        ((DatabaseVideos) dbVideos).createVideosTable();
        ((DatabaseVideos) dbVideos).inputVideo("Walka", "nieznany", "JakisLink", "https://drive.google.com/file/d/1tJc8JTCd9mdlChpFwgaEFFso8un_DjYA/view?fbclid=IwAR1_Z6D6RfH0BMob6x8-Hr_DcMj0vZGmH877xqhvs_3Gm-e6jea1h8X0HNU");
        System.out.println(((DatabaseVideos) dbVideos).displaySourceVideo("Walka", "nieznany"));
        */



    }

    public static void openNewWindow(Object fromClass, String windowToOpen, ActionEvent event)
    {
        Pane mainAppWindow = null;
        try
        {
            mainAppWindow = FXMLLoader.load(fromClass.getClass().getResource("/view/fxml/" + windowToOpen + ".fxml"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Scene scene = new Scene(mainAppWindow);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }



}
