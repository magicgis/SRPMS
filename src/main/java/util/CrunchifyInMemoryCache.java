package util;

import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.LRUMap;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guofan on 2015/5/9.
 */
@Singleton
public class CrunchifyInMemoryCache<K, T> {
    private long timeToLive;
    private LRUMap crunchifyCacheMap;

    protected class CrunchifyCacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CrunchifyCacheObject(T value) {
            this.value = value;
        }
    }

    /**
     * @param crunchifyTimeToLive    生存时间
     * @param crunchifyTimerInterval 间隔时间(定期清理时间)
     * @param maxItems               元素数量限制
     */
    public CrunchifyInMemoryCache(long crunchifyTimeToLive, final long crunchifyTimerInterval, int maxItems) {
        this.timeToLive = crunchifyTimeToLive * 1000;

        crunchifyCacheMap = new LRUMap(maxItems);

        if (timeToLive >= 0 && crunchifyTimerInterval >= 0) {

            Thread t = new Thread(new Runnable() {
                public void run() {
                    if (crunchifyTimerInterval == 0 && timeToLive == 0) {
                    } else {
                        while (true) {
                            try {
                                Thread.sleep(crunchifyTimerInterval * 1000);
                            } catch (InterruptedException ex) {
                            }
                            cleanup();
                        }
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        }
    }

    /**
     * 放入key
     *
     * @param key   key
     * @param value value
     */
    public void put(K key, T value) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.put(key, new CrunchifyCacheObject(value));
        }
    }

    /**
     * 取出key
     *
     * @param key key
     * @return value
     */
    @SuppressWarnings("unchecked")
    public T get(K key) {
        synchronized (crunchifyCacheMap) {
            CrunchifyCacheObject c = (CrunchifyCacheObject) crunchifyCacheMap.get(key);

            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    /**
     * 删除key
     *
     * @param key key
     */
    public void remove(K key) {
        synchronized (crunchifyCacheMap) {
            crunchifyCacheMap.remove(key);
        }
    }

    /**
     * 获取size
     *
     * @return 长度
     */
    public int size() {
        synchronized (crunchifyCacheMap) {
            return crunchifyCacheMap.size();
        }
    }


    @SuppressWarnings("unchecked")
    public Map getAll() {
        ArrayList<K> getKey = null;
        Map<K, T> ans = null;
        synchronized (crunchifyCacheMap) {
            MapIterator itr = crunchifyCacheMap.mapIterator();
            K key = null;
            CrunchifyCacheObject value = null;
            ans = new HashMap<>();
            while (itr.hasNext()) {
                key = (K) itr.next();
                value = (CrunchifyCacheObject) itr.getValue();
                if (value != null) {
                    ans.put(key, (T) value);
                }
            }
        }
        return ans;
    }

    @SuppressWarnings("unchecked")
    public void empty() {
        synchronized (crunchifyCacheMap) {
            MapIterator itr = crunchifyCacheMap.mapIterator();
            K key = null;
            while (itr.hasNext()) {
                key = (K) itr.next();
                remove(key);
            }
        }
    }

    /**
     * 清理过期数据
     */
    @SuppressWarnings("unchecked")
    public void cleanup() {
        /*获取当前时间*/
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey = null;

        synchronized (crunchifyCacheMap) {
            MapIterator itr = crunchifyCacheMap.mapIterator();

            deleteKey = new ArrayList<K>((crunchifyCacheMap.size() / 2) + 1);
            K key = null;
            CrunchifyCacheObject c = null;

            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (CrunchifyCacheObject) itr.getValue();
                /* c不为空并且现在时间大于存活时间+最后一次读取时间时，删除*/
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }

        for (K key : deleteKey) {
            synchronized (crunchifyCacheMap) {
                crunchifyCacheMap.remove(key);
            }

            Thread.yield();
        }
    }
}
