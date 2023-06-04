package com.example.proj2;

import com.example.proj2.datastructure.StackCa;

public class Test {

    public static void main(String[] args) {
        StackCa<Integer> ca = new StackCa<>(5);

        ca.puch(5);
        ca.puch(6);
        ca.puch(7);
        ca.puch(8);

        System.out.println(ca.isEmpty());

        System.out.println(ca.pop());
        System.out.println(ca.pop());
        System.out.println(ca.pop());
        System.out.println(ca.pop());

        System.out.println(ca.isEmpty());
    }

}
