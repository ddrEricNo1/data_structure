package com.dr.LinkedList;

import com.dr.List.AbstractList;


public class SingleLinkedList<E> extends AbstractList<E> {
    private int size;
    private Node<E> firstNode;
    private static final int ELEMENT_NOT_FOUND = -1;

    @Override
    public void clear() {
        size = 0;
        this.firstNode = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldValue = node.element;
        node.element = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        Node<E> prev = (index != 0) ? node(index - 1) : firstNode;
        Node<E> newNode = new Node<>(element, prev.next);
        prev.next = newNode;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E>node = firstNode;
        if (index == 0) {
            this.firstNode = firstNode.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = prev.next.next;   
        }
        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = firstNode;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            Node<E> node = firstNode;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element= element;
            this.next= next;
        }
    }

    /**
     * 获取index位置对应节点对象
     * @param index: index of the current node
     * @return: index位置节点元素
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
