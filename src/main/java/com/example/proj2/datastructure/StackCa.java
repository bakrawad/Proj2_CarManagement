package com.example.proj2.datastructure;

public class StackCa<T extends Comparable<T>> implements StackInterface<T> {

    CursorArray<T> list ;
    int index;

    public StackCa(int index) {
        list = new CursorArray<>(index + 1);
        this.index = list.createList();
    }

    @Override
    public void puch(T data) {
        if (list.hasFree())
            list.insertAtHead(data, index);
        else
            System.out.println("Stack OverFlow");
    }

    @Override
    public T pop() {
        if (!list.isEmpty(index))
            return list.deleteAtHead(index);
        else
            return null;
    }

    @Override
    public T peek() {
        if (!isEmpty())
            return list.gethead(index);
        else
            return null;

    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty(index);
    }


    @Override
    public void clear() {
        list.removeList(index);
    }

}