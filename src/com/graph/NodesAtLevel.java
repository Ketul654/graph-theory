package com.graph;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Program to solve https://www.geeksforgeeks.org/count-number-nodes-given-level-using-bfs/
 */
public class NodesAtLevel {
    private int vertex;
    private LinkedList<Integer> [] adjNodes;

    public NodesAtLevel(int vertex){
        this.vertex = vertex;
        adjNodes = new LinkedList[vertex];
        for (int i=0; i<vertex; i++){
            adjNodes[i] = new LinkedList<>();
        }
    }

    public static void main(String[] args) {
        NodesAtLevel graph = new NodesAtLevel(7);
        graph.addEdges(0,1);
        graph.addEdges(0,2);
        graph.addEdges(1,3);
        graph.addEdges(1,4);
        graph.addEdges(1,5);
        graph.addEdges(2,6);

        graph.noOfNodesAtLevel(0);

        graph = new NodesAtLevel(6);
        graph.addEdges(0,1);
        graph.addEdges(0,2);
        graph.addEdges(1,3);
        graph.addEdges(2,4);
        graph.addEdges(2,5);

        graph.noOfNodesAtLevel(2);
    }

    private void noOfNodesAtLevel(int level) {
        LinkedList<Integer> queueBfs = new LinkedList<>();
        int [] queueLevel = new int[vertex];

        queueBfs.add(0);
        queueLevel[0] = 0;
        while (!queueBfs.isEmpty()){
            int node = queueBfs.poll();
            for(int adjNode : adjNodes[node]){
                queueBfs.add(adjNode);
                queueLevel[adjNode] = queueLevel[node] + 1;
            }
        }

        int count = 0;
        for(int i=0;i<vertex;i++){
            if(queueLevel[i] == level){
                count++;
            }
        }
        System.out.println(Arrays.toString(queueLevel));
        System.out.println(count);
    }

    private void addEdges(int vertex, int edge) {
        adjNodes[vertex].add(edge);
    }
}
