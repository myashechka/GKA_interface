package com.example.gka_interface;


import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.User;
import services.UserService;

import java.io.IOException;

public class EntryController {

    private final UserService userService = new UserService();
    @FXML
    private Button authButton;

    @FXML
    private Button logo;

    @FXML
    private Button enterButton;

    @FXML
    private Text errorEntry;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void transferToRegistration(){
        authButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("registration.fxml"));
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



    void loginToPersonalAccount() throws IOException {
        String loginText = loginField.getText();
        String loginPassword = passwordField.getText();

        if(!loginText.equals("") && !loginPassword.equals("")){
            User current_user = userService.getUser(loginText);
            if(current_user!=null && current_user.password.equals(loginPassword))
            {
                CurrentUserManager.setUser(current_user);
                if(CurrentUserManager.getUser() == null) return;

                loginField.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("personalAccount.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
            else {
                errorEntry.setVisible(true);
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

    @FXML
    void initialize(){
        authButton.setOnAction(actionEvent -> transferToRegistration());
        enterButton.setOnAction(actionEvent -> {
            try {
                loginToPersonalAccount();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        logo.setOnAction(actionEvent -> {
            mainPage();
        });
    };
}

