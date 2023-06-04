package com.example.proj2.datastructure;

public class CNode <T extends Comparable<T>>{
        T date;
        int next;

        public CNode(T date, int next) {

            this.date = date;
            this.next = next;
        }

        public T getDate() {
            return date;
        }

        public void setDate(T date) {
            this.date = date;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node [date=" + date + ", next=" + next + "]";
        }
}
