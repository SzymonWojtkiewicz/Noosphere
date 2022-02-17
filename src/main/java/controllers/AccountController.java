package controllers;

import Database.DatabaseUsers;
import appSettings.AppSettings;
import appSettings.MultiLanguageStringGetter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class AccountController
{
    @FXML
    private Label mainLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label accountStatusLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label accountStatusText;
    @FXML
    private Button changeEmailButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button mainSaveButton;


    @FXML
    private HBox changeHBox;
    @FXML
    private Separator changeSeparator;

    @FXML
    private Label changeLabel;
    @FXML
    private Label changeNewLabel;
    @FXML
    private Label repeatNewPasswordLabel;
    @FXML
    private Label oldPasswordLabel;
    @FXML
    private Label changeErrorLabel;
    @FXML
    private TextField changeNewEmail;
    @FXML
    private PasswordField changeNewPassword;
    @FXML
    private PasswordField repeatNewPasswordField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private Button changeSaveButton;
    @FXML
    private Button changeCancelButton;

    private String userPassword;
    private boolean isChangingPassword = false;
    private boolean isChangingEmail = false;

    public void initialize() throws Exception
    {
        mainLabel.setText(MultiLanguageStringGetter.getString("MyAccountTitle"));
        usernameLabel.setText(MultiLanguageStringGetter.getString("UserName"));
        firstNameLabel.setText(MultiLanguageStringGetter.getString("FirstName"));
        lastNameLabel.setText(MultiLanguageStringGetter.getString("LastName"));
        emailLabel.setText(MultiLanguageStringGetter.getString("EmailAddress"));
        passwordLabel.setText(MultiLanguageStringGetter.getString("Password"));
        accountStatusLabel.setText(MultiLanguageStringGetter.getString("AccountStatus"));
        changeEmailButton.setText(MultiLanguageStringGetter.getString("ChangeEmail"));
        changePasswordButton.setText(MultiLanguageStringGetter.getString("ChangePassword"));
        repeatNewPasswordLabel.setText(MultiLanguageStringGetter.getString("RepeatNewPassword"));
        oldPasswordLabel.setText(MultiLanguageStringGetter.getString("OldPassword"));
        mainSaveButton.setText(MultiLanguageStringGetter.getString("Save"));
        changeSaveButton.setText(MultiLanguageStringGetter.getString("Save"));
        changeCancelButton.setText(MultiLanguageStringGetter.getString("Cancel"));

        //to pozwala na ustawienie widoczności danego elementu
        //teraz jeśli przykładowo wywołamy "changeNewPassword.setVisible(false)" to zostanie on całkowicie ukryty,
        //i taki element nie będzie wykrywany przez HBox (nie będzie zajmował miejsca)
        changeNewPassword.managedProperty().bind(changeNewPassword.visibleProperty());
        changeNewEmail.managedProperty().bind(changeNewEmail.visibleProperty());

        //TODO: Na podstawie informacji jaki użytkownik jest aktualnie zalogowany, to jego dane pobieramy
        String username = "loggedLogin";
        String firstName = "Imię";
        String lastName = "Nazwisko";
        String email = "example@gmail.com";
        String password = "haslo";
        boolean isAdmin = false;

        usernameField.setText(username);
        firstNameField.setText(firstName);
        lastNameField.setText(lastName);
        emailField.setText(email);
        userPassword = password;
        if(isAdmin)
            accountStatusText.setText(MultiLanguageStringGetter.getString("StatusAdmin"));
        else
            accountStatusText.setText(MultiLanguageStringGetter.getString("StatusUser"));



        SetChangePanel(false);
        errorLabel.setVisible(false);
        changeErrorLabel.setVisible(false);

        changePasswordButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try
                {
                    changeLabel.setText(MultiLanguageStringGetter.getString("ChangePassword"));
                    changeNewLabel.setText(MultiLanguageStringGetter.getString("NewPassword"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                changeNewEmail.setVisible(false);
                changeNewPassword.setVisible(true);
                repeatNewPasswordLabel.setVisible(true);
                repeatNewPasswordField.setVisible(true);
                oldPasswordLabel.setVisible(true);
                oldPasswordField.setVisible(true);
                isChangingPassword = true;
                isChangingEmail = false;
                SetChangePanel(true);

                changeNewPassword.setText("");
                repeatNewPasswordField.setText("");
                oldPasswordField.setText("");

                changeErrorLabel.setVisible(false);
            }
        });

        changeEmailButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                try
                {
                    changeLabel.setText(MultiLanguageStringGetter.getString("ChangeEmail"));
                    changeNewLabel.setText(MultiLanguageStringGetter.getString("NewEmail"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                changeNewPassword.setVisible(false);
                changeNewEmail.setVisible(true);
                repeatNewPasswordLabel.setVisible(false);
                repeatNewPasswordField.setVisible(false);
                oldPasswordLabel.setVisible(false);
                oldPasswordField.setVisible(false);
                isChangingEmail = true;
                isChangingPassword = false;
                SetChangePanel(true);

                changeNewEmail.setText("");

                changeErrorLabel.setVisible(false);
            }
        });

        mainSaveButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                SaveAccount();
            }
        });

        changeSaveButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if(isChangingEmail)
                    ChangeEmail();
                else if(isChangingPassword)
                    ChangePassword();
            }
        });

        changeCancelButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                changeNewPassword.setText("");
                changeNewEmail.setText("");
                repeatNewPasswordField.setText("");
                oldPasswordField.setText("");
                SetChangePanel(false);
            }
        });
    }

    private void SaveAccount()
    {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if(firstName.isEmpty() || lastName.isEmpty())
        {
            errorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                errorLabel.setText(MultiLanguageStringGetter.getString("NotAllFieldsHaveBeenCompleted!"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            errorLabel.setVisible(true);
            errorLabel.setTextFill(Color.web(AppSettings.successColor()));
            try
            {
                errorLabel.setText(MultiLanguageStringGetter.getString("SaveAccountChangesSuccess"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            //Zapisanie do bazy danych zmienionych imienia i nazwiska
            DatabaseUsers dbNames= new DatabaseUsers();
            dbNames.changingNameAndSurname(usernameField.getText(), firstName, lastName);
        }
    }

    private void ChangeEmail()
    {
        String newEmail = changeNewEmail.getText();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        //Sprawdzenie czy email, na który chcemy zmienić nie jest zajęty (zmienna `isTaken`)
        boolean isTaken = false;
        DatabaseUsers availableEmail = new DatabaseUsers();
        if(availableEmail.emailChecked(newEmail))
            isTaken = true;

        if(newEmail.isEmpty())
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("NotAllFieldsHaveBeenCompleted!"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(!newEmail.matches(emailRegex)) //Sprawdzamy czy podany email ma budowe emaila
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));
            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("IncorrectFormOfEmailAddress!"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(isTaken) //sprawdzamy czy email jest zajety
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));
            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("GivenEmailAddressIsTaken!"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.successColor()));
            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("ChangeEmailSuccess"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            emailField.setText(newEmail);

            //Zmiana w bazie danych emaila użytkownika na nowy podany email
            DatabaseUsers dbEmail = new DatabaseUsers();
            dbEmail.changingEmail(usernameField.getText(), newEmail);
        }
    }

    private void ChangePassword()
    {
        String newPassword = changeNewPassword.getText();
        String repeatNewPassword = repeatNewPasswordField.getText();
        String oldPassword = oldPasswordField.getText();


        //Sprawdzmy czy hasło ma 8 lub więcej znaków, czy ma min 1 cyfrę i min 1 znak specjalny
        boolean correctLength = false;
        boolean occursDigit = false;
        boolean occursUpperLetter = false;

        if(newPassword.length() >= 8) //czy 8 lub więcej znaków
            correctLength = true;

        char[] passwordChars = newPassword.toCharArray();
        for(char c : passwordChars)
        {
            if(Character.isDigit(c)) //czy cyfra
                occursDigit = true;
            if(Character.isUpperCase(c)) //czy duza litera
                occursUpperLetter = true;
        }


        if(newPassword.isEmpty() || repeatNewPassword.isEmpty() || oldPassword.isEmpty())
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("NotAllFieldsHaveBeenCompleted!"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(!newPassword.equals(repeatNewPassword))
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("NewPasswordDoesNotMatchRepeatPassword"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(!oldPassword.equals(userPassword))
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("OldPasswordDoesNotMatchCurrentPassword"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(!correctLength || !occursDigit || !occursUpperLetter)
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.errorColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("PasswordRequirements"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            changeErrorLabel.setVisible(true);
            changeErrorLabel.setTextFill(Color.web(AppSettings.successColor()));

            try
            {
                changeErrorLabel.setText(MultiLanguageStringGetter.getString("ChangePasswordSuccess"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

            userPassword = newPassword;

            //Zmiana w bazie danych hasła użytkownika na nowe podane hasło
            DatabaseUsers dbPassword = new DatabaseUsers();
            dbPassword.changingPassword(usernameField.getText(), newPassword);
        }
    }

    private void SetChangePanel(boolean state)
    {
        changeHBox.setVisible(state);
        changeSeparator.setVisible(state);
    }
}
