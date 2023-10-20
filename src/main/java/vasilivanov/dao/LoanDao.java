package vasilivanov.dao;

import vasilivanov.entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

  public void loanRefresh(Loan loanToRefresh) {
    em.refresh(loanToRefresh);
  }
}