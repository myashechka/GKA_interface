package com.example.gka_interface;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import currentUser.CurrentUserManager;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Ticket;
import services.TicketService;
import services.UserService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class HelloController {

    private final TicketService ticketService = new TicketService();
    @FXML
    private DatePicker backDate;

    @FXML
    private VBox ticketsDiv;

    @FXML
    private Button findButton;

    @FXML
    private TextField fromField;

    @FXML
    private Button personalAccount;

    @FXML
    private Label findMessage;

    @FXML
    private DatePicker thereDate;

    @FXML
    private TextField toField;

    void transferToYourPersonalAccount(){
        personalAccount.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        if(CurrentUserManager.getUser() == null)
            loader.setLocation(getClass().getResource("entry.fxml"));
        else
            loader.setLocation(getClass().getResource("personalAccount.fxml"));
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

    void findTickets() throws IOException {
        String from = fromField.getText();
        String to = toField.getText();
        LocalDate date1 = thereDate.getValue();
        LocalDate date2 = backDate.getValue();
        ticketsDiv.getChildren().clear();
        findMessage.setText("");

        if( from.equals("") || to.equals("") || date1 == null){
            findMessage.setText("Необходимо заполнить все обязательные поля.");
            findMessage.setTextFill(Color.RED);
        }else if(date2 != null) {
            List<Ticket> tickets_from = ticketService.getTickets(from, to, date1.toString());
            List<Ticket> tickets_to = ticketService.getTickets(to, from, date2.toString());
            if(tickets_from.isEmpty() || tickets_to.isEmpty()) {

                findMessage.setText("К сожалению, необходимые билеты не найдены.");
                findMessage.setTextFill(Color.RED);
            }else {
                for(int i = 0; i<tickets_from.size(); i++) {
                    for(int j=0; j < tickets_to.size(); j++) {
                        TicketControllerTwo dialogController = new TicketControllerTwo(tickets_from.get(i), tickets_to.get(j));
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketTwo.fxml"));
                        loader.setController(dialogController);
                        Pane ticketPane = (Pane) loader.load();
                        ticketsDiv.getChildren().add(ticketPane);
                    }
                }
            }
        }else {
            List<Ticket> tickets_from = ticketService.getTickets(from, to, date1.toString());
            if(tickets_from.isEmpty()) {

                findMessage.setText("К сожалению, необходимые билеты не найдены.");
                findMessage.setTextFill(Color.RED);
            }else {
                for(int i = 0; i<tickets_from.size(); i++) {
                    TicketController dialogController = new TicketController(tickets_from.get(i));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ticket.fxml"));
                    loader.setController(dialogController);
                    Pane ticketPane = (Pane) loader.load();
                    ticketsDiv.getChildren().add(ticketPane);
                }
            }
        }
    }

    @FXML
    void initialize(){
        personalAccount.setOnAction(actionEvent -> transferToYourPersonalAccount());
        findButton.setOnAction(actionEvent -> {
            try {
                findTickets();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        };
    }

