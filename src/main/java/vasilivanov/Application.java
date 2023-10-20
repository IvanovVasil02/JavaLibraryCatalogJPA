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
import java.util.Scanner;
import java.util.function.Supplier;

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
      Supplier<User> userSupplier = () -> new User(f.code().ean8(), f.name().firstName(), f.name().lastName(), f.date().birthday());

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
              cd.getAllLp().forEach(System.out::println);
              System.out.println("What do you want to do now");
              choice = Integer.parseInt(scanner.nextLine());

            case 1:
              while (choice == 1) {
                System.out.println("enter the data of library product that you want to add");
                ud.save(userSupplier.get());


                System.out.println("What do you want to do now");
                choice = Integer.parseInt(scanner.nextLine());
              }

            case 2:
              while (choice == 2) {
                System.out.println("enter the ISBN code of the book you want to remove from the catalog");
                isbn = scanner.nextLine();
                cd.deleteLibraryProductById(isbn);
                choice = Integer.parseInt(scanner.nextLine());
              }
            case 3:
              while (choice == 3) {
                System.out.println("enter the ISBN code to search: ");
                isbn = scanner.nextLine();
                cd.getById(isbn);

                System.out.println("What do you want to do now");
                choice = Integer.parseInt(scanner.nextLine());
              }
            case 4:
              while (choice == 4) {
                System.out.println("enter the year of publication ");
                int userDate = Integer.parseInt(scanner.nextLine());
                cd.getItemsByYear(userDate).forEach(System.out::println);

                System.out.println("What do you want to do now");
                choice = Integer.parseInt(scanner.nextLine());
              }
            case 5:
              while (choice == 5) {
                System.out.println("Enter the author's name");
                String author = scanner.nextLine();
                cd.getItemsByAuthor(author).forEach(System.out::println);

                System.out.println("What do you want to do now");
                choice = Integer.parseInt(scanner.nextLine());
              }
            case 6:
              System.out.println("Enter the user's card number");
              String useCard = scanner.nextLine();
              ld.getBorrowedProducts(useCard).forEach(System.out::println);

              System.out.println("What do you want to do now");
              choice = Integer.parseInt(scanner.nextLine());
            case 7:

              ld.getLoansExpiredORNotRepaid().forEach(System.out::println);

              System.out.println("What do you want to do now");
              choice = Integer.parseInt(scanner.nextLine());
            default:
              System.out.println("You need to enter one of the controls above");
              choice = Integer.parseInt(scanner.nextLine());

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
