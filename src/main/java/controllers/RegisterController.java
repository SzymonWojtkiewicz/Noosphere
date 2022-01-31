package controllers;

import Database.DatabaseManager;
import appSettings.AppSettings;
import appSettings.MultiLanguageStringGetter;
import colorSchemes.ColorPallets;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class RegisterController extends DatabaseManager
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
    private Label registerLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label surnameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label repeatPasswordLabel;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;

    public void initialize() throws Exception
    {
        usernameInfoLabel.setVisible(false);
        emailInfoLabel.setVisible(false);
        createAccountErrorLabel.setVisible(false);

        registerLabel.setText(MultiLanguageStringGetter.getString("Registration"));
        nameLabel.setText(MultiLanguageStringGetter.getString("FirstName"));
        surnameLabel.setText(MultiLanguageStringGetter.getString("LastName"));
        usernameLabel.setText(MultiLanguageStringGetter.getString("UserName"));
        emailLabel.setText(MultiLanguageStringGetter.getString("EmailAddress"));
        passwordLabel.setText(MultiLanguageStringGetter.getString("Password"));
        repeatPasswordLabel.setText(MultiLanguageStringGetter.getString("RepeatPassword"));
        createAccountButton.setText(MultiLanguageStringGetter.getString("CreateAnAccount"));
        cancelButton.setText(MultiLanguageStringGetter.getString("Cancel"));
        passwordInfoLengthLabel.setText(MultiLanguageStringGetter.getString("Minimum8Characters"));
        passwordInfoDigitLabel.setText(MultiLanguageStringGetter.getString("MinimumOneDigit"));
        passwordInfoLetterLabel.setText(MultiLanguageStringGetter.getString("MinimumOneCapitalLetter"));


    }

    @FXML

    public void CreateAccount(ActionEvent actionEvent) throws Exception
    {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String username = usernameTextField.getText();
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        String passwordConfirm = passwordConfirmTextField.getText();

        createAccountErrorLabel.setVisible(true);
        createAccountErrorLabel.setTextFill(Color.web(AppSettings.errorColor())); //ustaw domyslnie na czerwony
        //sprawdz czy wszystkie pola zostaly uzupelnione
        if(name.isEmpty() || surname.isEmpty() || username.isEmpty() ||
                email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty())
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("NotAllFieldsHaveBeenCompleted!"));
        else if(!isUsernameValid)
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("GivenUserNameIsInvalid!!"));
        else if(usernameChecked(username))
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("GivenUserNameIsUnavailable!"));
        else if(!isEmailValid)
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("GivenEmailAddressIsInvalid!"));
        else if(emailChecked(email))
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("GivenEmailAddressIsUnavailable!"));
        else if(!isPasswordValid)
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("ThePasswordYouEnterDoesNotMeetTheSecurityRequirements!"));
        else if(!password.equals(passwordConfirm))
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("PasswordsDoNotMatch!"));
        else
        {
            createAccountErrorLabel.setTextFill(Color.web(AppSettings.successColor()));
            createAccountErrorLabel.setText(MultiLanguageStringGetter.getString("AccountCreatedSuccessfully!"));

            createAccount(name, surname, username, email, password, false);
        }
    }

    @FXML
    public void CheckUsername(KeyEvent keyEvent)
    {
        String username = usernameTextField.getText();


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
    public void CheckEmail(KeyEvent keyEvent) throws Exception
    {
        String email = emailTextField.getText();

        //Regex wziety z neta, wzor jaka budowe ma email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        boolean isTaken = false;

        //Sprawdzamy czy podany email ma budowe emaila
        if(!email.matches(emailRegex))
        {
            emailInfoLabel.setVisible(true);
            emailInfoLabel.setText(MultiLanguageStringGetter.getString("IncorrectFormOfEmailAddress!"));
            isEmailValid = false;
        }
        else if(isTaken) //sprawdzamy czy email jest zajety
        {
            emailInfoLabel.setVisible(true);
            emailInfoLabel.setText(MultiLanguageStringGetter.getString("GivenEmailAddressIsTaken!"));
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
        Color errorColor = Color.web(AppSettings.errorColor());
        Color successColor = Color.web(AppSettings.successColor());

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