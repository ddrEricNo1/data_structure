package com.dr.Set;

public class Main {
    public static void main(String[] args) {
//        Set<Integer> listSet = new ListSet<>();
//        listSet.add(10);
//        listSet.add(11);
//        listSet.add(12);
//        listSet.add(10);
//        listSet.traversal(new Set.Visitor<Integer>() {
//
//            @Override
//            public boolean visit(Integer element) {
//                System.out.println(element);
//                return false;
//            }
//        });
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(12);
        treeSet.add(11);
        treeSet.add(10);
        treeSet.add(12);
        treeSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
