package com.revature.project1.util.structures;

public interface Map<K,V> {
    V get(K key);
    V put(K key, V value);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V key);
    boolean isEmpty();
    int size();
    Collection<V> values();
    Set<Map.Entry<K,V>> entrySet();
    Set<K> keySet();
    Set<V> valueSet();

    interface Entry<K,V>{
        K getKey();
        V getValue();
        V setValue(V value);
        boolean equals(Object var1);
        int hashCode();
    }
}
