package com.dr.Graph;

import com.dr.Heap.AbstractHeap;
import com.dr.Tree.printer.BinaryTreeInfo;

import java.util.Collection;
import java.util.Comparator;

public class MinHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

//    public MinHeap(E[] elements, Comparator<E> comparator) {
//        super(comparator);
//        if (elements == null || elements.length == 0) {
//            this.elements = (E[])new Object[DEFAULT_CAPACITY];
//        } else {
//            int capacity = Math.max(DEFAULT_CAPACITY, elements.length);
//            this.elements = (E[])new Object[capacity];
//            for (int i = 0; i < capacity; i++) {
//                this.elements[i] = elements[i];
//            }
//        }
//    }

    public MinHeap(Collection<E> elements, Comparator<E> comparator) {
        this.comparator = comparator;
        size = elements == null ? 0 : elements.size();
        if (size == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            int capacity = Math.max(size, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            int i = 0;
            for (E element: elements) {
                this.elements[i++] = element;
            }
            heapify();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        // 判断容量大小
        ensureCapacity(size + 1);

        elements[size++] = element;
        siftUp(size - 1);
    }

    public void addAll(Collection<E> elements) {
        if (elements == null) return;
        for (E element: elements) {
            add(element);
        }
    }

    // 上滤操作
    private void siftUp(int index) {
//        E e = elements[index];
//        // index大于0判断是否有父节点
//        while (index > 0) {
//            // 获取父节点索引
//            int pIndex = (index - 1) >> 1;
//            E p = elements[pIndex];
//            if (compare(e, p) <= 0) {
//                return;
//            } else {
//                // 交换
//                E tmp = elements[index];
//                elements[index] = elements[pIndex];
//                elements[pIndex] = tmp;
//
//                index = pIndex;
//            }
//        }
        E e = elements[index];
        // index大于0判断是否有父节点
        while (index > 0) {
            // 获取父节点索引
            int pIndex = (index - 1) >> 1;
            E p = elements[pIndex];
            if (compare(e, p) <= 0) {
                break;
            } else {
                // 将父元素存储在index位置
                elements[index] = p;
                // 重新赋值index
                index = pIndex;
            }
        }
        elements[index] = e;
    }

    // 下滤操作
    private void siftDown(int index) {
        int half = size >> 1;
        E element = elements[index];
        // 保证index是非叶子节点
        while (index < half) {
            // index的节点有两种情况
            // 1. 只有左子节点
            // 2. 同时有左右子节点

            // 默认为左子节点的索引
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 右子节点索引
            int rightIndex = childIndex + 1;

            // 选出左右子节点较大的
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                // 右子节点是在合理的范围之内并且右边节点比左边大
                childIndex = rightIndex;
                child = elements[rightIndex];
            }
            if (compare(element, child) >= 0) {
                break;
            } else {
                // 将子节点存放到index位置
                elements[index] = child;
                index = childIndex;
            }
        }
        elements[index] = element;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    // 删除堆顶元素
    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return root;
    }

    // 删除堆顶元素并添加新元素
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);
        E root = null;
	    if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    // heapify成堆操作
    public void heapify(E[] arr) {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    public void heapify() {
        this.heapify(this.elements);
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println(oldCapacity + "扩容为" + newCapacity);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element cannot be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        Integer index = (Integer)node;
        return elements[index];
    }
}
