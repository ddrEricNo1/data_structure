package com.dr.LinkedList;


import com.dr.List.List;

public class Main {
    static void testList(List<Integer> list) {
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        list.add(0, 55);
        list.add(2, 66);
        list.add(list.size(), 77);

        list.remove(0);
        list.remove(2);
        list.remove(list.size() - 1);

        System.out.println(list);
    }
    public static void main(String[] args) {
        testList(new DoubleLinkedList<>());
    }
}
