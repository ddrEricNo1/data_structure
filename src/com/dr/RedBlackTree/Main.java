package com.dr.RedBlackTree;

import com.dr.RedBlackTree.printer.BinaryTrees;
import com.dr.RedBlackTree.tree.RBTree;

public class Main {

    static void test1() {
        Integer[] data = {
                55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50
        };
        RBTree<Integer> rb = new RBTree<>();

        for (int i = 0; i < data.length; i++) {
            rb.add(data[i]);
        }

        BinaryTrees.println(rb);

        for (int i = 0; i < data.length; i++) {
            rb.remove(data[i]);
            BinaryTrees.println(rb);
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
