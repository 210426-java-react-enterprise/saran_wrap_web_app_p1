package com.revature.project1.util.structures;

public interface Set<T> extends Collection<T>{
    boolean add(T data);
    boolean contains(T data);
    boolean remove(T data);
    int size();
    T[] toArray();
}
