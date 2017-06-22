package pl.ctrl;

import pl.model.Book;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by eric on 6/22/17.
 */
public class BookController {
    @PersistenceContext(unitName = "MinimalApp")
    private EntityManager em;
    @Resource() UserTransaction ut;

    public List<Book> getBooks() {}

    public void refreshObject(Book book) {}

    public String add(String isbn,
                      String title,
                      int year) {}

    public String update(String isbn,
                         String title,
                         int year) {}

    public String destroy(String isbn) {}

}
