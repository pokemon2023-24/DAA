//slip 8
//Q.1) Write a program to implement Fractional Knapsack problems using Greedy Method

import java.util.*;

public class KnapsackGreedy_8 {
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static double knapsackGreedy(int capacity, List<Item> items) {
        Collections.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0;
        int remainingCapacity = capacity;

        for (Item item : items) {
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                totalValue += (double) item.value / item.weight * remainingCapacity;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        List<Item> items = new ArrayList<>();
        System.out.println("Enter weights and values of items:");
        for (int i = 0; i < numItems; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items.add(new Item(weight, value));
        }

       

        double maxValue = knapsackGreedy(capacity, items);
        System.out.println("Maximum value that can be achieved: " + maxValue);

        scanner.close();
    }
}

// Enter the capacity of the knapsack: 10
// Enter the number of items: 3
// Enter the weight and value of each item:
// Item 1: 2 6
// Item 2: 5 8
// Item 3: 3 7

//Q.2) Write Program to implement Traveling Salesman Problem using nearest neighbor algorithm

import java.util.Scanner;

public class TSPNearestNeighbor_16 {
    public static void tsp(int[][] graph) {
        int numNodes = graph.length;
        int[] visited = new int[numNodes];
        visited[0] = 1;
        int current = 0;

        System.out.print("Path: 0 -> ");

        for (int i = 0; i < numNodes - 1; i++) {
            int next = -1;
            int minDist = Integer.MAX_VALUE;

            for (int j = 0; j < numNodes; j++) {
                if (graph[current][j] > 0 && visited[j] == 0 && graph[current][j] < minDist) {
                    minDist = graph[current][j];
                    next = j;
                }
            }

            if (next != -1) {
                visited[next] = 1;
                current = next;
                System.out.print(current + " -> ");
            }
        }

        System.out.println("0");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();
        int[][] graph = new int[numNodes][numNodes];

        System.out.println("Enter the adjacency matrix: ");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        tsp(graph);
        scanner.close();
    }
}


// Enter the number of nodes: 4
// Enter the adjacency matrix:
// 0 10 15 20
// 10 0 35 25
// 15 35 0 30
// 20 25 30 0
