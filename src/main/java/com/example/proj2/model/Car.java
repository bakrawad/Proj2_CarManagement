package com.example.proj2.model;

public class Car implements Comparable<Car> {
    private Brand Brand;
    private String Model;
    private int Year;
    private String Color;
    private String Price;

    public Car(String brand, String model, int year, String color, String price) {
        Brand = new Brand(brand);
        Model = model;
        Year = year;
        Color = color;
        Price = price;
    }

    public Brand getBrand() {
        return Brand;
    }

    public void setBrand(Brand brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    @Override
    public String toString() {
        return   Brand.brand + ' ' + Model + ' ' + Year + " " + Color + ' ' + Price;
    }

    @Override
    public int compareTo(Car o) {
        return Integer.compare(getYear(), o.getYear());
    }

    public boolean equals(Car obj) {
        if(!obj.getBrand().getBrand().trim().equals(getBrand().getBrand().trim()))
            return false;
        if(!obj.getModel().trim().equals(getModel().trim()))
            return false;
        if(obj.getYear() != getYear())
            return false;
        if(!obj.getColor().trim().equals(getColor().trim()))
            return false;
        if(!obj.getPrice().trim().equals(getPrice().trim()))
            return false;

        return true;
    }

}

