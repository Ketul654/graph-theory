package com.graph;

import java.util.*;

/**
 * Program to solve https://www.geeksforgeeks.org/count-possible-paths-two-vertices/
 */
public class CharacterGraph {
    private int vertex;

    private LinkedList<Character>[] adjNodes;

    public CharacterGraph(int vertex) {
        this.vertex = vertex;

        adjNodes = new LinkedList[vertex];

        for (int i = 0; i < vertex; i++) {
            adjNodes[i] = new LinkedList<>();
        }
    }

    public void addEdge(char vertex, char edge) {
        adjNodes[vertex - 65].add(edge);
    }

    public static void main(String[] args) {
        CharacterGraph characterGraph = new CharacterGraph(5);
        characterGraph.addEdge('A', 'B');
        characterGraph.addEdge('A', 'E');
        characterGraph.addEdge('A', 'C');
        characterGraph.addEdge('B', 'D');
        characterGraph.addEdge('B', 'E');
        characterGraph.addEdge('C', 'E');
        characterGraph.addEdge('D', 'C');

        characterGraph.countAllPathBetweenVertices('A', 'E');

    }

    private void countAllPathBetweenVertices(char a, char e) {
        boolean [] visited = new boolean[vertex];
        int pathCount = 0;
        System.out.println(dfs(a, e, visited, pathCount));
    }

    private int dfs(char a, char e, boolean[] visited, int pathCount) {

        visited[a-65] = true;
        if(a == e){
            pathCount++;
        } else {
            Iterator<Character> itr = adjNodes[a-65].iterator();
            while (itr.hasNext()){
                char v = itr.next();
                if(!visited[v-65]) {
                    pathCount = dfs(v, e, visited, pathCount);
                }
            }
        }
        visited[a-65] = false;
        return pathCount;
    }
}
