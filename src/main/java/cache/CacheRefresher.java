package cache;

import dao.BaseDao;
import entity.*;
import listener.UpdateDBEvent;
import listener.UpdateDBListener;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class CacheRefresher extends TimerTask implements UpdateDBListener {

    private static CacheRefresher INSTANCE;

    private Map<String, Boolean> updateMap;

    /**
        Put entity class in the updateMap to enable refresh cache for this entity.
     */
    private CacheRefresher() {
        BaseDao.addUpdateDBEventListener(this);
        updateMap = new HashMap<>();
        setUpdateEntities(Book.class, Account.class, Genre.class, Author.class, Language.class);
    }

    public static CacheRefresher getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CacheRefresher();
        }
        return INSTANCE;
    }

    @Override
    public void run() {
        EntityCache cache = EntityCache.getInstance();
        for (Map.Entry<String, Boolean> updateMarker : updateMap.entrySet()) {
            if (updateMarker.getValue()) {
                updateMarker.setValue(false);
                cache.eraseCache(updateMarker.getKey());
            }
        }
    }

    @Override
    public void onDBUpdate(UpdateDBEvent event) {
        String name = event.getMessage();
        if (updateMap.containsKey(name) && !updateMap.get(name)) {
            updateMap.replace(name, true);
        }
    }

    private void setUpdateEntities(Class<? extends BaseEntity> ... tClasses) {
        for (Class<? extends BaseEntity> tClass : tClasses) {
            updateMap.put(tClass.getSimpleName(), false);
        }
    }
}
