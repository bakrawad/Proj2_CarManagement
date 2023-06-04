package com.example.proj2.datastructure;

public interface StackInterface<T extends Comparable<T>> {
    public void puch(T data);
    public T pop();
    public T peek();
    public boolean isEmpty();
    public void clear();
}