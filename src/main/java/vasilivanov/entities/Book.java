package vasilivanov.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Book extends LibraryProduct {

  private String author;
  private String genre;


  public Book(String isbnCode, String title, LocalDate publicationYear, long pagesNumber, String author, String genre) {
    super(isbnCode, title, publicationYear, pagesNumber);
    this.author = author;
    this.genre = genre;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
  public String toString() {
    return "Book{" +
            "isbnCode='" + isbnCode + '\'' +
            ", title='" + title + '\'' +
            ", publicationYear='" + publicationYear + '\'' +
            ", pagesNumber='" + pagesNumber + '\'' +
            "author='" + author + '\'' +
            ", genre='" + genre + '\'' +
            '}';
  }

}
