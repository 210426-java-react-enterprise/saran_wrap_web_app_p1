package com.revature.project0.util.structures;
import java.util.Arrays;

public class HashMap<K,V> implements Map<K,V> {
    private int size;
    private final int DEFAULT_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    private Map.Entry<K, V>[] entries = new Map.Entry[DEFAULT_CAPACITY];

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (entries[i].getKey() == null) {
                if (entries[i].getKey() == key) {
                    return true;
                }
            } else if (entries[i].getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(V key) {
        return false;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {

            if (entries[i].getKey() == null) {
                if (entries[i].getKey() == key) {
                    return entries[i].getValue();
                }
            } else if (entries[i].getKey().equals(key)) {
                return entries[i].getValue();
            }

        }
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K key, V value) {

        V previousValue = null;

        boolean wasInserted = true;
        for (int i = 0; i < size; i++) {

            if (entries[i].getKey() == null) {
                if (entries[i].getKey() == key) {
                    previousValue = entries[i].getValue();
                    entries[i].setValue(value);
                    wasInserted = false;
                    break;
                }
            } else if (entries[i].getKey().equals(key)) {
                previousValue = entries[i].getValue();
                entries[i].setValue(value);
                wasInserted = false;
                break;
            }

        }

        if (wasInserted) {
            ensureCapacity();
            entries[size++] = new Map.Entry<K,V>() {

                K key;
                V value;

                @Override
                public K getKey() {
                    return key;
                }

                @Override
                public V getValue() {
                    return value;
                }

                @Override
                public V setValue(V value) {
                    V oldValue = this.value;
                    this.value = value;
                    return oldValue;
                }
            };
        }

        return previousValue;

    }

    public V remove(K key) {

        V removedValue = null;
        boolean wasRemoved = false;

        for (int i = 0; i < size; i++) {
            if (entries[i].getKey() == null) {
                if (entries[i].getKey() == key) {
                    removedValue = entries[i].getValue();
                    entries[i] = entries[size - 1];
                    size--;
                    wasRemoved = true;
                }
            } else if (entries[i].getKey().equals(key)) {
                removedValue = entries[i].getValue();
                entries[i] = entries[size - 1];
                size--;
                wasRemoved = true;
            }
        }

        if (wasRemoved) {
            condenseArray();
        }

        return removedValue;

    }

    public int size() {
        return size;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyList = new HashSet<>();
        for (int i = 0; i < size; i++) {
            keyList.add(entries[i].getKey());
        }
        return keyList;
    }

    @Override
    public Set<V> valueSet() {
        Set<V> valueSet = new HashSet<>();
        for (int i = 0; i < size; i++) {
            valueSet.add(entries[i].getValue());
        }
        return valueSet;
    }

    private void ensureCapacity() {
        if (size == entries.length) {
            entries = Arrays.copyOf(entries, entries.length * 2);
        }
    }

    private void condenseArray() {
        if (size * 2 < entries.length) {
            entries = Arrays.copyOf(entries, entries.length / 2);
        }
    }


}
