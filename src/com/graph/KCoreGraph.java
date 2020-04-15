package com.graph;

import java.util.*;

/**
 * Program to solve https://www.geeksforgeeks.org/find-k-cores-graph/
 */
public class KCoreGraph {
    private int vertex;

    private LinkedList<Integer>[] adjNodes;

    public KCoreGraph(int vertex){
        this.vertex = vertex;

        adjNodes = new LinkedList[vertex];

        for (int i=0;i<adjNodes.length;i++){
            adjNodes[i] = new LinkedList<>();
        }
    }

    private void addEdge(int vertex, int edge) {
        adjNodes[vertex].add(edge);
    }

    private void clear() {
        for(LinkedList<Integer> adjNodeList : adjNodes){
            adjNodeList.clear();
        }
    }

    private void print() {
        for(int i =0; i<adjNodes.length; i++){
            System.out.println(i + " -->" + adjNodes[i].toString());
        }
    }

    private void findKcore(int core) {
        int []  degree = new int[vertex];
        for(int i=0; i<vertex; i++){
            degree[i] = adjNodes[i].size();
        }
        System.out.println("Degree before removing nodes : " + Arrays.toString(degree));
        boolean [] visited = new boolean[vertex];
        dfsForKCore(core, 0, visited, degree);
        System.out.println("Degree after removing nodes : " + Arrays.toString(degree));

        System.out.println("K core graph *************");
        for(int i=0; i<vertex; i++){
            if(degree[i]>=core){
                System.out.print(i + " -->[");
                for (Integer adjNode : adjNodes[i]) {
                    if(degree[adjNode] >= core) {
                        System.out.print(adjNode + ", ");
                    }
                }
                System.out.println("]");
            }
        }
    }

    private void dfsForKCore(int core, int node, boolean[] visited, int[] degree){
        visited[node] = true;
        for(Integer adjNode : adjNodes[node]){
            if(degree[node] < core){
                degree[adjNode] = degree[adjNode] - 1;
            }
            if(!visited[adjNode]) {
                dfsForKCore(core, adjNode, visited, degree);
            }
        }
    }

    public static void main(String[] args) {
        KCoreGraph graph = new KCoreGraph(9);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);

        graph.addEdge(2, 0);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);

        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(3, 7);

        graph.addEdge(4, 2);
        graph.addEdge(4, 3);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);

        graph.addEdge(5, 1);
        graph.addEdge(5, 2);
        graph.addEdge(5, 6);
        graph.addEdge(5, 8);

        graph.addEdge(6, 2);
        graph.addEdge(6, 3);
        graph.addEdge(6, 4);
        graph.addEdge(6, 5);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);

        graph.addEdge(7, 3);
        graph.addEdge(7, 4);
        graph.addEdge(7, 6);

        graph.addEdge(8, 5);
        graph.addEdge(8, 6);

        System.out.println("Input graph **************");
        graph.print();

        graph.findKcore(3);
    }
}
