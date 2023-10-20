package vasilivanov.dao;

import vasilivanov.entities.LibraryProduct;
import vasilivanov.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class LoanDao {
  private final EntityManager em;

  public LoanDao(EntityManager em) {
    this.em = em;
  }

  public void save(Loan loan) {
    EntityTransaction trc = em.getTransaction();
    try {
      trc.begin();
      em.persist(loan);
      trc.commit();
      System.out.println("New loan was added successfully");
    } catch (Exception e) {
      if (trc.isActive()) {
        trc.rollback();
      }
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public Loan getById(String id) {
    return em.find(Loan.class, id);
  }

  public void deleteLoanById(String id) {
    Loan loanToremove = getById(id);
    if (id != null) {
      EntityTransaction trc = em.getTransaction();
      try {
        if (loanToremove != null) {
          trc.begin();

          em.remove(loanToremove);
          trc.commit();
          System.out.println("The even was removed successfully");
        } else {
          System.out.println("There was not found element with this id.");
        }
      } catch (Exception e) {
        if (trc.isActive()) {
          trc.rollback();
        }
        System.out.println("There was an error loading data");
      }
    }

  }

  public List<LibraryProduct> getBorrowedProducts(String userCard) {
    try {
      TypedQuery<LibraryProduct> getResultQuery = em.createQuery("SELECT lp " +
              "FROM Loan l INNER JOIN LibraryProduct lp ON l.product = lp.isbnCode " +
              "WHERE l.user.id = :user_card", LibraryProduct.class);
      getResultQuery.setParameter("user_card", userCard);
      return getResultQuery.getResultList();
    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public List<Loan> getLoansExpiredORNotRepaid() {
    try {
      TypedQuery<Loan> getResultQuery = em.createQuery("Select l FROM Loan l WHERE l.endDate > :now AND WHERE l.returnProductDate = null", Loan.class);
      getResultQuery.setParameter("now", LocalDate.now());
      return getResultQuery.getResultList();
    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }


  public void loanRefresh(Loan loanToRefresh) {
    em.refresh(loanToRefresh);
  }
}
