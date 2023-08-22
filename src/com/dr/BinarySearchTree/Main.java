package com.dr.BinarySearchTree;

import com.dr.Tree.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    private static class PersonComparator implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p1.getAge() - p2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        public int compare(Person p1, Person p2) {
            return p2.getAge() - p1.getAge();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] array = {5, 7, 6, 8, 9, 3, 1, 2, 4};
        for (int i = 0; i < array.length; i++) {
            bst.add(array[i]);
        }
        BinaryTrees.println(bst);
        bst.remove(5);
        BinaryTrees.println(bst);
    }
}
