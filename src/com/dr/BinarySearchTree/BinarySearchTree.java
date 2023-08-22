package com.dr.BinarySearchTree;

import com.dr.Tree.BinaryTree;

import java.util.Comparator;

public class BinarySearchTree<E> extends BinaryTree<E> {
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            // 添加第一个节点
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        // 添加的不是第一个节点
        // 找到父节点
        Node<E> node = root;
        Node<E> parent = null;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 相等
                node.element = element;
                return;
            }
        }

        Node<E> newNode = createNode(element, parent);
        // 看看插入到父节点的哪个位置
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        // 新添加节点之后的处理
        afterAdd(newNode);
    }

    public void remove(E element) {
        remove(node(element));
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) {
            return;
        }
        size--;

        // 度为2
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            // 删除后继节点
            node = s;
        }

        // 删除node节点
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            // node是度为1的节点
            replacement.parent = node.parent;
            // 更改parent的left和right指向
            if (node.parent == null) {
                // node为度是1的节点，并且是根节点
                root = replacement;
            } else {
                if (node == node.parent.left) {
                    node.parent.left = replacement;
                } else {
                    node.parent.right = replacement;
                }
            }

            // 删除节点之后的处理
            afterRemove(replacement);
        } else if(node.parent == null) {
            // node为根节点，且为叶子节点
            root = null;
            afterRemove(node);
        } else {
            // node为叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            afterRemove(node);
        }
    }

    /**
     *
     * @param node 被删除的节点
     */
    protected void afterRemove(Node<E> node) {}

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) {
                return node;
            } else if(cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     *
     * @param e1
     * @param e2
     * @return 返回值等于0,代表e1等于e2,大于0代表e1大于e2,小于0表示e1小于e2
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 比较器为空
        return ((Comparable<E>)e1).compareTo(e2);
    }

    /**
     * 添加node之后的调整
     * @param node 当前添加的node
     */
    protected void afterAdd(Node<E> node) {}

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }
}
