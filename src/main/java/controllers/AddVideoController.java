package controllers;

import Database.DatabaseVideos;
import appSettings.AppSettings;
import appSettings.MultiLanguageStringGetter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.MainNoosphere;
import java.io.IOException;
import java.io.UncheckedIOException;

public class AddVideoController extends DatabaseVideos
{

    @FXML
    private Button addAccountButton;

    @FXML
    private Button clearButton;

    @FXML
    private Label directorInfoLabel;

    @FXML
    private Label directorLabel;

    @FXML
    private TextField directorTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label pictureInfoLabel;

    @FXML
    private Label pictureLabel;

    @FXML
    private TextField pictureTextField;

    @FXML
    private Label uploadErrorLabel;

    @FXML
    private Label usernameInfoLabel1;

    @FXML
    private Label videoInfoLabel;

    @FXML
    private Label videoLabel;

    @FXML
    private PasswordField videoTextField;

    public void initialize() throws Exception
    {

        usernameInfoLabel1.setVisible(false);
        directorInfoLabel.setVisible(false);
        pictureInfoLabel.setVisible(false);
        videoInfoLabel.setVisible(false);
        uploadErrorLabel.setVisible(false);

        nameLabel.setText(MultiLanguageStringGetter.getString("Title"));
        //nameTextField.setText(MultiLanguageStringGetter.getString(" "));
        usernameInfoLabel1.setText(MultiLanguageStringGetter.getString("MissingTitle"));
        directorLabel.setText(MultiLanguageStringGetter.getString("Director"));
       //directorTextField.setText(MultiLanguageStringGetter.getString(" "));
        directorInfoLabel.setText(MultiLanguageStringGetter.getString("MissingDirector"));
        pictureLabel.setText(MultiLanguageStringGetter.getString("TitleScreenSource"));
        //pictureTextField.setText(MultiLanguageStringGetter.getString(" "));
        pictureInfoLabel.setText(MultiLanguageStringGetter.getString("MissingTitleScreenSource"));
        videoLabel.setText(MultiLanguageStringGetter.getString("VideoSource"));
        //videoTextField.setText(MultiLanguageStringGetter.getString(" "));
        videoInfoLabel.setText(MultiLanguageStringGetter.getString("MissingVideoSource"));
        uploadErrorLabel.setText(MultiLanguageStringGetter.getString("UploadFailed"));
        clearButton.setText(MultiLanguageStringGetter.getString("Clear"));
        addAccountButton.setText(MultiLanguageStringGetter.getString("Add"));

        clearButton.setOnAction(event ->{
            usernameInfoLabel1.setVisible(false);
            directorInfoLabel.setVisible(false);
            pictureInfoLabel.setVisible(false);
            videoInfoLabel.setVisible(false);
            uploadErrorLabel.setVisible(false);

            nameTextField.clear();
            directorTextField.clear();
            pictureTextField.clear();
            videoTextField.clear();

        });

        addAccountButton.setOnAction(event ->{
            usernameInfoLabel1.setVisible(false);
            directorInfoLabel.setVisible(false);
            pictureInfoLabel.setVisible(false);
            videoInfoLabel.setVisible(false);
            uploadErrorLabel.setVisible(false);

            String tilte = nameTextField.getText();
            String director = directorTextField.getText();
            String tilteScreenSource = pictureTextField.getText();
            String videoSource = videoTextField.getText();



            //sprawdz czy wszystkie pola zostaly uzupelnione
            if(tilte.isEmpty() || director.isEmpty() || tilteScreenSource.isEmpty() ||
                    videoSource.isEmpty())
                uploadErrorLabel.setVisible(true);
            else
            {
                uploadErrorLabel.setTextFill(Color.web(AppSettings.successColor()));

                try {
                    uploadErrorLabel.setText(MultiLanguageStringGetter.getString("UploadSuccessful"));
                    throw new Exception("as");
                }
                catch (final Exception e){

                }
                inputVideo(tilte, director, tilteScreenSource, videoSource);

            }

        });

    }

}
