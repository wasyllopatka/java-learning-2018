package benchmark;

import java.util.Collection;
/*
    This interface contains methods for collections, interfaces that implements Collection class.
    Remove method work correctly only for numbers, not for Objects.
    Link: https://stackoverflow.com/questions/104799/why-arent-java-collections-remove-methods-generic
 */

public interface ITest<T> {

    T add(Collection collection, int size, String type);

    long remove(Collection collection);

    T searchRandom(Collection collection);

    SearchEntity searchThreeElements(Collection collection);
}
