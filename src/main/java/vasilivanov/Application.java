package vasilivanov;

import com.github.javafaker.Faker;
import vasilivanov.dao.CatalogDao;
import vasilivanov.dao.LoanDao;
import vasilivanov.dao.UserDao;
import vasilivanov.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
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
      Faker f = new Faker(new Locale("ITALY"));
      CatalogDao cd = new CatalogDao(em);
      UserDao ud = new UserDao(em);
      LoanDao ld = new LoanDao(em);
      Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2000");
      Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2023");
      Supplier<User> userSupplier = () -> new User(f.code().ean8(), f.name().firstName(), f.name().lastName(), f.date().birthday());
      Supplier<Book> bookSupplier = () -> new Book(f.code().isbn10(),
              f.book().title(), convertToLocalDate(f.date().between(date1, date2)),
              getRndm(), f.book().author(), f.book().genre());
      Supplier<Magazine> magazineSupplier = () -> new Magazine(f.code().isbn10(),
              f.book().title(), convertToLocalDate(f.date().between(date1, date2)),
              getRndm(), randomPeriodicity());

//      for (int i = 0; i < 9; i++) {
//        ud.save(userSupplier.get());
//        cd.save(bookSupplier.get());
//        cd.save(magazineSupplier.get());
//      }

      User usFrmDb = ud.getById("07593048");
      LibraryProduct lbFrmDb = cd.getById("0218417667");

      Loan loan1 = new Loan(usFrmDb, lbFrmDb, "2023-10-02", "2023-10-29", null);

      ld.getLoansExpiredORNotRepaid().forEach(System.out::println);

      while (true) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 0 to view the library catalog");
        System.out.println("Enter 1 to add an element the library catalog");
        System.out.println("Enter 2 to remove an element from the library catalog");
        System.out.println("Enter 3 to search an element of the catalog by ISBN");
        System.out.println("Enter 4 to search elements of the catalog by pubblication date");
        System.out.println("Enter 5 to search an element of the catalog by author");
        System.out.println("Enter 6 to view your borrowed elements");
        System.out.println("Enter 7 to to see overdue or unrepaid loans");

        try {
          int choice = Integer.parseInt(scanner.nextLine());
          String isbn;

          switch (choice) {

            case 0:
              if (!cd.getAllLp().isEmpty()) {
                cd.getAllLp().forEach(System.out::println);
              } else {
                System.out.println("There are not library product, add one");
              }
              System.out.println("What do you want to do now");
              break;

            case 1:
              System.out.println("enter the data of library product that you want to add");
              cd.save(bookSupplier.get());
              System.out.println("What do you want to do now");
              break;

            case 2:
              System.out.println("enter the ISBN code of the book you want to remove from the catalog");
              isbn = scanner.nextLine();
              cd.deleteLibraryProductById(isbn);
              break;

            case 3:
              System.out.println("enter the ISBN code to search: ");
              isbn = scanner.nextLine();
              if (cd.getById(isbn) != null) {
                System.out.println(cd.getById(isbn));
              } else {
                System.out.println("There is no product with this ISBN");
              }
              System.out.println("What do you want to do now");
              break;

            case 4:
              System.out.println("enter the year of publication ");
              int userDate = Integer.parseInt(scanner.nextLine());
              if (!cd.getItemsByYear(userDate).isEmpty()) {
                cd.getItemsByYear(userDate).forEach(System.out::println);
              } else {
                System.out.println("There are no products related to the year entered");
              }
              System.out.println("What do you want to do now");
              break;

            case 5:
              System.out.println("Enter the author's name");
              String author = scanner.nextLine();
              if (!cd.getItemsByAuthor(author).isEmpty()) {
                cd.getItemsByAuthor(author).forEach(System.out::println);
              } else {
                System.out.println("There are no products related to author entered");
              }
              System.out.println("What do you want to do now");
              break;

            case 6:
              System.out.println("Enter the user's card number");
              String useCard = scanner.nextLine();
              if (!ld.getBorrowedProducts(useCard).isEmpty()) {
                ld.getBorrowedProducts(useCard).forEach(System.out::println);
              } else {
                System.out.println("the list of loaned products is empty or the card number is wrong");
              }
              System.out.println("What do you want to do now");
              break;

            case 7:
              ld.getLoansExpiredORNotRepaid().forEach(System.out::println);
              System.out.println("What do you want to do now");
              break;

            default:
              System.out.println("You need to enter one of the controls above");
              choice = Integer.parseInt(scanner.nextLine());
              break;

          }
        } catch (Exception ex) {
          System.out.println("you must enter one of the appropriate controls");
        }
      }


    } catch (Exception er) {
      System.err.println(er.getMessage());
    } finally {
      em.close();
      emf.close();
    }

  }
}
