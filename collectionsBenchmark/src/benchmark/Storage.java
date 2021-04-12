package benchmark;

import java.util.*;

/*  Singleton, that contains all collections,
    implements general methods,
    returns collections and additional objects
*/
public class Storage {
    private static Storage instance;

    private List<Object> list = new ArrayList<>();
    private Set<?> set = new HashSet<>();
    private Map<Integer, Object> map = new HashMap<>();

    private ArrayListTest arrayListTest = new ArrayListTest();
    private HashSetTest hashSetTest = new HashSetTest();
    private HashMapTest hashMapTest = new HashMapTest();

    private Object randomArrayListObject = new Object();
    private Object randomHashSetObject = new Object();
    private Object randomHashMapObject = new Object();

    private SearchEntity searchALEntity;
    private SearchEntity searchHSEntity;
    private SearchEntity searchHMEntity;

    private String lastType;
    private int lastSize;

    private Storage() {
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public Integer addToArrayList(int size, String type) {
        if (!list.isEmpty()) list.clear();
        setLastType(type);
        setLastSize(size);
        return Math.toIntExact((Long) arrayListTest.add(list, size, type));
    }

    public Integer addToHashSet(int size, String type) {
        if (!set.isEmpty()) set.clear();
        return Math.toIntExact((Long) hashSetTest.add(set, size, type));
    }

    public Integer addToHashMap(int size, String type) {
        if (!map.isEmpty()) map.clear();
        return Math.toIntExact((Long) hashMapTest.add(map, size, type));
    }

    public Integer removeFromArrayList() {
        return Math.toIntExact(arrayListTest.remove(list));
    }

    public Integer removeFromHashSet() {
        return Math.toIntExact(hashSetTest.remove(set));
    }

    public Integer removeFromHashMap() {
        return Math.toIntExact(hashMapTest.remove(map));
    }

    public Integer searchRandomInArrayList() {
        long start = System.currentTimeMillis();
        randomArrayListObject = arrayListTest.searchRandom(list);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    public Integer searchRandomInHashSet() {
        long start = System.currentTimeMillis();
        randomHashSetObject = hashSetTest.searchRandom(set);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    public Integer searchRandomInHashMap() {
        long start = System.currentTimeMillis();
        randomHashMapObject = hashMapTest.searchRandom(map);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    public Integer searchThreeInArrayList() {
        long start = System.currentTimeMillis();
        searchALEntity = arrayListTest.searchThreeElements(list);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    public Integer searchThreeInHashSet() {
        long start = System.currentTimeMillis();
        searchHSEntity = hashSetTest.searchThreeElements(set);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    public Integer searchThreeInHashMap() {
        long start = System.currentTimeMillis();
        searchHMEntity = hashMapTest.searchThreeElements(map);
        long finish = System.currentTimeMillis();
        return Math.toIntExact(finish - start);
    }

    // Getters and Setters

    public List<Object> getList() {
        return list;
    }

    public Set<?> getSet() {
        return set;
    }

    public Map<Integer, Object> getMap() {
        return map;
    }

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }

    public int getLastSize() {
        return lastSize;
    }

    public void setLastSize(int lastSize) {
        this.lastSize = lastSize;
    }

    public Object getRandomArrayListObject() {
        return randomArrayListObject;
    }

    public Object getRandomHashSetObject() {
        return randomHashSetObject;
    }

    public Object getRandomHashMapObject() {
        return randomHashMapObject;
    }

    public SearchEntity getSearchALEntity() {
        return searchALEntity;
    }

    public SearchEntity getSearchHSEntity() {
        return searchHSEntity;
    }

    public SearchEntity getSearchHMEntity() {
        return searchHMEntity;
    }
}
