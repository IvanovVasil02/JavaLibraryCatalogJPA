package vasilivanov.dao;

import vasilivanov.entities.LibraryProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CatalogDao {
  private final EntityManager em;

  public CatalogDao(EntityManager em) {
    this.em = em;
  }

  public void save(LibraryProduct lbProduct) {
    EntityTransaction trc = em.getTransaction();
    try {
      trc.begin();
      em.persist(lbProduct);
      trc.commit();
      System.out.println("New lbProduct was added successfully");
    } catch (Exception e) {
      if (trc.isActive()) {
        trc.rollback();
      }
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public LibraryProduct getById(String id) {
    return em.find(LibraryProduct.class, id);
  }

  public void deleteLibraryProductById(String id) {
    LibraryProduct lbProductToremove = getById(id);
    if (id != null) {
      EntityTransaction trc = em.getTransaction();
      try {
        if (lbProductToremove != null) {
          trc.begin();

          em.remove(lbProductToremove);
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

  public void lbProductRefresh(LibraryProduct lbProductToRefresh) {
    em.refresh(lbProductToRefresh);
  }
}
