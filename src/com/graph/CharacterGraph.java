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
        int p = dfs(a, e, visited, pathCount);
        System.out.println(p);
    }

    private int dfs(char a, char e, boolean[] visited, int pathCount) {

        visited[a-65] = true;
        //System.out.println(a + "," + e + " -> " + Arrays.toString(visited));
        if(a == e){
            pathCount++;
            System.out.println(a + " -> " + pathCount);
        } else {
            Iterator<Character> itr = adjNodes[a-65].iterator();
            while (itr.hasNext()){
                char v = itr.next();
                //System.out.println(v);
                if(!visited[v-65]) {
                    pathCount = dfs(v, e, visited, pathCount);
                    //dfs(v, e, visited, pathCount);
                }
            }
        }
        visited[a-65] = false;
        System.out.println("Making "+(a) + " unvisited");
        return pathCount;
    }
}
