package com.dr.AVLTree;

import com.dr.Tree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add(i);
        }
        BinaryTrees.println(tree);
    }
}
