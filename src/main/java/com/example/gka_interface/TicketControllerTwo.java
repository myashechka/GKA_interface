package com.example.gka_interface;

import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.Booking;
import models.Ticket;
import services.BookingService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class TicketControllerTwo {

    private final BookingService bookingService = new BookingService();
    @FXML
    private Text From;

    @FXML
    private Text From1;

    @FXML
    private Text To;

    @FXML
    private Text To1;

    @FXML
    private Button buyButton;

    @FXML
    private Text buyMessage;

    @FXML
    private Text companyFrom;

    @FXML
    private Text companyFrom1;

    @FXML
    private Text dateFrom;

    @FXML
    private Text dateFrom1;

    @FXML
    private Text dateTo;

    @FXML
    private Text dateTo1;

    @FXML
    private Text price;

    @FXML
    private Text timeFrom;

    @FXML
    private Text timeFrom1;

    @FXML
    private Text timeTo;

    @FXML
    private Text timeTo1;

    private Ticket ticket_to;
    private Ticket ticket_from;
    public TicketControllerTwo(Ticket ticket_to, Ticket ticket_from) {
       this.ticket_to = ticket_to;
       this.ticket_from = ticket_from;
    }

    void buyTicket() throws IOException {
        buyButton.setStyle("-fx-background-color: #0abc34 ");
        buyButton.setText("Билет куплен");
        buyButton.setDisable(true);
        CurrentUserManager.getUser().score += (ticket_to.cost + ticket_from.cost)/10;
        bookingService.postBooking(new Booking(0L,CurrentUserManager.getUser(),ticket_to, ticket_from, true,true));
    }

    @FXML
    void initialize() throws IOException {
        price.setText((ticket_to.cost + ticket_from.cost)+" руб.");
        From.setText(ticket_to.formAirport);
        From1.setText(ticket_to.toAirport);
        To.setText(ticket_to.toAirport);
        To1.setText(ticket_to.formAirport);
        timeTo.setText(ticket_to.toTime);
        timeTo1.setText(ticket_from.toTime);
        timeFrom.setText(ticket_to.fromTime);
        timeFrom1.setText(ticket_from.fromTime);
        companyFrom.setText(ticket_to.company);
        companyFrom1.setText(ticket_from.company);
        dateFrom.setText(ticket_to.fromDate);
        dateFrom1.setText(ticket_from.fromDate);
        dateTo.setText(ticket_to.toDate);
        dateTo1.setText(ticket_from.toDate);

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

            System.out.println(ticket_to.id);
            System.out.println(ticket_from.id);
            List<Booking> user_bookings = bookingService.getBookingByUserId(CurrentUserManager.getUser().id);
            for (Booking user_booking : user_bookings) {
                System.out.println(user_booking.ticketTo.id);
                if (Objects.equals(user_booking.ticketTo.id, ticket_to.id) && user_booking.ticketFrom!=null &&
                        Objects.equals(user_booking.ticketFrom.id, ticket_from.id)) {
                    buyButton.setStyle("-fx-background-color: #0abc34 ");
                    buyButton.setText("Билет куплен");
                    buyButton.setDisable(true);
                }
            }
        }
    }

}

