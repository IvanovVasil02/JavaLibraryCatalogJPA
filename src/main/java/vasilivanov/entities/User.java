package vasilivanov.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(name = "user_card")
  private String id;
  private String name;
  private String surname;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @OneToMany(mappedBy = "user")
  private List<Loan> loansList;

  public User() {
  }

  public User(String id, String name, String surname, Date birthDate) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.birthDate = convertToLocalDate(birthDate);
  }

  public static LocalDate convertToLocalDate(Date dateToConvert) {
    return Instant.ofEpochMilli(dateToConvert.getTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDate();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", birthDate=" + birthDate +
            '}';
  }
}
