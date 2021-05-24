package com.revature.project1.util.structures;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class HashSet<T> implements Set<T>{
    private final Map<T, Object> map;

    public HashSet() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(Class<T> clazz) {
        Set<T> keys = this.map.keySet();
        T[] keyArr = (T[]) Array.newInstance(clazz, size());
        int ctr = 0;
        for (T t : this) {
            keyArr[ctr] = t;
        }
        return keyArr;
    }

    @Override
    public boolean add(T data) {
        return this.map.put(data, data.hashCode()) == null;
    }

    @Override
    public boolean contains(T data) {
        return this.map.containsKey(data);
    }

    // TODO implement me!
    @Override
    public boolean remove(T data) {
        return false;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    // TODO implement me!
    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            T[] asArray = toArray();
            int currentPosition = 0;

            @Override
            public boolean hasNext() {
                return currentPosition < asArray.length;
            }

            @Override
            public T next() {

                T data = null;

                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }

                while(this.hasNext()) {
                    data = asArray[currentPosition++];
                }

                return data;
            }
        };
    }
}
