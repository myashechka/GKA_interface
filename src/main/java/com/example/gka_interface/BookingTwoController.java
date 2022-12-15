package com.example.gka_interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import models.Ticket;

public class BookingTwoController{

    @FXML
    private Text From;

    @FXML
    private Button cancelButton;

    @FXML
    private Text From1;

    @FXML
    private Text To;

    @FXML
    private Text To1;

    @FXML
    private Text companyFrom;

    @FXML
    private Text companyFrom1;

    @FXML
    private Text count_score;

    @FXML
    private Text dateFrom;

    @FXML
    private Text dateFrom1;

    @FXML
    private Text dateTo;

    @FXML
    private Text dateTo1;

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

    public BookingTwoController(Ticket ticket_to, Ticket ticket_from) {
        this.ticket_to = ticket_to;
        this.ticket_from = ticket_from;
    }

    @FXML
    void initialize()
    {
        count_score.setText(""+(ticket_to.cost + ticket_from.cost)/10);
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

    }

}

