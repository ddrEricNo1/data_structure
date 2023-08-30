package com.dr.Graph;

import java.util.*;

public class ListGraph<V, E> implements Graph<V, E> {
    // 存储所有的顶点, V与顶点的映射
    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    public void print() {
        // lambda表达式
        System.out.println("【顶点】");
        vertices.forEach((V key, Vertex<V, E> vertex) -> {
            System.out.println(key);
            System.out.println("out-----");
            System.out.println(vertex.outEdges);
            System.out.println("in-----");
            System.out.println(vertex.inEdges);
        });
        System.out.println("【边】");
        edges.forEach((Edge<V, E>edge) -> {
            System.out.println(edge);
        });
    }
    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        // 判断from to顶点是否存在
        // 起点
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        // 终点
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        Edge<V, E> newEdge = new Edge<>(fromVertex, toVertex);
        // 不管这条边曾经在不在outEdges中，都先删除再添加进去
        // 如果没有，不会remove，如果有，删除的是之前的对象
        // hashset判断相不相等的方法是调用equals，而equals的判断并不需要weight的参与
        if (fromVertex.outEdges.remove(newEdge)) {
            toVertex.inEdges.remove(newEdge);
            edges.remove(newEdge);
        }
        newEdge.weight = weight;
        fromVertex.outEdges.add(newEdge);
        toVertex.inEdges.add(newEdge);
        edges.add(newEdge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;

        // 删除顶点相关联的边
        // 迭代器
        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            // 将当前遍历到的元素从集合中删除,边遍历边删除
            iterator.remove();
            edges.remove(edge);
        }
        // 迭代器
        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            // 将当前遍历到的元素从集合中删除,边遍历边删除
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        Vertex<V, E> toVertex = vertices.get(to);
        if (fromVertex == null) {
            return;
        }
        if (toVertex == null) {
            return;
        }
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    // 宽度优先遍历很像二叉树的层序遍历
    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        // 已经遍历过的节点进入set
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(beginVertex);
        visitedVertices.add(beginVertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            System.out.println(vertex.value);
            for (Edge<V, E> edge: vertex.outEdges) {
                // 判断是否重复添加
                if (visitedVertices.contains(edge.to)) {
                    continue;
                }
                queue.offer(edge.to);
                visitedVertices.add(edge.to);
            }
        }
    }

    // 顶点
    private static class Vertex<V, E> {
        V value;
        // 以该节点为终点的边
        Set<Edge<V, E>> inEdges = new HashSet<>();
        // 以该节点为起点的边
        Set<Edge<V, E>> outEdges = new HashSet<>();
        public Vertex(V v) {
            this.value = v;
        }

        @Override
        public boolean equals(Object obj) {
           return Objects.equals(value, ((Vertex<V, E>)obj).value);
        }

        @Override
        public int hashCode() {
           return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
        // 边的起点
        Vertex<V, E> from;
        // 边的终点
        Vertex<V, E> to;
        // 权值
        E weight;

        public Edge(Vertex<V, E> fromVertex, Vertex<V, E> toVertex) {
            this.from = fromVertex;
            this.to = toVertex;
        }

        @Override
        public boolean equals(Object obj) {
            Edge<V, E> edge = (Edge<V, E>)obj;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            int fromCode = from.hashCode();
            int toCode = to.hashCode();
            return fromCode * 31 + toCode;
        }

        @Override
        public String toString() {
           return "Edge [from=" + from + ", to=" + to +", weight=" + weight + "]";
        }
    }
}
