//slip 18
//Q.1) Write a program to implement Graph Coloring Algorithm. 

import java.util.Scanner;

public class GraphColoring_17 {
    private int V;
    private int[] colors;
    private boolean[][] graph;

    public GraphColoring_17(int v) {
        V = v;
        colors = new int[V];
        graph = new boolean[V][V];
    }

    public void addEdge(int u, int v) {
        graph[u][v] = true;
        graph[v][u] = true;
    }

    public boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] && c == colors[i])
                return false;
        }
        return true;
    }

    public boolean graphColoringUtil(int v, int m) {
        if (v == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                colors[v] = c;

                if (graphColoringUtil(v + 1, m))
                    return true;

                colors[v] = 0;
            }
        }

        return false;
    }

    public boolean graphColoring(int m) {
        if (!graphColoringUtil(0, m)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution();
        return true;
    }

    public void printSolution() {
        System.out.println("Solution Exists: Following are the assigned colors");
        for (int i = 0; i < V; i++)
            System.out.println("Vertex " + i + " ---> Color " + colors[i]);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        GraphColoring_17 g = new GraphColoring_17(V);

        System.out.println("Enter the adjacency matrix (1 if edge exists, 0 otherwise):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                g.graph[i][j] = scanner.nextInt() == 1;
            }
        }

        System.out.print("Enter the number of colors: ");
        int m = scanner.nextInt();

        g.graphColoring(m);

        scanner.close();
    }
}


/*Enter the number of vertices: 4
Enter the adjacency matrix (1 if edge exists, 0 otherwise):
0 1 1 1
1 0 1 1
1 1 0 1
1 1 1 0
Enter the number of colors: 4
*/

//Q.2) Write a program to find out live node, E node and dead node from a given graph.

import java.util.*;

public class NodeClassification {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            adjList.get(source).add(destination);
        }

        System.out.print("Enter the source node: ");
        int source = scanner.nextInt();

        Set<Integer> liveNodes = new HashSet<>();
        Set<Integer> eNodes = new HashSet<>();
        Set<Integer> deadNodes = new HashSet<>();

        dfs(adjList, source, liveNodes, eNodes, deadNodes);
        markDeadNodes(adjList, source, liveNodes, deadNodes);

        System.out.println("Live Nodes: " + liveNodes);
        System.out.println("E Nodes: " + eNodes);
        System.out.println("Dead Nodes: " + deadNodes);

        scanner.close();
    }

    public static void dfs(List<List<Integer>> adjList, int vertex, Set<Integer> liveNodes, Set<Integer> eNodes, Set<Integer> deadNodes) {
        liveNodes.add(vertex);
        for (int neighbor : adjList.get(vertex)) {
            if (!liveNodes.contains(neighbor)) {
                dfs(adjList, neighbor, liveNodes, eNodes, deadNodes);
            } else if (!eNodes.contains(neighbor)) {
                eNodes.add(neighbor);
            }
        }
    }

    public static void markDeadNodes(List<List<Integer>> adjList, int source, Set<Integer> liveNodes, Set<Integer> deadNodes) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size()];
        visited[source] = true;
        queue.add(source);
    
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : adjList.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    
        for (int i = 0; i < adjList.size(); i++) {
            if (!visited[i]) {
                deadNodes.add(i);
            }
        }
    }
    
}



// Enter the number of vertices: 5
// Enter the number of edges: 7
// Enter the edges (source destination):
// 0 1
// 0 2
// 1 3
// 2 3
// 3 4
// 4 2
// 1 2
// Enter the source node: 0

