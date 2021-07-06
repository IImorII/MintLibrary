package locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LocaleTagSupport extends TagSupport {
    private static final String NO_PROPERTY_FILE = "No property file!";
    private static final String LOCALE = "locale_";
    private static final String PROPERTIES = ".properties";
    private static final String LANGUAGE = "language";
    private static final List<String> languages = new ArrayList<>();
    private static final String EN = "en";
    private static final String RU = "ru";
    private static final String PL = "pl";
    private static final String BE = "be";
    private static final String DEFAULT = EN;

    public LocaleTagSupport() {
        languages.add(EN);
        languages.add(RU);
        languages.add(PL);
        languages.add(BE);
    }

    private String key;

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String language = null;

        Object userLanguage = request.getAttribute(LANGUAGE);
        HttpSession session = request.getSession();
        if (session != null) {
            userLanguage = session.getAttribute(LANGUAGE);
        }
        if (userLanguage != null) {
            language = userLanguage.toString().toLowerCase();
        }
        if (language == null || !languages.contains(language)) {
            language = DEFAULT;
        }
        Properties properties = new Properties();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(LOCALE + language + PROPERTIES)) {
            properties.load(inputStream);
        } catch (IOException ex) {
            throw new IllegalStateException(NO_PROPERTY_FILE);
        }
        String message = properties.getProperty(key);
        try {
            out.print(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return EVAL_PAGE;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private void setCookie(String value) {

    }
}
