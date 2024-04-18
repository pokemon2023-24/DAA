//Slip 7
//Q-1) Write a program for the Implementation of Dijkstra’s algorithm to find shortest path to
//other vertices 

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


//Q.2) Write a program for finding Topological sorting for Directed Acyclic Graph (DAG)

import java.util.*;

public class TopologicalSort_14 {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public TopologicalSort_14(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
    }

    public void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        Iterator<Integer> iterator = adjList[vertex].listIterator();
        while (iterator.hasNext()) {
            int nextVertex = iterator.next();
            if (!visited[nextVertex]) {
                topologicalSortUtil(nextVertex, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public void topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        System.out.println("Topological Sorting:");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        TopologicalSort_14 graph = new TopologicalSort_14(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        graph.topologicalSort();

        scanner.close();
    }
}


// Enter the number of vertices: 6
// Enter the number of edges: 6
// Enter the edges (source destination):
// 5 2
// 5 0
// 4 0
// 4 1
// 2 3
// 3 1




