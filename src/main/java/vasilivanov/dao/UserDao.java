package vasilivanov.dao;

import vasilivanov.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao {
  private final EntityManager em;

  public UserDao(EntityManager em) {
    this.em = em;
  }

  public void save(User user) {
    EntityTransaction trc = em.getTransaction();
    try {
      trc.begin();
      em.persist(user);
      trc.commit();
      System.out.println("New user was added successfully");
    } catch (Exception e) {
      if (trc.isActive()) {
        trc.rollback();
      }
      System.out.println("There was an error loading data: ");
      throw e;
    }
  }

  public User getById(String id) {
    return em.find(User.class, id);
  }

  public void deleteUserById(String id) {
    User userToremove = getById(id);
    if (id != null) {
      EntityTransaction trc = em.getTransaction();
      try {
        if (userToremove != null) {
          trc.begin();

          em.remove(userToremove);
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

  public void userRefresh(User user) {
    em.refresh(user);
  }
}
