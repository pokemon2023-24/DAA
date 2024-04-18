//slip11
//Q.1) Write a programs to implement DFS (Depth First Search) and determine the time complexity for the same.
import java.util.*;

public class DepthFirstSearch_13 {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    public DepthFirstSearch_13(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();
    }
    

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        DepthFirstSearch_13 g = new DepthFirstSearch_13(V);

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges (source destination): ");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            g.addEdge(src, dest);
        }

        System.out.print("Enter the starting vertex for DFS: ");
        int start = scanner.nextInt();

        System.out.println("Depth First Traversal starting from vertex " + start + ":");
        g.DFS(start);

        scanner.close();
    }
}
/*Enter the number of vertices: 3
Enter the number of edges: 2
Enter the edges (source destination):
0 1
1 2
Enter the starting vertex for DFS: 0
Depth First Traversal starting from vertex 0:
0 1 2*/




//Q.2) Write a program to find shortest paths from a given vertex in a weighted connected graph, to other vertices using Dijkstra’s algorithm. 

import java.util.*;

public class DijkstrasShortestPath_7 {
    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static int[] dijkstra(List<Edge>[] graph, int source, int vertices) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        minHeap.add(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int u = current.destination;

            for (Edge neighbor : graph[u]) {
                int v = neighbor.destination;
                int w = neighbor.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    minHeap.add(new Edge(v, distance[v]));
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        List<Edge>[] graph = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        System.out.println("Enter the edges with their weights (destination weight):");
        for (int i = 0; i < edgesCount; i++) {
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph[source].add(new Edge(destination, weight));
        }

        int[] shortestDistances = dijkstra(graph, source, vertices);

        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": " + shortestDistances[i]);
        }

        scanner.close();
    }
}


// Enter the number of vertices: 4
// Enter the number of edges: 5
// Enter the source vertex: 0
// Enter the edges with their weights (destination weight):
// 0 1 10
// 0 2 6
// 0 3 5
// 1 3 15
// 2 3 4