//slip 16
//Q.1) Write a program to implement to find out solution for 0/1 knapsack problem using
//dynamic programming. 

import java.util.*;

public class KnapsackProblem_25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        int[] weights = new int[numItems];
        int[] values = new int[numItems];

        System.out.println("Enter the weight and value of each item:");
        for (int i = 0; i < numItems; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        scanner.close();
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int numItems = weights.length;
        int[][] dp = new int[numItems + 1][capacity + 1];

        for (int i = 1; i <= numItems; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[numItems][capacity];
    }
}


// Enter the number of items: 3
// Enter the weight and value of each item:
// 5 10
// 3 7
// 4 8
// Enter the capacity of the knapsack: 7


//Q.2) Write a program to determine if a given graph is a Hamiltonian cycle or not

import java.util.*;

public class HamiltonianCycle_15 {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public HamiltonianCycle_15(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    public boolean isHamiltonianCycle() {
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        // Check if every vertex is reachable from the first vertex
        if (dfs(0, visited, 1)) {
            // Check if all vertices are visited
            for (boolean v : visited) {
                if (!v) {
                    return false;
                }
            }
            // Check if the last vertex has an edge to the first vertex
            return adjList[0].contains(vertices - 1);
        }
        return false;
    }

    private boolean dfs(int vertex, boolean[] visited, int count) {
        visited[vertex] = true;

        if (count == vertices) {
            return true;
        }

        for (int nextVertex : adjList[vertex]) {
            if (!visited[nextVertex] && dfs(nextVertex, visited, count + 1)) {
                return true;
            }
        }

        visited[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        HamiltonianCycle_15 graph = new HamiltonianCycle_15(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        if (graph.isHamiltonianCycle()) {
            System.out.println("The given graph is a Hamiltonian cycle.");
        } else {
            System.out.println("The given graph is not a Hamiltonian cycle.");
        }

        scanner.close();
    }
}


// Enter the number of vertices: 5
// Enter the number of edges: 7
// Enter the edges (source destination):
// 0 1
// 1 2
// 2 3
// 3 4
// 4 0
// 0 2
// 1 3


