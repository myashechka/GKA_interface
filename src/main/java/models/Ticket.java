package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    public Long id;

    public String company;
    public String formAirport;
    public String fromTime;
    public String fromDate;
    public String toAirport;
    public String toTime;
    public String toDate;
    public int cost;
}
