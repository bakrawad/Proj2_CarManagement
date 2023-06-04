package com.example.proj2.model;

import com.example.proj2.datastructure.SLinkedList;

public class Brand implements Comparable<Brand> {
    String brand;

    SLinkedList<Car> cars;

    public Brand(String brand) {
        this.brand = brand;
        cars = new SLinkedList<>();
    }

    public SLinkedList<Car> getCars() {
        return cars;
    }

    public void setCars(SLinkedList<Car> cars) {
        this.cars = cars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return brand;
    }

    @Override
    public int compareTo(Brand o) {
        return this.brand.compareTo(o.brand);
    }
}
