package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    public Long id;

    public User user;

    public Ticket ticketTo;

    public Ticket ticketFrom;

    public boolean paid;

    public boolean actual;
}
