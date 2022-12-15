package com.example.gka_interface;
import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import models.Booking;
import models.Ticket;
import services.BookingService;
import services.TicketService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TicketController {
    private final BookingService bookingService = new BookingService();
    @FXML
    private Text From;

    @FXML
    private Text To;

    @FXML
    private Button buyButton;

    @FXML
    private Text companyFrom;

    @FXML
    private Text dateFrom;

    @FXML
    private Text dateTo;

    @FXML
    private Text price;

    @FXML
    private Text timeFrom;

    @FXML
    private Text timeTo;

    @FXML
    private Text buyMessage;

    private Ticket current_ticket;
    public TicketController(Ticket ticket) {
        current_ticket = ticket;
    }

    void buyTicket() throws IOException {
        buyButton.setStyle("-fx-background-color: #0abc34 ");
        buyButton.setText("Билет куплен");
        buyButton.setDisable(true);
        bookingService.postBooking(new Booking(0L,CurrentUserManager.getUser(),current_ticket,null, true,true));
        CurrentUserManager.getUser().score += current_ticket.cost/10;
    }

    @FXML
    void initialize() throws IOException {
        From.setText(current_ticket.formAirport);
        To.setText(current_ticket.toAirport);
        price.setText(current_ticket.cost +" руб.");
        timeTo.setText(current_ticket.toTime);
        timeFrom.setText(current_ticket.fromTime);
        companyFrom.setText(current_ticket.company);
        dateFrom.setText(current_ticket.fromDate);
        dateTo.setText(current_ticket.toDate);

        if(CurrentUserManager.getUser() == null)
        {
            buyButton.setDisable(true);
            buyButton.setStyle("-fx-background-color: #a9a0a0");
        }
        else {
            buyButton.setDisable(false);
            buyButton.setOnAction(actionEvent -> {
                try {
                    buyTicket();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            buyMessage.setVisible(false);

            List <Booking> user_bookings = bookingService.getBookingByUserId(CurrentUserManager.getUser().id);
            for (Booking user_booking : user_bookings) {
                if (Objects.equals(user_booking.ticketTo.id, current_ticket.id)) {
                    buyButton.setStyle("-fx-background-color: #0abc34 ");
                    buyButton.setText("Билет куплен");
                    buyButton.setDisable(true);
                }
            }
        }
    }
}
