package com.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Program to solve https://www.geeksforgeeks.org/transitive-closure-of-a-graph-using-dfs/
 */
public class TransitiveClosure {
    private int vertex;

    private LinkedList<Integer>[] adjNodes;

    private Stack<Integer> completedNode = new Stack<>();

    public TransitiveClosure(int vertex){
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

    private void findTransitiveClosure() {

        System.out.println("Finding Transitive Closure for graph **********************************");
        this.print();
        int [][] tc = new int[vertex][vertex];

        for(int i=0;i<vertex;i++){
            tc[i][i] = 0;
            dfsForTransitiveClosure(i, i, tc);
        }

        System.out.println("Output *********************************");
        for(int i=0;i<vertex;i++){
            System.out.println(Arrays.toString(tc[i]));
        }
    }

    private void dfsForTransitiveClosure(int node, int adjNode, int[][] tc) {
        if(node != adjNode)
            tc[node][adjNode] = 1;
        for(int an: adjNodes[adjNode ]){
            if(tc[node][an]!=1)
                dfsForTransitiveClosure(node, an, tc);
        }
    }

    public static void main(String[] args) {
        TransitiveClosure graph = new TransitiveClosure(7);

        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        graph.addEdge(1, 0);

        graph.addEdge(2, 1);

        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        graph.findTransitiveClosure();
    }
}
