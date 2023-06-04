package com.example.proj2.datastructure;

public class CursorArray<T extends Comparable<T>> {

    int index;
    CNode<T>[] cursorArray = new CNode[index];

    public CursorArray() {
        // cursorArray = new CNode[50];
        cursorArray = new CNode[10];
        initialization();

    }

    public CursorArray(int index) {

        cursorArray = new CNode[index];
        initialization();

    }

    public int initialization() {
        for (int i = 0; i < cursorArray.length - 1; i++)
            cursorArray[i] = new CNode<>(null, i + 1);
        cursorArray[cursorArray.length - 1] = new CNode<>(null, 0);
        return 0;
    }

    public int malloc() {
        int p = cursorArray[0].next;
        cursorArray[0].next = cursorArray[p].next;
        return p;
    }

    public void free(int p) {
        cursorArray[p] = new CNode(null, cursorArray[0].next);
        cursorArray[0].next = p;
    }

    public boolean isNull(int l) {
        return cursorArray[l] == null;
    }

    public boolean isEmpty(int l) {
        return cursorArray[l].next == 0;
    }

    public boolean isLast(int p) {
        return cursorArray[p].next == 0;
    }

    public int createList() {
        int l = malloc();
        if (l == 0)
            System.out.println("Error: Out of space!!!");
        else
            cursorArray[l] = new CNode("-", 0);
        return l;
    }

    public void insertAtHead(T data, int l) {
        if (isNull(l)) // list not created
            return;
        int p = malloc();
        if (p != 0) {
            cursorArray[p] = new CNode(data, cursorArray[l].next);
            cursorArray[l].next = p;
        } else
            System.out.println("Error: Out of space!!!");
    }
    public T deleteAtHead(int l) {
        T temp = null;
        if (!isEmpty(l)) {
            int c = cursorArray[l].next;
            temp = cursorArray[c].date;
            cursorArray[l].next = cursorArray[c].next;
            free(c);
        }
        return temp;
    }
    public boolean hasFree() {
        return cursorArray[0].next != 0;
    }
    public T gethead(int e){
        int l = cursorArray[e].next;
        T temp = cursorArray[l].date;
        return temp;
    }

    public void traversList(int l) {
        System.out.print("list_" + l + "-->");
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].next;
            System.out.print(cursorArray[l] + "-->");
        }
        System.out.println("null");
    }

    public int find(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            l = cursorArray[l].next;
            if (cursorArray[l].getDate().equals(data))
                return l;
        }
        return -1; // not found
    }

    public int findPrevious(T data, int l) {
        while (!isNull(l) && !isEmpty(l)) {
            if (cursorArray[cursorArray[l].next].getDate().equals(data))
                return l;
            l = cursorArray[l].next;
        }
        return -1; // not found
    }

    public CNode delete(T data, int l) {
        int p = findPrevious(data, l);
        if (p != -1) {
            int c = cursorArray[p].next;
            CNode temp = cursorArray[c];
            cursorArray[p].next = temp.next;
            free(c);
        }
        return null;
    }

    public void insertAtLast(T date, int l) {
        if (!isNull(l)) {
            int p = malloc();
            if (p != 0) {
                while (!isLast(l)) {
                    l = cursorArray[l].next;
                }
                cursorArray[p] = new CNode<>(date, 0);
                cursorArray[l].next = p;

            }
        }
    }
    public int length(int l){
        int count = 0;
        if(!isNull(l)){
            while(cursorArray[l].next!=0){
                count++;
                l=cursorArray[l].next;
            }
        }

        return count;
    }
    public T deleteAtLast(T date,int l){
        int p = malloc();
        while(isNull(l)&&isEmpty(p)){
            p =l;
            l=cursorArray[l].next;
        }if( l == p){//empty list
            return null;
        }
        T temp = cursorArray[l].date;
        cursorArray[p].next=0;
        free(1);
        return temp;
    }
    public void insertSorted(T date,int l){
        int p = malloc();
        if(!isNull(l)){
            if(p!=0){
                if(cursorArray[l].getNext()==0){
                    insertAtHead(date, l);
                }else{
                    while(cursorArray[cursorArray[l].getNext()].getDate().compareTo(date)<0){
                        l = cursorArray[l].next;
                    }
                    cursorArray[p]=new CNode<>(date, cursorArray[l].next);
                    cursorArray[l].next=p;
                }
            }
        }
    }
    public void removeList(int l) {
        while (!isEmpty(l))
            deleteAtHead(l);
        free(l);
    }
}