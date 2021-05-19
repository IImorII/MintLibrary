import dao.LanguageDao;
import dao.impl.ProxyDaoFactory;
import entity.Language;
import repository.EntityRepository;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Language language = new Language();
        language.setName("Test2");
        List<Language> languages = (List<Language>) EntityRepository.getInstance().retrieveCollection(Language.class);
        LanguageDao dao = (LanguageDao) ProxyDaoFactory.getDaoFor(Language.class);
        try {
            for (Language l : languages) {
                System.out.println(l.getName());
            }
            dao.create(language);
            languages = (List<Language>) EntityRepository.getInstance().retrieveCollection(Language.class);
            for (Language l : languages) {
                System.out.println(l.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
