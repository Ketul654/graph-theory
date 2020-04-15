package com.graph;

import java.util.*;

public class SCCAndMotherNode {
    private int vertex;

    private LinkedList<Integer>[] adjNodes;

    private Stack<Integer> completedNode = new Stack<>();

    public SCCAndMotherNode(int vertex){
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

    private void dfsRecursive(int node, boolean[] visited, boolean fillCompletedStack) {
        visited[node] = true;
        for(int adjNode : adjNodes[node]){
            if(!visited[adjNode]){
                dfsRecursive(adjNode, visited, fillCompletedStack);
            }
        }
        System.out.print(node + " -->");
        if(fillCompletedStack)
            completedNode.add(node);
    }

    private void findMotherNode() {
        System.out.println("Finding mother node ************************");
        boolean [] visited = new boolean[vertex];
        int lastVisitedVertex = 0;
        for(int i=0; i<vertex; i++){
            if(!visited[i]) {
                dfsRecursive(i, visited, false);
                lastVisitedVertex = i;
            }
        }
        System.out.println(" NULL");
        visited = new boolean[vertex];
        dfsRecursive(lastVisitedVertex, visited, false);
        System.out.println(" NULL");

        boolean isMotherNode = true;
        for(boolean isVisited : visited) {
            if (!isVisited) {
                isMotherNode = false;
                break;
            }
        }

        if(isMotherNode)
            System.out.println("One of the mother nodes is : "+ lastVisitedVertex);
        else
            System.out.println("SCCAndMotherNode does not have mother node");
    }

    private void findSCC() {
        System.out.println("Finding SCC ***********************");
        boolean[] visited = new boolean[vertex];
        for(int i=0; i<vertex; i++) {
            if(!visited[i]) {
                dfsRecursive(i, visited, true);
            }
        }

        SCCAndMotherNode graph = this.transpose();

        System.out.println("\nTransposed SCCAndMotherNode");
        graph.print();

        System.out.println("SCC :");
        visited = new boolean[vertex];
        while (!completedNode.isEmpty()){
            int node = completedNode.pop();
            if(!visited[node]) {
                graph.dfsRecursive(node, visited, false);
            }
            System.out.println();
        }
    }

    private SCCAndMotherNode transpose() {
        SCCAndMotherNode graph = new SCCAndMotherNode(vertex);
        for(int i=0;i<vertex;i++){
            for(int adjNode : adjNodes[i]){
                graph.addEdge(adjNode, i);
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        SCCAndMotherNode graph = new SCCAndMotherNode(7);

        graph.addEdge(0, 2);
        graph.addEdge(0, 3);

        graph.addEdge(1, 0);

        graph.addEdge(2, 1);

        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        graph.findMotherNode();

        graph.findSCC();
    }
}
