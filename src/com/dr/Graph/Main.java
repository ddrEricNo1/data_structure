package com.dr.Graph;

import com.dr.List.List;

import java.util.Collection;

public class Main {
    static Graph.WeightManager<Integer>  weightManager = new Graph.WeightManager<>() {
        @Override
        public int compare(Integer w1, Integer w2) {
           return w2 - w1;
        }

        @Override
        public Integer add(Integer w1, Integer w2) {
            return w1 + w2;
        }
    };

    static void testDfs() {
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        //graph.removeVertex("V0");
        graph.bfs("V1", new Graph.VertexVisitor<String>() {
            @Override
            public boolean visit(String s) {
                System.out.println(s);
                return false;
            }
        });
    }

    static void testMst() {
        ListGraph<String, Integer> graph = new ListGraph<>(weightManager);
        graph.addEdge("1", "5", 1);
        graph.addEdge("5", "1", 1);

        graph.addEdge("1", "2", 3);
        graph.addEdge("2", "1", 3);

        graph.addEdge("2", "6", 6);
        graph.addEdge("6", "2", 6);

        graph.addEdge("5", "6", 4);
        graph.addEdge("6", "5", 4);

        graph.addEdge("6", "4", 8);
        graph.addEdge("4", "6", 8);

        graph.addEdge("2", "4", 4);
        graph.addEdge("4", "2", 4);

        graph.addEdge("2", "0", 2);
        graph.addEdge("0", "2", 2);

        graph.addEdge("4", "0", 7);
        graph.addEdge("0", "4", 7);

        graph.addEdge("5", "7", 5);
        graph.addEdge("7", "5", 5);

        graph.addEdge("7", "3", 9);
        graph.addEdge("3", "7", 9);

        Collection< Graph.EdgeInfo<String, Integer>> result = graph.mst();
        for(Graph.EdgeInfo<String, Integer> edge: result) {
            System.out.println(edge);
        }
    }

    public static void main(String[] args) {
        testMst();
    }
}
