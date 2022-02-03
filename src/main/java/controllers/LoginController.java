package controllers;

import Database.DatabaseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import main.MainNoosphere;

public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField loginField;
    @FXML
    private ImageView logo;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
    @FXML
    private Button resetButton;
    @FXML
    private Label loginErrorLabel;

    public void initialize() throws Exception {
        loginErrorLabel.setVisible(false);
    }

    //funkcja wyłączająca komunikat niepowodzenia logowania, gdy ponownie zaczniemy wprowadzać dane
    @FXML
    void resetErrorLabel(KeyEvent event)
    {
        if(loginErrorLabel.isVisible())
            loginErrorLabel.setVisible(false);
    }

    @FXML
    void loginButtonPressed(ActionEvent event) throws Exception
    {
        String login = loginField.getText();
        String password = passwordField.getText();

        boolean isLogged = false;

        //sprawdza poprawność loginu i hasła, jeśli są zgodne z tymi w bazie danych, ustawia wartość isLogged na true
        DatabaseManager dbAccount = new DatabaseManager();
        if(dbAccount.usernameChecked(login) && dbAccount.passwordChecked(password)) {
            isLogged = true;
        }

        if(!isLogged || login.isEmpty() || password.isEmpty())
            loginErrorLabel.setVisible(true);
        else
            MainNoosphere.openNewWindow(this, "mainAppWindow", event);
    }

    @FXML
    void registerButtonPressed(ActionEvent event) throws Exception
    {
        MainNoosphere.openNewWindow(this, "registrationScreen", event);
    }

    @FXML
    void resetButtonPressed(ActionEvent event)
    {
        loginField.clear();
        passwordField.clear();
    }
}

