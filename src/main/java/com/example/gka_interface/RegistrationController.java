package com.example.gka_interface;

import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.User;
import services.UserService;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrationController {

    private final UserService userService = new UserService();
    @FXML
    private DatePicker birthdayField;

    @FXML
    private RadioButton isMan;

    @FXML
    private RadioButton isWoman;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private Button logo;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField patronymicField;

    @FXML
    private Button personalAccount;

    @FXML
    private Button regButton;

    @FXML
    private Label regMessage;

    @FXML
    private TextField surnameField;

    void registrationUser() throws IOException {
        LocalDate date = birthdayField.getValue();
        String login = loginField.getText();
        String name = nameField.getText();
        String password = passwordField.getText();
        String patronymic = patronymicField.getText();
        String surname = surnameField.getText();
        if( date == null || login.equals("") || name.equals("") || password.equals("") || patronymic.equals("") || surname.equals("") || (!isMan.isSelected() && !isWoman.isSelected())){
            regMessage.setText("Необходимо заполнить все поля.");
            regMessage.setTextFill(Color.RED);
        }
        else{
            User user = userService.postUser(new User(0L, name, surname, patronymic, isMan.isSelected(), date.toString(), login, password, 0));
            if (user.id == null) {
                regMessage.setText("Пользователь с таким логином уже существует.");
                regMessage.setTextFill(Color.RED);
            }else {
                regMessage.setText("Вы успешно зарегистрированы!");
                regMessage.setTextFill(Color.GREEN);
            }
        }
    }

    void mainPage(){
        logo.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    };

    void personalAccountPage(){
        personalAccount.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("entry.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void initialize(){
        ToggleGroup group = new ToggleGroup();
        isMan.setToggleGroup(group);
        isWoman.setToggleGroup(group);
        regButton.setOnAction(actionEvent -> {
            try {
                registrationUser();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        logo.setOnAction(actionEvent -> {
            mainPage();
        });

        personalAccount.setOnAction(actionEvent -> {
            personalAccountPage();
        });
    };

}

