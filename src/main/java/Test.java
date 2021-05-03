import context.AppContext;
import dao.impl.*;
import entity.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        AppContext.init();
        List<Language> languages = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language language = new Language();
        language.setName("Normana");
        LanguageDao.getInstance().create(language);
        language = LanguageDao.getInstance().getByName(language.getName()).get();
        languages.add(language);

        Genre genre = new Genre();
        genre.setName("Fantasy");
        GenreDao.getInstance().create(genre);
        genre = GenreDao.getInstance().getByName(genre.getName()).get();
        genres.add(genre);

        Author author = new Author();
        author.setName("Terry");
        author.setBirth(2000);
        author.setLanguages(languages);
        AuthorDao.getInstance().create(author);
        author = AuthorDao.getInstance().getByName(author.getName()).get();
        authors.add(author);

        Book book = new Book();
        book.setName("Sword");
        book.setLanguage(language);
        book.setYearOfRelease(2010);
        book.setCount(1);
        book.setRate(0.0);
        book.setAuthors(authors);
        book.setGenres(genres);
        BookDao.getInstance().create(book);
        book = BookDao.getInstance().getByName(book.getName()).get();
        books.add(book);

    }
}
