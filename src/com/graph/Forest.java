package com.graph;

import java.util.LinkedList;

/**
 * Program to solve https://www.geeksforgeeks.org/count-number-trees-forest/
 */
public class Forest {
    private int vertex;

    private LinkedList<Integer>[] adjNodes;

    public Forest(int vertex) {
        this.vertex = vertex;
        adjNodes = new LinkedList[vertex];

        for(int i=0;i<vertex;i++){
            adjNodes[i] = new LinkedList<>();
        }
    }

    private void addEdge(int from, int to) {
        adjNodes[from].add(to);
    }

    private void print() {
        for(int i =0; i<adjNodes.length; i++){
            System.out.println(i + " -->" + adjNodes[i].toString());
        }
    }

    private void findNoOfTree() {
        boolean [] visited = new boolean[vertex];
        int noOfTree = 0;
        for(int i=0;i<vertex;i++){
            if(!visited[i]){
                boolean isTree = dfs(i, visited, true);
                System.out.println("is tree for node " + i + " : " + isTree);
                noOfTree = isTree ? noOfTree+1 : noOfTree;
            }
        }
        System.out.println(noOfTree);
    }

    private boolean dfs(int i, boolean[] visited, boolean isTree) {
        visited[i] = true;
        for(Integer adjNode : adjNodes[i]){
            if(!visited[adjNode]) {
                isTree = dfs(adjNode, visited, isTree);
            } else {
                if(isTree)
                    isTree = false;
            }
        }
        return isTree;
    }

    public static void main(String[] args) {
        Forest forest = new Forest(6);
        forest.addEdge(0, 1);
        forest.addEdge(0, 2);
        forest.addEdge(3, 4);
        //forest.addEdge(4, 2);
        forest.addEdge(4, 5);
        //forest.addEdge(4, 2);
        forest.print();
        forest.findNoOfTree();
    }
}
