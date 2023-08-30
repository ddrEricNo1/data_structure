package com.dr.Graph;

public interface Graph<V, E> {
    // 返回边个数
    int edgesSize();
    // 返回顶点个数
    int verticesSize();
    // 添加顶点
    void addVertex(V v);
    void addEdge(V from, V to);
    void addEdge(V from, V to, E weight);
    void removeVertex(V v);
    void removeEdge(V from, V to);
    // 宽度优先遍历
    void bfs(V begin);
}
