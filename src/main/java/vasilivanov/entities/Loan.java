package vasilivanov.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Loans")
public class Loan {
  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "product_id")
  private LibraryProduct product;
  @Column(name = "start_date")
  private LocalDate startDate;
  @Column(name = "end_date")
  private LocalDate endtDate;
  @Column(name = "return_product_date")
  private LocalDate returnProductDate;

  public Loan(User user, LibraryProduct product, LocalDate startDate, LocalDate endtDate, LocalDate returnProductDate) {
    this.user = user;
    this.product = product;
    this.startDate = startDate;
    this.endtDate = endtDate;
    this.returnProductDate = returnProductDate;
  }

  public long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LibraryProduct getProduct() {
    return product;
  }

  public void setProduct(LibraryProduct product) {
    this.product = product;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndtDate() {
    return endtDate;
  }

  public void setEndtDate(LocalDate endtDate) {
    this.endtDate = endtDate;
  }

  public LocalDate getReturnProductDate() {
    return returnProductDate;
  }

  public void setReturnProductDate(LocalDate returnProductDate) {
    this.returnProductDate = returnProductDate;
  }

  @Override
  public String toString() {
    return "Loan{" +
            "id=" + id +
            ", user=" + user +
            ", product=" + product +
            ", startDate=" + startDate +
            ", endtDate=" + endtDate +
            ", returnProductDate=" + returnProductDate +
            '}';
  }
}
