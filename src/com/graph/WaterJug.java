package com.graph;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

/**
 * Program to solve https://www.geeksforgeeks.org/water-jug-problem-using-bfs/
 *
 * The operations you can perform are:
 *
 *      Empty a Jug, (X, Y)->(0, Y) Empty Jug 1
 *      Fill a Jug, (0, 0)->(X, 0) Fill Jug 1
 *      Pour water from one jug to the other until one of the jugs is either empty or full, (X, Y) -> (X-d, Y+d)
 */
public class WaterJug {
    public static void main(String[] args) {
        WaterJug waterJug = new WaterJug();

        waterJug.bfsToFillJug(8, 29, 3);
    }

    private void bfsToFillJug(int jug1Capacity, int jug2Capacity, int galonToFill) {
        JugState initialJugState = new JugState(0, 0);
        LinkedList<JugState> jugStatesQueue = new LinkedList<>();
        Set<JugState> visited = new LinkedHashSet<>();
        jugStatesQueue.add(initialJugState);
        visited.add(initialJugState);
        int steps = 0;
        JugState finalState = initialJugState;
        while (!jugStatesQueue.isEmpty()) {
            JugState nextJugState = jugStatesQueue.poll();
            int jug1State = nextJugState.getJug1State();
            int jug2State = nextJugState.getJug2State();
            System.out.print(nextJugState);
            steps++;

            if(jug2State == galonToFill || jug1State == galonToFill) {
                finalState = nextJugState;
                break;
            }
            Set<JugState> adjJugStates = new LinkedHashSet<>();

            // If both jugs are empty
            if(jug1State == 0 && jug2State == 0) {
                adjJugStates.add(new JugState(jug1Capacity, 0));
                adjJugStates.add(new JugState(0, jug2Capacity));
            } else if(jug1State == jug1Capacity && jug2State == jug2Capacity) { // If both jugs are full
                adjJugStates.add(new JugState(jug1Capacity, 0));
                adjJugStates.add(new JugState(0, jug2Capacity));
            } else {
                // If only Jug1 is empty
                if(jug1State == 0) {
                    adjJugStates.add(new JugState(jug1Capacity, jug2State));
                }

                // If only Jug2 is empty
                if(jug2State == 0) {
                    adjJugStates.add(new JugState(jug1State, jug2Capacity));
                }

                // If Jug1 is not full
                if (jug1State < jug1Capacity && jug2State > 0) {
                    int pour = jug1Capacity - jug1State;
                    if(pour<=jug2State)
                        adjJugStates.add(new JugState(jug1Capacity, jug2State - pour));
                    else
                        adjJugStates.add(new JugState(jug1State + jug2State, 0));
                }

                // If Jug2 is not full
                if(jug2State < jug2Capacity && jug1State > 0) {
                    int maxPour = jug2Capacity - jug2State;
                    if(maxPour <=jug1State)
                        adjJugStates.add(new JugState(jug1State - maxPour, jug2Capacity));
                    else 
                        adjJugStates.add(new JugState(0, jug1State + jug2State));
                }
            }

            //System.out.println(String.format("%s => %s", nextJugState, adjJugStates));
            if(adjJugStates.size() > 0) {
                for(JugState state : adjJugStates) {
                    if(!visited.contains(state)) {
                        state.setPreviousJugState(nextJugState);
                        jugStatesQueue.add(state);
                        visited.add(state);
                    }
                }
            }
            //System.out.println(jugStatesQueue);
        }

        //System.out.println(jugStatesQueue);
        boolean isItPossible = true;
        if(finalState.getJug2State() == galonToFill) {
            System.out.print(new JugState(0, finalState.getJug2State()));
            steps++;
        } else if(finalState.getJug1State() == galonToFill){
            System.out.print(new JugState(finalState.getJug1State(), 0));
            steps++;
        } else {
            isItPossible = false;
        }

        if(isItPossible){
            System.out.println("\n" + steps);
            Stack<JugState> shortedPath = new Stack<>();
            findShortestPath(finalState, shortedPath);
            System.out.println(shortedPath);
        } else {
            System.out.println("\nIt is not possible");
        }
    }

    private void findShortestPath(JugState finalState, Stack<JugState> shortedPath) {
        shortedPath.add(finalState);
        if(finalState.previousJugState!=null) {
            findShortestPath(finalState.previousJugState, shortedPath);
        }
    }

    private class JugState {
        private int jug1State;
        private int jug2State;
        private JugState previousJugState;

        public JugState(int jug1State, int jug2State) {
            this.jug1State = jug1State;
            this.jug2State = jug2State;
        }

        public int getJug1State() {
            return jug1State;
        }

        public int getJug2State() {
            return jug2State;
        }

        public JugState getPreviousJugState() {
            return previousJugState;
        }

        public void setPreviousJugState(JugState previousJugState) {
            this.previousJugState = previousJugState;
        }

        @Override
        public int hashCode() {
            return String.format("%s-%s", jug1State, jug2State).hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            JugState jugState = (JugState) obj;
            return (this.jug1State == jugState.jug1State && this.jug2State == jugState.jug2State);
        }

        @Override
        public String toString() {
            return String.format("[%s,%s]", jug1State, jug2State);
        }
    }
}
