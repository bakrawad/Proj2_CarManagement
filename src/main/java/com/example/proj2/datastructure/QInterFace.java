package com.example.proj2.datastructure;

public interface QInterFace<T extends Comparable<T>> {
    void enqueue(T data);

    T dequeue();

    T getFront();

    boolean isEmpty();

    void clear();

}