package controllers;

import colorSchemes.ColorPallets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

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
    private Label passwordInfoLengthLabel;
    @FXML
    private Label passwordInfoDigitLabel;
    @FXML
    private Label passwordInfoLetterLabel;
    @FXML
    private Label createAccountErrorLabel;

    private boolean isUsernameValid = false;
    private boolean isEmailValid = false;
    private boolean isPasswordValid = false;

    @FXML
    public void CreateAccount(ActionEvent actionEvent)
    {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String passwordConfirm = passwordConfirmTextField.getText();

        //sprawdz czy wszystkie pola zostaly uzupelnione
        if(name.isEmpty() || surname.isEmpty() || username.isEmpty() ||
                email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty())
            createAccountErrorLabel.setText("Prosimy o wypelnienie wszystkich pol!");
        else if(!isUsernameValid)
            createAccountErrorLabel.setText("Podana nazwa uzytkownika jest niepoprawna!");
        else if(!isEmailValid)
            createAccountErrorLabel.setText("Podany adres email jest niepoprawny!");
        else if(!isPasswordValid)
            createAccountErrorLabel.setText("Podane haslo nie spelnia wymagan bezpieczenstwa!");
        else if(!password.equals(passwordConfirm))
            createAccountErrorLabel.setText("Hasla sie nie zgadzaja!");
        else
        {
            createAccountErrorLabel.setText("Pomyslnie utworzono konto!");
            //TODO: Przeslanie danych do bazy danych
        }
    }

    @FXML
    public void CheckUsername(KeyEvent keyEvent)
    {
        String username = usernameTextField.getText();

        //TODO: Sprawdzenie czy podana nazwa uzytkownika nie jest zajeta (zmienna `isTaken`)
        boolean isTaken = false;

        if(isTaken) //sprawdz czy nazwa uzytkownika jest zajeta
        {
            usernameInfoLabel.setVisible(true);
            isUsernameValid = false;
        }
        else
        {
            usernameInfoLabel.setVisible(false);
            isUsernameValid = true;
        }
    }

    @FXML
    public void CheckEmail(KeyEvent keyEvent)
    {
        String email = emailTextField.getText();

        //Regex wziety z neta, wzor jaka budowe ma email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        //TODO: Sprawdzenie czy email juz istnieje w bazie danych (zmienna `isTaken`)
        boolean isTaken = false;

        //Sprawdzamy czy podany email ma budowe emaila
        if(!email.matches(emailRegex))
        {
            emailInfoLabel.setVisible(true);
            emailInfoLabel.setText("Niepoprawna forma adresu email!");
            isEmailValid = false;
        }
        else if(isTaken) //sprawdzamy czy email jest zajety
        {
            emailInfoLabel.setVisible(true);
            emailInfoLabel.setText("Podany adres email jest zajety!");
            isEmailValid = false;
        }
        else
        {
            emailInfoLabel.setVisible(false);
            isEmailValid = true;
        }
    }

    @FXML
    public void CheckPassword(KeyEvent keyEvent)
    {
        String password = passwordTextField.getText();

        //pobieramy kolory z naszej klasy
        ColorPallets colorPallets = new ColorPallets();
        Color errorColor = Color.web(colorPallets.GetColor(colorPallets.errorColor));
        Color successColor = Color.web(colorPallets.GetColor(colorPallets.successColor));

        // I - sprawdz dlugosc hasla
        if(password.length() < 8)
            passwordInfoLengthLabel.setTextFill(errorColor);
        else
            passwordInfoLengthLabel.setTextFill(successColor);

        // II - sprawdz czy haslo zawiera minimum 1 cyfre
        // III - sprawdz czy haslo zawiera minimum 1 duza litere
        char[] passwordChars = password.toCharArray();
        boolean occursDigit = false;
        boolean occursUpperLetter = false;
        for(char c : passwordChars)
        {
            if(Character.isDigit(c)) //czy cyfra
                occursDigit = true;
            if(Character.isUpperCase(c)) //czy duza litera
                occursUpperLetter = true;
        }

        if(!occursDigit)
            passwordInfoDigitLabel.setTextFill(errorColor);
        else
            passwordInfoDigitLabel.setTextFill(successColor);

        if(!occursUpperLetter)
            passwordInfoLetterLabel.setTextFill(errorColor);
        else
            passwordInfoLetterLabel.setTextFill(successColor);

        if(password.length() >= 8 && occursDigit && occursUpperLetter)
            isPasswordValid = true;
        else
            isPasswordValid = false;
    }
}
