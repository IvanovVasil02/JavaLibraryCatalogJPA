package vasilivanov.dao;

import vasilivanov.entities.LibraryProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogDao {
  private final EntityManager em;

  public CatalogDao(EntityManager em) {
    this.em = em;
  }


  public List<LibraryProduct> getAllLp() {
    TypedQuery<LibraryProduct> getResultQuery = em.createQuery("SELECT lb FROM LibraryProduct lb", LibraryProduct.class);
    return getResultQuery.getResultList();
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

  public List<LibraryProduct> getItemsByYear(int year) {
    try {
      TypedQuery<LibraryProduct> getResultQuery = em.createQuery("SELECT lp FROM LibraryProduct lp WHERE EXTRACT(YEAR FROM lp.publicationYear) = :year", LibraryProduct.class);
      getResultQuery.setParameter("year", year);
      return getResultQuery.getResultList();
    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public List<LibraryProduct> getItemsByAuthor(String author) {
    try {
      TypedQuery<LibraryProduct> getResultQuery = em.createQuery("SELECT lp FROM LibraryProduct lp WHERE lp.author LIKE :author", LibraryProduct.class);
      getResultQuery.setParameter("author", "%" + author + "%");
      return getResultQuery.getResultList();
    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public List<LibraryProduct> getItemsByTitle(String title) {
    try {
      TypedQuery<LibraryProduct> getResultQuery = em.createQuery("SELECT lp FROM LibraryProduct lp WHERE lp.title LIKE :title", LibraryProduct.class);
      getResultQuery.setParameter("title", "%" + title + "%");
      return getResultQuery.getResultList();
    } catch (Exception e) {
      System.out.println("There was an error loading data");
      throw e;
    }
  }

  public void lbProductRefresh(LibraryProduct lbProductToRefresh) {
    em.refresh(lbProductToRefresh);
  }


}
