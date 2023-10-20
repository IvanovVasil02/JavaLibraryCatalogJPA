package vasilivanov;

import com.github.javafaker.Faker;
import vasilivanov.dao.CatalogDao;
import vasilivanov.entities.Book;
import vasilivanov.entities.Magazine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.function.Supplier;

import static vasilivanov.entities.LibraryProduct.convertToLocalDate;
import static vasilivanov.entities.LibraryProduct.getRndm;
import static vasilivanov.entities.Magazine.randomPeriodicity;

public class Application {
  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d2w3");

  public static void main(String[] args) throws ParseException {

    EntityManager em = emf.createEntityManager();
    System.out.println("Hello World!");

    try {
      CatalogDao cd = new CatalogDao(em);
      Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020");
      Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2023");

      Faker f = new Faker(new Locale("ITALY"));
      Supplier<Book> bookSupplier = () -> new Book(f.code().isbn10(),
              f.book().title(), convertToLocalDate(f.date().between(date1, date2)),
              getRndm(), f.book().author(), f.book().genre());

      Supplier<Magazine> magazineSupplier = () -> new Magazine(f.code().isbn10(),
              f.book().title(), convertToLocalDate(f.date().between(date1, date2)),
              getRndm(), randomPeriodicity());

      for (int i = 0; i < 10; i++) {
        cd.save(bookSupplier.get());
        cd.save(magazineSupplier.get());
      }
    } catch (Exception er) {
      System.err.println(er.getMessage());
    } finally {
      em.close();
      emf.close();
    }

  }
}
