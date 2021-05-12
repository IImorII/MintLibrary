import context.AppContext;
import dao.BaseDao;
import dao.factory.AbstractDaoFactory;
import entity.*;
import entity.dto.BookDto;
import repository.EntityRepository;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        AppContext.init();
        try {
//            Language language = new Language("Russian");
//            Book iriska = new Book();
//            Book luxa = new Book();
//            Book masyana = new Book();
//            iriska.setName("Iriska");
//            iriska.setDescription("Yellow cat!");
//            iriska.setPhotoUrl("https://bipbap.ru/wp-content/uploads/2017/08/1333182003.jpg");
//            iriska.setLanguage(language);
//            luxa.setName("Luxa");
//            luxa.setDescription("3 - color cat!");
//            luxa.setPhotoUrl("https://bipbap.ru/wp-content/uploads/2017/08/4-35.jpg");
//            luxa.setLanguage(language);
//            masyana.setName("Masyana");
//            masyana.setDescription("Black cat!");
//            masyana.setPhotoUrl("https://bipbap.ru/wp-content/uploads/2017/08/3-45.jpg");
//            masyana.setLanguage(language);
//            BaseDao dao = AbstractDaoFactory.getDaoFor(Book.class);
//            dao.create(iriska);
//            dao.create(luxa);
//            dao.create(masyana);
            List<Book> books = (List<Book>) EntityRepository.getInstance().retrieveCollection(Book.class);
            List<Book> books1 = (List<Book>) EntityRepository.getInstance().retrieveCollection(Book.class);
            System.out.println(books);
            for (Book b : books) {
                System.out.println(b.getName());
            }
            System.out.println(books == books1);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
