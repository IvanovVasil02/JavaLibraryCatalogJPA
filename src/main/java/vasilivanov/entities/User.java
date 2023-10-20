package vasilivanov.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  @Column(name = "card_number")
  private long id;
  private String name;
  private String surname;
  @Column(name = "birth_date")
  private LocalDate birthDate;
  @OneToMany(mappedBy = "user")
  private List<Loan> loansList;

  public User() {
  }

  public User(String name, String surname, String birthDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    this.name = name;
    this.surname = surname;
    this.birthDate = LocalDate.parse(birthDate, formatter);

  }

  public long getId() {
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
