package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class RegisterController
{
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private PasswordField passwordConfirmTextField;

    @FXML
    private Label usernameInfoLabel;
    @FXML
    private Label emailInfoLabel;
    @FXML
    private Label passwordInfoLabel;
    @FXML
    private Label passwordConfirmInfoLabel;

    @FXML
    public void CreateAccount(ActionEvent actionEvent)
    {
        System.out.println(surnameTextField.getText());
    }

    @FXML
    public void CheckUsername(ActionEvent actionEvent)
    {
        //Porownanie nazwy uzytkownika z baza danych
    }

    @FXML
    public void CheckEmail(ActionEvent actionEvent)
    {
        //Sprawdzenie czy email juz istnieje w bazie danych
    }

    @FXML
    public void CheckPassword(KeyEvent keyEvent)
    {
        /*
        String password = passwordTextField.getText();
        String message = "";

        if(password.length() < 8)
            message += "Minimum 8 znaków\n";

        passwordInfoLabel.setText(message);
        */

        //Sprawdzenie kryteriow bezpiecznego hasla
        // - minimum 8 znakow
        // - minimum 1 cyfra
        // - minimum 1 duza litera
    }

    @FXML
    public void CheckPasswordConfirm(ActionEvent actionEvent)
    {
        //Porownanie hasla podanego w tym polu z polem "Haslo"
    }
    /*
        ^
        |
        there should be only declarations
     */
    public void initialize(){

    }

    //here you will put eventHandler like
    /*
    mainAnchorPane.addEventFilter(KeyEvent.KEY_PRESSED, KeyEvent ->{

            switch (KeyEvent.getCode()){
                case UP:
                    if(newGame.move("up"))
                        newGame.addNumber();
                    display(newGame.getGameArray());
                    scoreLabel.setText((Integer.toString((newGame.getScore()))));
                    break;
                case DOWN:
                    if(newGame.move("down"))
                        newGame.addNumber();
                    display(newGame.getGameArray());
                    scoreLabel.setText((Integer.toString((newGame.getScore()))));
                    break;
                case LEFT:
                    if(newGame.move("left"))
                        newGame.addNumber();
                    display(newGame.getGameArray());
                    scoreLabel.setText((Integer.toString((newGame.getScore()))));
                    break;
                case RIGHT:
                    if(newGame.move("right"))
                        newGame.addNumber();
                    display(newGame.getGameArray());
                    scoreLabel.setText((Integer.toString((newGame.getScore()))));
                    break;
            }
        });

    }
     */
}
