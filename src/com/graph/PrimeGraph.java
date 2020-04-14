package com.graph;

import java.util.*;


/**
 * Program to solve https://www.geeksforgeeks.org/shortest-path-reach-one-prime-changing-single-digit-time/
 */
public class PrimeGraph {
    public static void main(String[] args) {
        PrimeGraph primeGraph = new PrimeGraph();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first prime number between 1000 and 9999 : ");
        int sourcePrime = sc.nextInt();
        System.out.print("Enter second prime number between 1000 and 9999 : ");
        int destinationPrime = sc.nextInt();
        System.out.println("Shortest path length is : " + primeGraph.getShortestPathLength(sourcePrime, destinationPrime));
    }

    private int getShortestPathLength(int sourcePrime, int destinationPrime) {
        if(!isPrime(sourcePrime) || !isPrime(destinationPrime)) {
            return -1;
        } else if (sourcePrime == destinationPrime){
            return 0;
        } else {
            return bfs(sourcePrime, destinationPrime);
        }
    }

    private int bfs(int sourcePrime, int destinationPrime) {
        int shortestPathLength = 0;
        LinkedList<Integer> levelQueue = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(sourcePrime);
        levelQueue.add(shortestPathLength);
        Set<Integer> visited = new HashSet<>();
        visited.add(sourcePrime);

        while (!queue.isEmpty()) {
            int nextPrime = queue.peek();
            shortestPathLength = levelQueue.peek();
            if (nextPrime == destinationPrime) {
                break;
            }

            Set<Integer> adjNodes = getUnvisitedAdjPrimes(nextPrime, visited);
            if (adjNodes.size() > 0) {
                for (Integer adjNode : adjNodes) {
                    queue.add(adjNode);
                    visited.add(adjNode);
                    levelQueue.add(shortestPathLength + 1);
                }
            } else {
                queue.poll();
                levelQueue.poll();
            }
        }
        return shortestPathLength;
    }

    private Set<Integer> getUnvisitedAdjPrimes(int prime, Set<Integer> visited) {
        Set<Integer> adjNodes = new HashSet<>();
        int firstDigit = prime / 1000;
        int secondDigit = (prime / 100) % 10;
        int thirdDigit = (prime / 10) % 10;
        int forthDigit = (prime % 10);

        addAdjPrimesByChangingDigits(firstDigit, 1000, prime, adjNodes, visited);
        addAdjPrimesByChangingDigits(secondDigit, 100, prime, adjNodes, visited);
        addAdjPrimesByChangingDigits(thirdDigit, 10, prime, adjNodes, visited);
        addAdjPrimesByChangingDigits(forthDigit, 1, prime, adjNodes, visited);

        return adjNodes;
    }

    private void addAdjPrimesByChangingDigits(int digit, int multiplier, int prime, Set<Integer> adjNodes, Set<Integer> visited) {
        for (int i=0;i<10;i++) {
            if (i != digit) {
                int adjNode = prime + ((i - digit) * multiplier);
                if (isPrime(adjNode) && !visited.contains(adjNode)) {
                    adjNodes.add(adjNode);
                }
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 999 || n > 9999) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
