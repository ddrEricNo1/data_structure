package com.dr.BinarySearchTree;

public class Person implements Comparable<Person> {
    private int age;
    public Person(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
        return age - person.age;
    }

    public int getAge() {
        return age;
    }

    public void setAge() {
        this.age = age;
    }

    @Override
    public String toString() {
        return "" + this.age;
    }
}
