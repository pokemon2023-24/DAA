//slip 5
//Q.1) Write a program for the Implementation of Kruskal’s algorithm to find minimum cost spanning tree. 
import java.util.*;

class Edge implements Comparable<Edge> {
    int source;
    int destination;
    int weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalsMST {
    private int vertices;
    private List<Edge> edges;

    public KruskalsMST(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edges.add(edge);
    }

    public void kruskalsMST() {
        Collections.sort(edges);

        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        List<Edge> mst = new ArrayList<>();

        int edgeCount = 0;
        int index = 0;
        while (edgeCount < vertices - 1 && index < edges.size()) {
            Edge edge = edges.get(index);
            index++;

            int sourceParent = findParent(parent, edge.source);
            int destinationParent = findParent(parent, edge.destination);

            if (sourceParent != destinationParent) {
                mst.add(edge);
                edgeCount++;
                parent[sourceParent] = destinationParent;
            }
        }

        System.out.println("Edges of Minimum Cost Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }

    private int findParent(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = findParent(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edgesCount = scanner.nextInt();

        KruskalsMST graph = new KruskalsMST(vertices);

        System.out.println("Enter the edges with their weights (source destination weight):");
        for (int i = 0; i < edgesCount; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        graph.kruskalsMST();

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

//Q.2) Write a program to implement Huffman Code using greedy methods and also calculate the
//best case and worst-case complexity. 

