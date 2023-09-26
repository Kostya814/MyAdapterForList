package com.example.listadapterforlake;

public class Lake {
    private String Name;
    private int Age;
    private double square;
    private double length;

    public Lake(String name, int age, double square, double length) {
        Name = name;
        Age = age;
        this.square = square;
        this.length = length;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}