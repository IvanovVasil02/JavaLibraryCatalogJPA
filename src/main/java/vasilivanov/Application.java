package vasilivanov;

import com.github.javafaker.Faker;
import vasilivanov.dao.CatalogDao;
import vasilivanov.dao.UserDao;
import vasilivanov.entities.Loan;
import vasilivanov.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.Locale;
import java.util.function.Supplier;

public class Application {
  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d2w3");

  public static void main(String[] args) throws ParseException {

    EntityManager em = emf.createEntityManager();
    System.out.println("Hello World!");

    try {
      CatalogDao cd = new CatalogDao(em);
      UserDao ud = new UserDao(em);
      Faker f = new Faker(new Locale("ITALY"));
//      Supplier<User> userSupplier = () -> new User(f.code().ean8(), f.name().firstName(), f.name().lastName(), f.date().birthday());

      Supplier<Loan> loanSupplier = () -> new User(f.code().ean8(), f.name().firstName(), f.name().lastName(), f.date().birthday());


    } catch (Exception er) {
      System.err.println(er.getMessage());
    } finally {
      em.close();
      emf.close();
    }

  }
}
