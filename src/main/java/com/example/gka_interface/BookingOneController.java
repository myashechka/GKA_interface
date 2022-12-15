package com.example.gka_interface;

import currentUser.CurrentUserManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Ticket;

public class BookingOneController {

    @FXML
    private Text From;

    @FXML
    private Button cancelButton;

    @FXML
    private Text To;

    @FXML
    private Text companyFrom;

    @FXML
    private Text count_score;

    @FXML
    private Text dateFrom;

    @FXML
    private Text dateTo;

    @FXML
    private Text timeFrom;

    @FXML
    private Text timeTo;

    protected final Ticket current_ticket;
    public BookingOneController(Ticket ticket) {current_ticket = ticket;}


    @FXML
    void initialize()
    {
        count_score.setText(""+current_ticket.cost/10);
        From.setText(current_ticket.formAirport);
        To.setText(current_ticket.toAirport);
        timeTo.setText(current_ticket.toTime);
        timeFrom.setText(current_ticket.fromTime);
        companyFrom.setText(current_ticket.company);
        dateFrom.setText(current_ticket.fromDate);
        dateTo.setText(current_ticket.toDate);

    }
}

