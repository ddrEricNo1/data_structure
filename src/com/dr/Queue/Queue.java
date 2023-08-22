package com.dr.Queue;

import com.dr.LinkedList.DoubleLinkedList;
import com.dr.List.List;

public class Queue<E> {
    private List<E> list = new DoubleLinkedList<>();
    
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public void clear() {
        list.clear();
    }
}
