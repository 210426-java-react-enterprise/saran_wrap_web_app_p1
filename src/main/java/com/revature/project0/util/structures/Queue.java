package com.revature.project0.util.structures;

public interface Queue<T> extends Collection<T>{
    T poll();
    T peek();
}
