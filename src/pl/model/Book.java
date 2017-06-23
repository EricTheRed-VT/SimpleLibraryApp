package pl.model;

/**
 * Created by eric on 6/22/17.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.util.List;

//[__strong__]
@Entity @Table(name="books")
@RequestScoped @ManagedBean(name ="book")
public class Book {
    @Id private String isbn;
    private String title;
    private int year;

    //constructors
    public Book() {}
    public Book( String isbn, String title, int year ) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.setYear(year);
    }

    //getters/setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title;}

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year;}

    //CRUD data mgmt methods
    public static void add(EntityManager em,
                           UserTransaction ut,
                           String isbn,
                           String title,
                           int year) throws Exception {
        ut.begin();
        Book book = new Book(isbn, title, year);
        em.persist(book);
        ut.commit();
    }

    public static List<Book> retrieveAll(EntityManager em) {
        Query query = em.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

//    public static Book retrieve() {}

    public static void update(EntityManager em,
                              UserTransaction ut,
                              String isbn,
                              String title,
                              int year) throws Exception {
        ut.begin();
        Book book = em.find(Book.class, isbn);
        book.setTitle(title);
        book.setYear(year);
        ut.commit();
    }

    public static void destroy(EntityManager em,
                               UserTransaction ut,
                               String isbn) throws Exception {
        ut.begin();
        Book book = em.find(Book.class, isbn);
        em.remove(book);
        ut.commit();
    }

    public static void clearData(EntityManager em,
                                 UserTransaction ut) throws Exception {
        ut.begin();
        Query deleteStatement = em.createQuery("DELETE FROM Book");
        deleteStatement.executeUpdate();
        ut.commit();
    }

    public static void createTestData(EntityManager em,
                                      UserTransaction ut) throws Exception {
        Book book = null;
        Book.clearData(em, ut);
        ut.begin();

        book = new Book("006251587X","Weaving the Web", 2000);
        em.persist( book);
        book = new Book("0465026567","Gödel, Escher, Bach", 1999);
        em.persist( book);
        book = new Book("0465030793","I Am A Strange Loop", 2008);
        em.persist( book);

        ut.commit();
    }

}



/* MySQL table creation
CREATE TABLE IF NOT EXISTS books (
  isbn VARCHAR(10) NOT NULL PRIMARY KEY,
  title VARCHAR(128),
  year SMALLINT
);
* */
