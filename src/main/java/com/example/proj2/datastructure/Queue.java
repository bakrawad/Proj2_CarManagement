package com.example.proj2.datastructure;

public class Queue<T extends Comparable<T>> implements QInterFace<T> {
    int front = -1;
    int back = -1;
    int count = 0;
    Comparable[] arr;


    public Queue(int size) {
        arr = new Comparable [size];
    }


    @Override
    public void enqueue(T data) {
        if(count <arr.length){
            back = (++back) % arr.length;
            arr[back]= data;
            ++count;
            if (count==1) {
                front = back;
            }
        }

    }

    @Override
    public T dequeue() {
        if(count > 0){
            T temp = (T) arr[front];
            front = (++front)%arr.length;
            count--;
            if(count==0){
                front=back=-1;
            }
            return temp;
        }
        return null;
    }

    @Override
    public T getFront() {
        if(front>=0){
            return (T) arr[front];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        if(count>0){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        front =-1;
        back =-1;
        count =0;
        arr = (T[]) new Object [arr.length];
    }

}