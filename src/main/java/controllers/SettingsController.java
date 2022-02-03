package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import appSettings.MultiLanguageStringGetter;
import appSettings.AppSettings;
import javafx.scene.control.Button;
import javafx.collections.*;


public class SettingsController {

    @FXML
    private CheckBox darkModeCheckBox;

    @FXML
    private ChoiceBox<String> languageChoiceBox;

    @FXML
    private Button applayButton;


    public void initialize() throws Exception{

        //String st[] = { MultiLanguageStringGetter.getString("English"), MultiLanguageStringGetter.getString("Polski") };
        darkModeCheckBox.setText(MultiLanguageStringGetter.getString("DarkMode"));
        languageChoiceBox.getItems().add(MultiLanguageStringGetter.getString("English"));
        languageChoiceBox.getItems().add(MultiLanguageStringGetter.getString("Polski"));
        applayButton.setText(MultiLanguageStringGetter.getString("Apply"));

        applayButton.setOnAction(event ->{
            if(darkModeCheckBox.isSelected())
                AppSettings.setNightModeToNightMode();
            else
                AppSettings.setNightModeToLightMode();
            AppSettings.setLanguage(languageChoiceBox.getAccessibleText());

        });
    }

}
