package com.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * Program to solve https://www.geeksforgeeks.org/dfs-n-ary-tree-acyclic-graph-represented-adjacency-list/
 */
public class NAryTree {
    private int vertex;
    private LinkedList<Integer>[] adjNodes;
    public NAryTree(int vertex) {
        this.vertex = vertex;
        adjNodes = new LinkedList[vertex];
        for(int i=0; i<vertex; i++){
            adjNodes[i] = new LinkedList<>();
        }
    }

    private int size() {
        return vertex;
    }

    private void addEdge(int from, int to) {
        adjNodes[from].add(to);
    }

    private void dfs(int i, boolean[] visited) {
        visited[i] = true;
        System.out.print(i + " ");
        for(int adjNode : adjNodes[i]) {
            if(!visited[adjNode]){
                dfs(adjNode, visited);
            }
        }
    }

    public static void main(String[] args) {
        NAryTree nAryTree = new NAryTree(5);
        nAryTree.addEdge(0, 1);
        nAryTree.addEdge(0, 2);
        nAryTree.addEdge(1, 3);
        nAryTree.addEdge(2, 4);

        boolean []  visited = new boolean[nAryTree.size()];
        for (int i=0; i<nAryTree.size(); i++) {
            if(!visited[i])
                nAryTree.dfs(i, visited);
        }
    }
}
