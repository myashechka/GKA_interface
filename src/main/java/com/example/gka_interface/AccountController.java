package com.example.gka_interface;

import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Booking;
import models.Ticket;
import models.User;
import services.BookingService;

import java.io.IOException;
import java.util.List;

public class AccountController {

    private final BookingService bookingService = new BookingService();

    @FXML
    private Text bron;

    @FXML
    private Button exitButton;

    @FXML
    private Text dateField;

    @FXML
    private VBox divTickets;

    @FXML
    private Text loginField;

    @FXML
    private Button logotip;

    @FXML
    private Text nameField;

    @FXML
    private Text preField;

    @FXML
    private Text scoreField;

    @FXML
    private Text surnameField;

    void mainPage(){
        logotip.getScene().getWindow().hide();

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
    void initialize() throws IOException {
        logotip.setOnAction(actionEvent -> mainPage());

        User current_user = CurrentUserManager.getUser();
        dateField.setText(current_user.birthday);
        loginField.setText(current_user.email);
        nameField.setText(current_user.name);
        preField.setText(current_user.patronymic);
        scoreField.setText(""+current_user.score);
        surnameField.setText(current_user.surname);

        List<Booking> booking = bookingService.getBookingByUserId(current_user.id);
        if(!booking.isEmpty())
        {
            bron.setVisible(false);
            for (Booking value : booking) {

                String url;
                if (value.ticketFrom == null) {
                    BookingOneController dialogController = new BookingOneController(value.ticketTo);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingOne.fxml"));
                    loader.setController(dialogController);
                    Pane ticketPane = (Pane) loader.load();
                    divTickets.getChildren().add(ticketPane);
               } else {
                    BookingTwoController dialogController = new BookingTwoController(value.ticketTo, value.ticketFrom);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingTwo.fxml"));
                    loader.setController(dialogController);
                    Pane ticketPane = (Pane) loader.load();
                    divTickets.getChildren().add(ticketPane);
                }


            }
        }

        exitButton.setOnAction(actionEvent -> {
            CurrentUserManager.setUser(null);
            exitButton.getScene().getWindow().hide();

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
        });
    }
}