package vasilivanov.entities;

import vasilivanov.enums.Sex;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String surname;
  private String birtDate;
  private LocalDate birthDate;

  public User() {
  }

  public User(String name, String surname, String email, String birthDate, Sex sex) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.birthDate = LocalDate.parse(birthDate, formatter);
    this.sex = sex;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public Sex getSex() {
    return sex;
  }

  public void setSex(Sex sex) {
    this.sex = sex;
  }

  public List<Partecipation> getPartecipationList() {
    return partecipationList;
  }

  public void setPartecipationList(List<Partecipation> partecipationList) {
    this.partecipationList = partecipationList;
  }

  @Override
  public String toString() {
    return " name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", email='" + email + '\'' +
            ", birthDate=" + birthDate +
            ", sex=" + sex +
            '}';
  }
}
