package com.dr.List;

public abstract class AbstractList<E> implements List<E>{
    protected int size;

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    /**
     * 元素的数量
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    /**
     * 添加元素到队尾
     * @param element
     */
    @Override
    public void add(E element) {
        add(size, element);
    }
}
