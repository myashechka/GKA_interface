package models;

import lombok.*;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    public Long id;
    public String name;
    public String surname;
    public String patronymic;


    public Boolean sex;
    public String birthday;
    public String email;
    public String password;
    public int score;
}
