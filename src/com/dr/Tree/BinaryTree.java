package com.dr.Tree;

import com.dr.BinarySearchTree.BinarySearchTree;
import com.dr.Tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public void preorderTraversal() {
        preorderTraversal(root);
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public void levelOrder(BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (visitor.visit(node.element)) {
                return;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public void preOrder(BinarySearchTree.Visitor<E> visitor) {
        preOrder(root, visitor);
    }

    protected void preOrder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }
        if (visitor.stop) return;
        visitor.visit(node.element);
        preOrder(node.left, visitor);
        preOrder(node.right, visitor);
    }

    public void inOrder(BinarySearchTree.Visitor<E> visitor) {
        inOrder(root, visitor);
    }

    protected void inOrder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }
        inOrder(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.element);
        inOrder(node.right, visitor);
    }

    public void postOrder(BinarySearchTree.Visitor<E> visitor) {
        postOrder(root, visitor);
    }

    protected void postOrder(Node<E> node, BinarySearchTree.Visitor<E> visitor) {
        if (root == null || visitor.stop) {
            return;
        }
        postOrder(node.left, visitor);
        postOrder(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);

    }

    /**
     *
     * @return 返回二叉树的高度
     */
    public int height() {
        return height(root);
    }

    /**
     *
     * @param node
     * @return 获取某个节点的高度(递归的方式)
     */
    protected int height(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * @return 获取某个节点的高度,迭代的方式
     */
    public int height2() {
        if (root == null) {
            return 0;
        }

        int height = 0;

        // 存储每一层的元素数量
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    // 判断是否为完全二叉树
    public boolean isComplete(Node<E> root) {
        if (root == null) {
            return false;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (leaf && !node.isLeaf()) {
                return false;
            }
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return true;
    }

    protected Node<E> invertTree(Node<E> node) {
        if (node == null) {
            return node;
        }
        Node<E> tmp = node.right;
        node.right = node.left;
        node.left = tmp;
        invertTree(node.left);
        invertTree(node.right);
        return node;
    }

    public static abstract class Visitor<E> {
        boolean stop;
        /**
         *
         * @param element
         * @return 如果返回true，则停止
         */
        abstract boolean visit(E element);
    }

    protected void preorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.element);
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    protected void inorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    protected void postorderTraversal(Node<E> node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    // 前驱节点
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.left != null) {
            Node<E> p = node.left;
            while (p.right != null) {
                p = p.right;
            }
            return p;
        } else {
            while ((node.parent != null) && (node == node.parent.left)) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    // 后继节点
    protected Node<E> successor(Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        } else {
            while ((node.parent != null) && (node == node.parent.right)) {
                node = node.parent;
            }
            return node.parent;
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object string(Object node) {
        return node;
    }

    protected static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        /**
         *
         * @return 返回兄弟节点
         */
        public Node<E> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
