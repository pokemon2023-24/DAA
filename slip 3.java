//slip 3
//Q.1) Write a program to sort a given set of elements using the Quick sort method and determine
the time required to sort the elements
import java.util.Random;

public class Ass1_2 { //QuickSortExample

    public static void main(String[] args) {
        // Experiment with different values of n
        int[] nValues = {100, 500, 1000, 5000, 10000};

        for (int n : nValues) {
            int[] arr = generateRandomArray(n);
            
            // Measure the time required for sorting
            long startTime = System.nanoTime();
            quickSort(arr, 0, arr.length - 1);
            long endTime = System.nanoTime();
            
            long elapsedTime = endTime - startTime;
            
            System.out.println("For n=" + n + ", Time taken: " + elapsedTime + " nanoseconds");
        }
    }

    // Quick Sort Algorithm
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
     // Generate an array of random integers
     public static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1000); // Adjust the upper limit as needed
        }
         return arr;
    }
}




// Q.2) Write a program to find Minimum Cost Spanning Tree of a given undirected graph using Prims algorithm

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