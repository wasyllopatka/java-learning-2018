package benchmark;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/*
    This class represent implementation of ArrayList methods
 */
public class ArrayListTest implements ITest {
    private long time;

    // Add elements to ArrayList
    // Return elapsed time
    @Override
    public Object add(Collection collection, int size, String type) {
        if (type.equals("Integer") || type.equals("Double") || type.equals("Long")) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                collection.add(i);
            }
            long finish = System.currentTimeMillis();
            time = finish - start;
        }
        if (type.equals("String")) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < size; i++) {
                collection.add(String.valueOf(i));
            }
            long finish = System.currentTimeMillis();
            time = finish - start;
        }
        return time;
    }

    // Remove all elements from array list
    // Return elapsed time
    @Override
    public long remove(Collection collection) {
        long start = System.currentTimeMillis();
        for (int i = collection.size() - 1; i >= 0; i--) collection.remove(i);
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    @Override
    public Object searchRandom(Collection collection) {
        Random random = new Random();
        Object object = new Object();
        int  n = random.nextInt(collection.size()) + 1;
        for (int i =0; i<collection.size(); i++) {
            if (n == i) {
                object = i;
                break;
            }
        }
        return object;
    }

    @Override
    public SearchEntity searchThreeElements(Collection collection) {
        List<Object> list = (List<Object>) collection;
        int first = (int) list.get(0);
        int middle = (int) list.get(collection.size()/2);
        int last = (int) list.get(collection.size()-1);
        SearchEntity entity = new SearchEntity(first, middle, last);
        return entity;
    }

}
