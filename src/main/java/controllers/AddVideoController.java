package controllers;

import appSettings.MultiLanguageStringGetter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AddVideoController {

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

        nameLabel.setText(MultiLanguageStringGetter.getString(" "));
        nameTextField.setText(MultiLanguageStringGetter.getString(" "));
        usernameInfoLabel1.setText(MultiLanguageStringGetter.getString(" "));
        directorLabel.setText(MultiLanguageStringGetter.getString(" "));
        directorTextField.setText(MultiLanguageStringGetter.getString(" "));
        directorInfoLabel.setText(MultiLanguageStringGetter.getString(" "));
        pictureLabel.setText(MultiLanguageStringGetter.getString(" "));
        pictureTextField.setText(MultiLanguageStringGetter.getString(" "));
        pictureInfoLabel.setText(MultiLanguageStringGetter.getString(" "));
        videoLabel.setText(MultiLanguageStringGetter.getString(" "));
        videoTextField.setText(MultiLanguageStringGetter.getString(" "));
        videoInfoLabel.setText(MultiLanguageStringGetter.getString(" "));
        clearButton.setText(MultiLanguageStringGetter.getString(" "));
        addAccountButton.setText(MultiLanguageStringGetter.getString(" "));

    }

}
