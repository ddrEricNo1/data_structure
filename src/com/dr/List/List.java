package com.dr.List;

public interface List<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    void clear();
    int size();
    boolean isEmpty();
    boolean contains(E element);
    /**
     * 获取index位置元素
     *
     * @param index
     * @return
     */
    E get(int index);

    /**
     * 设置index位置元素
     * @param index
     * @param element
     * @return 原来的元素
     */
    E set(int index, E element);

    /**
     * 在index位置插入一个元素
     * @param index
     * @param element
     */
    void add(int index, E element);

    void add(E element);

    /**
     * 删除index位置元素
     * @param index
     * @return
     */
    E remove(int index);

    /**
     * 查看元素索引
     * @param element
     * @return
     */
    int indexOf(E element);
}
