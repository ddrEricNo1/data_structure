package com.dr.UnionFind;
/**
 * 通用并查集
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericUnionFind<V> {
    // 自定义对象与Node一一对应，方便根据V找到对应的node
    private Map<V, Node<V>> nodes = new HashMap<>();
    public void makeSet(V v) {
        if (nodes.containsKey(v)) return;
        nodes.put(v, new Node<>(v));
    }

    // 找到v对应的节点的根节点
    public Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }

        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        return node == null ? null: node.value;
    }

    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) return;
        if (Objects.equals(p1, p2)) return;
        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank += 1;
        }
    }

    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private static class Node<V> {
        V value;
        // 默认parent指针指向自己
        Node<V> parent = this;
        int rank = 1;
        public Node(V v) {
            this.value = v;
        }
    }
}
