package com.example.proj2.model;

import java.util.Date;

public class Order implements Comparable<Order> {

    private String customerName;
    private String customerPhone;
    private Car car;
    private String status;
    private String date;


    public Order(String customerName, String customerPhone, Car car, String status, String date) {
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.car = car;
        this.status = status;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" + "customerName='" + customerName + '\'' + ", customerPhone='" + customerPhone + '\'' + ", car=" + car + ", status='" + status + '\'' + ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(Order o) {
        return customerName.compareTo(o.getCustomerName());
    }
}
