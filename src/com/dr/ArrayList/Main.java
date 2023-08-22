package com.dr.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(99);
        list.add(88);
        list.add(77);
        list.add(66);
        list.add(55);
        list.add(list.size(), 100);
        list.set(list.size() - 1, 0);
        System.out.println(list);
    }
}
