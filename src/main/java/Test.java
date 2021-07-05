import dao.Dao;
import dao.factory.ProxyDaoInstance;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;

public class Test {
    public static void main(String[] args) {
        initBooks();
    }

    public static void initBooks() {
        for (int i = 0; i < 100; i++) {
            Language language = new Language("Language " + i % 10);
            Genre genre = new Genre("Genre " + i % 10);
            Author author = new Author("Author " + i % 10);
            Book book = new Book();

            book.setName("Book " + i);
            book.setDescription("Description " + i);
            book.setCount(10);
            book.setRate((double)i);
            book.setYearOfRelease(2000 + i);
            book.setLanguage(language);
            try {
                Dao.of(Book.class).create(book);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
