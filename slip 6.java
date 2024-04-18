//slip 6
//Q-1) Write a program for the Implementation of Prim’s algorithm to find minimum cost
//spanning tree. 

import java.util.*;

public class PrimsMST_6 {
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static List<Edge> primMST(List<Edge>[] graph, int vertices) {
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        List<Edge> result = new ArrayList<>();

        // Start Prim's algorithm from vertex 0
        visited[0] = true;
        for (Edge edge : graph[0]) {
            minHeap.add(edge);
        }

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            if (visited[edge.destination]) {
                continue;
            }
            visited[edge.destination] = true;
            result.add(edge);
            3
            for (Edge adjacent : graph[edge.destination]) {
                if (!visited[adjacent.destination]) {
                    minHeap.add(adjacent);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        List<Edge>[] graph = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.println("Enter the edges with their weights (source destination weight):");
        for (int i = 0; i < edgesCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[source].add(new Edge(source, destination, weight));
            graph[destination].add(new Edge(destination, source, weight));
        }

        List<Edge> mst = primMST(graph, vertices);

        System.out.println("Edges of Minimum Cost Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }

        scanner.close();
    }
}


// Enter the number of vertices: 4
// Enter the number of edges: 5
// Enter the edges with their weights (source destination weight):
// 0 1 10
// 0 2 6
// 0 3 5
// 1 3 15
// 2 3 4



//Q.2) Write a Program to find only length of Longest Common Subsequence

public class LongestCommonSubsequence22 {
 
    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    int lcs(char[] X, char[] Y, int m, int n)
    {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcs(X, Y, m - 1, n - 1);
        else
            return max(lcs(X, Y, m, n - 1), lcs(X, Y, m - 1, n));
    }
 
    /* Utility function to get max of 2 integers */
    int max(int a, int b)
    {
        return (a > b) ? a : b;
    }
 
    public static void main(String[] args)
    {
        LongestCommonSubsequence22 lcs = new LongestCommonSubsequence22();
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
 
        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int m = X.length;
        int n = Y.length;
 
        System.out.println("Length of LCS is"
                           + " " + lcs.lcs(X, Y, m, n));
    }
}

/*OUTPUT:
Length of LCS is 4
  */