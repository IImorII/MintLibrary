package locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocaleTagSupport extends TagSupport {
    private static final String NO_PROPERTY_FILE = "No property file!";
    private static final String CANNOT_PRINT_FOR_THIS_LOCALE_MSG = "Could not print message! locale {} message {}";
    private static final String LOCALE = "locale_";
    private static final String PROPERTIES = ".properties";
    private static final String LANGUAGE = "language";
    private static final String EN = "en";
    private static final String RU = "ru";

    private String key;

    @Override
    public int doStartTag() {
        JspWriter out = pageContext.getOut();
        String language = RU;

        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie temp : cookies) {
                if (temp.getName().equals(LANGUAGE)) {
                    language = temp.getValue();
                }
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
        }
        return EVAL_PAGE;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
