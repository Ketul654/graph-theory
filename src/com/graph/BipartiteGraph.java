package com.graph;

import java.util.LinkedList;

public class BipartiteGraph {
    private int vertex;
    private LinkedList<Integer>[] adjNodes;
    private Enum<COLOR>[] visited;
    private int blue;
    private int green;
    private int numOfEdges = 0;

    public BipartiteGraph(int vertex){
        this.vertex = vertex;
        adjNodes = new LinkedList[vertex];
        visited = new Enum[vertex];

        for(int i=0; i<vertex; i++) {
            adjNodes[i] = new LinkedList();
            visited[i] = COLOR.WHITE;
        }
    }

    private void addEdge(int from, int to) {
        if(visited[from] == COLOR.WHITE && visited[to] == COLOR.WHITE) {
            visited[from] = COLOR.BLUE;
            visited[to] = COLOR.GREEN;
            blue++;
            green++;
        } else if (visited[from] == COLOR.WHITE) {
            if(visited[to] == COLOR.BLUE) {
                visited[from] = COLOR.GREEN;
                green++;
            } else {
                visited[from] = COLOR.BLUE;
                blue++;
            }
        } else if (visited[to] == COLOR.WHITE) {
            if(visited[from] == COLOR.BLUE) {
                visited[to] = COLOR.GREEN;
                green++;
            } else {
                visited[to] = COLOR.BLUE;
                blue++;
            }
        }
        adjNodes[from].add(to);
        adjNodes[to].add(from);
        numOfEdges++;
    }

    public void printVisited(){
        for (int i = 0; i<vertex; i++) {
            System.out.print(visited[i] + " ");
        }
        System.out.println();
    }

    public int getMaxEdgesPossible(){
        return (blue * green) - numOfEdges;
    }

    public void printGraph(){
        for(int i=0; i<vertex; i++) {
            System.out.println(i + " ==> " + adjNodes[i].toString());
        }
    }

    private enum COLOR {
        BLUE, GREEN, WHITE;
    }

    public static void main(String[] args) {
        BipartiteGraph bipartiteGraph = new BipartiteGraph(5);
        bipartiteGraph.addEdge(0, 1);
        bipartiteGraph.addEdge(0, 2);
        bipartiteGraph.addEdge(1, 3);
        bipartiteGraph.addEdge(2, 4);
        bipartiteGraph.printGraph();
        bipartiteGraph.printVisited();
        System.out.println(bipartiteGraph.getMaxEdgesPossible());
    }


}




