package pl.ctrl;

import pl.model.Book;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * Created by eric on 6/22/17.
 */
@SessionScoped @ManagedBean(name ="bookCtrl")
public class BookController {
    @PersistenceContext(unitName ="MinimalApp")
    private EntityManager em;
    @Resource() UserTransaction ut;

    public List<Book> getBooks() {
        return Book.retrieveAll(em);
    }

    public void refreshObject(Book book) {}

    public String add(String isbn,
                      String title,
                      int year) {
        try {
            Book.add(em, ut, isbn, title, year);
            //clear the form after saving the Book record
            FacesContext fContext = FacesContext.getCurrentInstance();
            fContext.getExternalContext().getRequestMap().remove("book");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "create";
    }

    public String update(String isbn,
                         String title,
                         int year) {
        try {
            Book.update(em, ut, isbn, title, year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "update";
    }

    public String destroy(String isbn) {}

}
