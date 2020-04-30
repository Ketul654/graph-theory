package com.graph;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Program to solve https://www.geeksforgeeks.org/peterson-graph/
 */
public class PetersonGraph {
    private int vertex;
    private LinkedList<Integer>[] adjNodes;

    public PetersonGraph(int vertex) {
        this.vertex = vertex;
        this.adjNodes = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++) {
            adjNodes[i] = new LinkedList<>();
        }
    }

    public void addEdge(int from, int to) {
        adjNodes[from].add(to);
    }

    public void print() {
        for (int i = 0; i < vertex; i++) {
            System.out.println(String.format("[%s-%s]", i, adjNodes[i].toString()));
        }
    }

    public static void main(String[] args) {
        PetersonGraph petersonGraph = new PetersonGraph(10);

        petersonGraph.addEdge(0, 1);
        petersonGraph.addEdge(0, 4);
        petersonGraph.addEdge(0, 5);

        petersonGraph.addEdge(1, 0);
        petersonGraph.addEdge(1, 2);
        petersonGraph.addEdge(1, 6);

        petersonGraph.addEdge(2, 1);
        petersonGraph.addEdge(2, 3);
        petersonGraph.addEdge(2, 7);

        petersonGraph.addEdge(3, 2);
        petersonGraph.addEdge(3, 4);
        petersonGraph.addEdge(3, 8);

        petersonGraph.addEdge(4, 0);
        petersonGraph.addEdge(4, 3);
        petersonGraph.addEdge(4, 9);

        petersonGraph.addEdge(5, 0);
        petersonGraph.addEdge(5, 7);
        petersonGraph.addEdge(5, 8);

        petersonGraph.addEdge(6, 1);
        petersonGraph.addEdge(6, 8);
        petersonGraph.addEdge(6, 9);

        petersonGraph.addEdge(7, 2);
        petersonGraph.addEdge(7, 5);
        petersonGraph.addEdge(7, 9);

        petersonGraph.addEdge(8, 3);
        petersonGraph.addEdge(8, 5);
        petersonGraph.addEdge(8, 6);

        petersonGraph.addEdge(9, 4);
        petersonGraph.addEdge(9, 6);
        petersonGraph.addEdge(9, 7);

        //petersonGraph.print();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter S : ");
        char [] S = sc.next().toCharArray();
        int []  W = new int[S.length];
        if(petersonGraph.determineWalk(S, W)) {
            for(int i=0; i< W.length; i++)
                System.out.print(W[i]);
        }else
            System.out.println("-1");
    }

    public boolean determineWalk(char [] S, int[] W) {
        boolean hasWalk = bfs(S[0] - 65, S, W);
        if(!hasWalk)
            hasWalk = bfs(S[0] - 65 + 5, S, W);
        return hasWalk;
    }

    public boolean bfs(int node, char [] S, int [] W) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertex];
        int pathOffset = 0;
        queue.add(node);
        visited[node] = true;
        W[pathOffset] = node;
        pathOffset = pathOffset + 1;
        while (!queue.isEmpty()) {
            int firstNode = queue.poll();
            if(pathOffset < S.length) {
                for (Integer adjNode : adjNodes[firstNode]) {
                    if (!visited[adjNode]) {
                        if (S[pathOffset] == (adjNode % 5) + 65) {
                            queue.add(adjNode);
                            W[pathOffset] = adjNode;
                            pathOffset++;
                            visited[adjNode] = true;
                            break;
                        }
                    }
                }
            }
            if(pathOffset == S.length) {
                return true;
            }
        }
        return false;
    }
}
