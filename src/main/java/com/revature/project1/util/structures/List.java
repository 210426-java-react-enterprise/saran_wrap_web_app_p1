package com.revature.project1.util.structures;

public interface List<T> extends Collection<T> {
    //all fields declared within interfaces are implicitly
    //public, static, and final

    //all method stubs declared within interfaces are implicity
    //public and abstract
  T get(int index);

    /*
        Interfaces
            - act as a contract for implementing classes (all concrete implementations must
              implement all abstract methods!)
            - do no have constructors
            - all fields declared within are implicitly public, static, and final
            - all method stubs declared within are implicitly public and abstract
            - methods with implementations are permitted in two ways: static and default methods
                + static methods cannot be overridden by implementing classes (but can be redeclared/shadowed)
                + default methods can be overridden by implementing classes (introduced in Java 8)
     */

}