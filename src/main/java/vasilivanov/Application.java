package vasilivanov;

import com.github.javafaker.Faker;
import vasilivanov.dao.CatalogDao;
import vasilivanov.dao.LoanDao;
import vasilivanov.dao.UserDao;
import vasilivanov.entities.LibraryProduct;
import vasilivanov.entities.Loan;
import vasilivanov.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.util.Locale;

public class Application {
  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d2w3");

  public static void main(String[] args) throws ParseException {

    EntityManager em = emf.createEntityManager();
    System.out.println("Hello World!");

    try {
      Faker f = new Faker(new Locale("ITALY"));
      CatalogDao cd = new CatalogDao(em);
      UserDao ud = new UserDao(em);
      LoanDao ld = new LoanDao(em);
//      Supplier<User> userSupplier = () -> new User(f.code().ean8(), f.name().firstName(), f.name().lastName(), f.date().birthday());

      User usFrmDb = ud.getById("07593048");
      LibraryProduct lbFrmDb = cd.getById("0218417667");

      Loan loan1 = new Loan(usFrmDb, lbFrmDb, "2023-10-02", "2023-10-29", null);

      ld.save(loan1);

    } catch (Exception er) {
      System.err.println(er.getMessage());
    } finally {
      em.close();
      emf.close();
    }

  }
}
