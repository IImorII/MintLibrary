import com.google.protobuf.GeneratedMessageV3;
import dao.LanguageDao;
import dao.impl.ProxyDaoFactory;
import entity.Book;
import entity.Language;
import repository.EntityRepository;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        initBooks();
    }

    public static void initBooks() {
        for (int i = 0; i < 100; i++) {
            Language language = new Language("Language " + i);
            Book book = new Book();
            book.setName("Book " + i);
            book.setDescription("Description " + i);
            book.setCount(10);
            book.setRate((double)i);
            book.setYearOfRelease(2000 + i);
            book.setLanguage(language);
            book.setPhotoUrl("img/BookDefault.png");
            try {
                ProxyDaoFactory.getDaoFor(Book.class).create(book);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
