package com.example.listadapterforlake;

import java.io.Serializable;

public class Lake implements Serializable {
    private String Name;
    private int Age;
    private int square;
    private int length;

    public Lake()
    {

    }
    public Lake(String name, int age, int square, int length) {
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

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}