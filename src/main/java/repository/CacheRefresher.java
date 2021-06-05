package repository;

import dao.BaseDao;
import listener.UpdateDBEvent;
import listener.UpdateDBListener;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class CacheRefresher extends TimerTask implements UpdateDBListener {

    private static CacheRefresher INSTANCE;

    private Map<String, Boolean> updateMap;

    private CacheRefresher() {
        BaseDao.addUpdateDBEventListener(this);
        updateMap = new HashMap<>();
        updateMap.put("Book", false);
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
}
