package benchmark;

import java.util.Map;
import java.util.Random;

public class HashMapTest {

    private long time;
    private Integer number = 10;

    public Object add(Map<Integer, Object> map, int size, String type) {
        if (type.equals("Integer") || type.equals("Double") || type.equals("Long")) {
            long start = System.currentTimeMillis();
            for(int i = 0; i<size; i++) map.put(i, number);
            long finish = System.currentTimeMillis();
            time = finish - start;
        }
        if (type.equals("String")) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) map.put(i, String.valueOf(i));
            long finish = System.currentTimeMillis();
            time = finish - start;
        }
        return time;
    }

    public long remove(Map<Integer, Object> collection) {
        long start = System.currentTimeMillis();
        for (int i = collection.size() - 1; i >= 0; i--) collection.remove(i);
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    public Object searchRandom(Map<Integer, Object> map) {
        Random random = new Random();
        Object object = new Object();
        int  n = random.nextInt(map.size()) + 1;
        for (int i =0; i<map.size(); i++) {
            if (n == i) {
                object = i;
                break;
            }
        }
        return object;
    }

    public SearchEntity searchThreeElements(Map<Integer, Object> map) {
        int first = (int) map.get(0);
        int middle = (int) map.get(map.size()/2);
        int last = (int) map.get(map.size() - 1);
        SearchEntity entity = new SearchEntity(first, middle, last);
        return entity;
    }
}
