//slip12
//Q.1) Write a program to implement BFS (Breadth First Search) and determine the time complexity for the same
import java.util.*;

public class BreadthFirstSearch_13 {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    public BreadthFirstSearch_13(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    void BFS(int s) {
        boolean visited[] = new boolean[V];

        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        BreadthFirstSearch_13 g = new BreadthFirstSearch_13(V);

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        System.out.println("Enter the edges (source destination): ");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            g.addEdge(src, dest);
        }

        System.out.print("Enter the starting vertex for BFS: ");
        int start = scanner.nextInt();

        System.out.println("Breadth First Traversal starting from vertex " + start + ":");
        g.BFS(start);

        scanner.close();
    }
}
//Enter the number of vertices: 6
//Enter the number of edges: 8
//Enter the edges (source destination):
//0 1
//0 2
//1 3
//1 4
//2 4
//3 4
//3 5
//4 5
//Enter the starting vertex for BFS: 0




//Q.2) Write a program to sort a given set of elements using the Selection sort method and determine the time required to sort the elements.

import java.util.Arrays;

public class SelectionSort_1 {

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        System.out.println("Original Array: " + Arrays.toString(arr));

        long startTime = System.nanoTime();
        selectionSort(arr);
        long endTime = System.nanoTime();

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Time taken by Selection Sort: " + (endTime - startTime) + " ns");
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}