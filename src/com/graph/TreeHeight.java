package com.graph;

import java.util.Arrays;

/**
 * Program to solve https://www.geeksforgeeks.org/height-generic-tree-parent-array/
 */
public class TreeHeight {
    public static void main(String[] args) {
        //int [] parent = {3, 4, 3, 4, -1};
        //int [] parent = {-1, 0, 0, 0, 3, 1, 1, 2};
        //int [] parent = {-1, 0, 1, 2, 3};
        //int [] parent = {3, 0, 3, 4, -1};
        //int [] parent = {6,5,4,3,2,1,0,-1};
        //int [] parent = {-1,0,1,2,3,4,5,6,7,8,9};
        int [] parent = {1,2,-1,0,2};
        int [] level = new int[parent.length];
        for(int i=0; i<level.length; i++){
            level[i] = -1;
        }
        boolean [] visited = new boolean[parent.length];
        for(int i = 0; i<parent.length; i++) {
            if(level[i] == -1 && !visited[i])
                dfs(i, parent, level, visited);
        }
        System.out.println(Arrays.toString(level));
        Arrays.sort(level);
        System.out.println(Arrays.toString(level));
        System.out.println(level[level.length-1]);
    }

    private static int dfs(int i, int[] parent, int[] level, boolean[] visited) {
        visited[i] = true;
        if(parent[i] == -1){
            level[i] = 0;
        } else {
            if(level[parent[i]] == -1 && !visited[parent[i]]){
                level[i] = dfs(parent[i], parent, level, visited);
            } else {
                level[i] = level[parent[i]] + 1;
            }
        }
        return level[i] + 1;
    }
}
