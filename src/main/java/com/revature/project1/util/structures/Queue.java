package com.revature.project1.util.structures;

public interface Queue<T> extends Collection<T>{
    T poll();
    T peek();
}
