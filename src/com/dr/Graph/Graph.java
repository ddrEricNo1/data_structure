package com.dr.Graph;

import java.util.List;
import java.util.Set;

public abstract class Graph<V, E> {
    public interface WeightManager<E> {
        int compare(E w1, E w2);
        E add(E w1, E e2);
    }

    protected WeightManager<E> weightManager;
    public Graph() {}
    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }
    // 返回边个数
    public abstract int edgesSize();
    // 返回顶点个数
    public abstract int verticesSize();
    // 添加顶点
    public abstract void addVertex(V v);
    public abstract void addEdge(V from, V to);
    public abstract void addEdge(V from, V to, E weight);
    public abstract void removeVertex(V v);
    public abstract void removeEdge(V from, V to);
    // 宽度优先遍历
    public abstract void bfs(V begin, VertexVisitor<V> visitor);
    // 深度优先遍历
    public abstract void dfs(V begin, VertexVisitor<V> visitor);
    interface VertexVisitor<V> {
        boolean visit(V v);
    }
    // 拓扑排序
    public abstract List<V> topologicalSort();
    // 最小生成树
    public abstract Set<EdgeInfo<V, E>> mst();
    // 边信息
    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
}
