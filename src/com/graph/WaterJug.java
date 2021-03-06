package com.graph;

import java.util.*;

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
            int jug1State = nextJugState.jug1State;
            int jug2State = nextJugState.jug2State;
            //System.out.print(nextJugState);
            steps++;

            if(jug2State == galonToFill || jug1State == galonToFill) {
                finalState = nextJugState;
                break;
            }
            Set<JugState> adjJugStates = getAdjJugs(jug1State, jug2State, jug1Capacity, jug2Capacity);

            System.out.println(String.format("%s => %s", nextJugState, adjJugStates));
            if(adjJugStates.size() > 0) {
                for(JugState state : adjJugStates) {
                    if(!visited.contains(state)) {
                        state.previousJugState = nextJugState;
                        jugStatesQueue.add(state);
                        visited.add(state);
                    }
                }
            }
        }
        if(isSolutionAvailable(finalState, galonToFill)){
            System.out.println("\n" + ++steps);
            System.out.println(getShortestPath(finalState));
        } else {
            System.out.println("\nIt is not possible");
        }
    }

    private boolean isSolutionAvailable(JugState finalState, int galonToFill) {
        boolean isItPossible = true;
        if(finalState.jug2State == galonToFill) {
            System.out.print(new JugState(0, finalState.jug2State));
        } else if(finalState.jug1State == galonToFill){
            System.out.print(new JugState(finalState.jug1State, 0));
        } else {
            isItPossible = false;
        }
        return isItPossible;
    }

    private Set<JugState> getAdjJugs(int jug1State, int jug2State, int jug1Capacity, int jug2Capacity) {

        Set<JugState> adjJugStates = new HashSet<>();

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

        return adjJugStates;
    }

    private String getShortestPath(JugState finalState) {
        StringBuilder shortedPath = new StringBuilder(finalState.toString());
        while (finalState.previousJugState!=null) {
            shortedPath.insert(0, finalState.previousJugState);
            finalState = finalState.previousJugState;
        }
        return shortedPath.toString();
    }

    private class JugState {
        private int jug1State;
        private int jug2State;
        private JugState previousJugState;

        public JugState(int jug1State, int jug2State) {
            this.jug1State = jug1State;
            this.jug2State = jug2State;
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
