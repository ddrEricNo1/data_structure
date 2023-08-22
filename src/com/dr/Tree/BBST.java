/**
 * BBST: balanced binary search tree
 * 继承自BST，为AVL Tree和红黑树提供旋转操作
 */
package com.dr.Tree;

import java.util.Comparator;

import com.dr.BinarySearchTree.BinarySearchTree;

public class BBST<E> extends BinarySearchTree<E> {
    public BBST() {
        this(null);
    }

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        // 让parent成为子树根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if(grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            // grand可能是根节点
            root = parent;
        }

        if (child != null) {
            child.parent = grand;
        }

        grand.parent = parent;
    }

    /**
     *
     * @param r 当前子树的根节点
     * @param a 当前子树的a节点
     * @param b 当前子树的b节点
     * @param c 当前子树的c节点
     * @param d 当前子树的d节点
     * @param e 当前子树的e节点
     * @param f 当前子树的f节点
     * @param g 当前子树的g节点
     */
    protected void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        if (a != null) {
            a.parent = b;
        }

        b.right = c;
        if (c != null) {
            c.parent = b;
        }

        // e-f-g
        f.left = e;
        if (e != null) {
            e.parent = f;
        }

        f.right = g;
        if (g != null) {
            g.parent = f;
        }

        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
