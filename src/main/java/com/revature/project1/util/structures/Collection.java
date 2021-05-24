package com.revature.project1.util.structures;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public interface Collection<T> extends Iterable<T> {
    boolean add(T data);
    boolean contains(T data);
    boolean isEmpty();
    boolean remove(T data);
    int size();

    default Stream<T> stream(){
        return StreamSupport.stream(this.spliterator(),false);
    }
    default Stream<T> parallelStream(){
        return StreamSupport.stream(this.spliterator(),true);
    }

}
