package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainAppController
{
    //te dwie zmienne są tylko do testów
    private final static String registrationScreen = "registrationScreen";
    private final static String loginScreen = "loginScreen";

    private final static String homeScreen = "homeScreen";
    private final static String myVideosScreen = "";
    private final static String myAccountScreen = "mainAppUserSettings";
    private final static String addVideoScreen = "mainAppMediaTransfer";
    private final static String playerScreen = "mediaPlayer";
    private final static String helpScreen = "";
    private final static String settingsScreen = "";

    @FXML
    private GridPane videosGridPane;

    @FXML
    private Button homeButton;
    @FXML
    private Button myVideosButton;
    @FXML
    private Button myAccountButton;
    @FXML
    private Button addVideoButton;
    @FXML
    private Button playerButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button settingsButton;

    @FXML
    private TextField searchTextField;
    @FXML
    private Button searchButton;

    //funkcja wyświetlająca "pod-okno", dla przycisków/zakładek
    @FXML
    public void openSubWindow(ActionEvent event) throws Exception
    {
        String windowName = "";
        //pobieramy ID przycisku
        String buttonName = ((Button) event.getTarget()).getId();

        //na podstawie ID przycisku, wiemy jakie pod-okno wyświetlić
        switch (buttonName)
        {
            case "homeButton":
                windowName = homeScreen;
                showSearchField(true);
                break;
            case "myVideosButton":
                windowName = loginScreen;
                showSearchField(true);
                break;
            case "myAccountButton":
                windowName = registrationScreen;
                showSearchField(false);
                break;
            case "addVideoButton":
                windowName = addVideoScreen;
                showSearchField(false);
                break;
            case "playerButton":
                windowName = playerScreen;
                showSearchField(false);
                break;
            case "helpButton":
                windowName = helpScreen;
                showSearchField(false);
                break;
            case "settingsButton":
                windowName = settingsScreen;
                showSearchField(false);
                break;
        }

        Pane loginWindow = FXMLLoader.load(getClass().getResource("/view/fxml/" + windowName + ".fxml"));
        videosGridPane.getChildren().setAll(loginWindow);
    }

    private void showSearchField(boolean state)
    {
        searchTextField.setVisible(state);
        searchButton.setVisible(state);
    }
}
