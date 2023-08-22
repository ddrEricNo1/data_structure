package com.dr.AVLTree;

import com.dr.Tree.BBST;

import java.util.Comparator;

public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        super(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            }else {
                // 恢复高度
                reBalance(node);
                break;
            }
        }
    }

    private void reBalance(Node<E> grand) {
        Node<E> parentNode = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parentNode).tallerChild();
        if (parentNode.isLeftChild()) {
            if (node.isLeftChild()) {
                // LL
                rotateRight(grand);
            } else {
                // LR
                rotateLeft(parentNode);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                // RL
                rotateRight(parentNode);
                rotateLeft(grand);
            } else {
                // RR
                rotateLeft(grand);
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);
        
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r,
                        Node<E> a, Node<E> b, Node<E> c,
                        Node<E> d,
                        Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    // 统一旋转操作，不需要判断LL，RR等情况，统一进行处理
    private void reBalance2(Node<E> grand) {
        Node<E> parentNode = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parentNode).tallerChild();
        if (parentNode.isLeftChild()) {
            if (node.isLeftChild()) {
                rotate(grand, node.left,node, node.right, parentNode, parentNode.right, grand, grand.right);
            } else {
                rotate(grand, parentNode.left, parentNode, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                rotate(grand, grand.left, grand, node.left, node, node.right, parentNode, parentNode.right);
            } else {
                rotate(grand, grand.left, grand, parentNode.left, parentNode, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                // 更新高度
                updateHeight(node);
            }else {
                // 恢复平衡
                reBalance(node);
            }
        }
    }

    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left : right;
        }
    }
}
