package com.dr.Map;

import com.dr.Set.Set;

public interface Map <K, V> {
    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    void traversal(Visitor<K, V> vivitor);
    public abstract class Visitor<K, V> {

    }
}
