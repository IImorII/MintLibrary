import context.AppContext;
import dao.impl.*;
import entity.*;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        AppContext.init();
        try {
            List<Genre> genres = GenreDaoImpl.getInstance().getAll();
            for (Genre g : genres) {
                System.out.println(g.getName());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
