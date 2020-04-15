package com.graph;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Program to solve https://www.geeksforgeeks.org/minimum-initial-vertices-traverse-whole-matrix-given-conditions/
 */
public class MinimumVertices {
    public static void main(String[] args) {
        MinimumVertices minimumVertices = new MinimumVertices();
        int [] [] matrix = new int[][] {
                {1,2,3},
                {2,3,1},
                {1,1,1}
        };

        Set<Indices> pairs = new TreeSet<>();

        for(int i=0; i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                pairs.add(new Indices(i, j, matrix[i][j]));
            }
        }

        boolean [][] visited = new boolean[matrix.length][matrix.length];
        Iterator<Indices> itr = pairs.iterator();
        while (itr.hasNext()){
            Indices indices = itr.next();
            if(!visited[indices.getI()][indices.getJ()]) {
                minimumVertices.dfs(indices.getI(), indices.getJ(), visited, matrix);
                System.out.println("[" + indices.getI() + "," + indices.getJ() + "]");
            }
        }
    }

    private void dfs(int i, int j, boolean[][] visited, int[][] matrix) {
        visited[i][j] = true;
        // Right
        if(j + 1 < matrix.length && matrix[i][j+1] <= matrix[i][j] && !visited[i][j+1]){
            dfs(i, j+1, visited, matrix);
        }

        // Bottom
        if(i + 1 < matrix.length && matrix[i+1][j] <= matrix[i][j] && !visited[i+1][j]){
            dfs(i+1, j, visited, matrix);
        }

        // Up
        if(i - 1 >= 0 && matrix[i-1][j] <= matrix[i][j] && !visited[i-1][j]){
            dfs(i-1, j, visited, matrix);
        }

        // Left
        if(j - 1 >= 0 && matrix[i][j-1] <= matrix[i][j] && !visited[i][j-1]){
            dfs(i, j-1, visited, matrix);
        }
    }
}
