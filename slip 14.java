//slip 14
//Q.1) Write a program to sort a list of n numbers in ascending order using Insertion sort and determine the time required to sort the elements.v
import java.util.Arrays;
import java.util.Scanner;

public class InsertionSortTime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter the elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        long startTime = System.nanoTime();
        insertionSort(arr);
        long endTime = System.nanoTime();

        System.out.println("Sorted Array: " + Arrays.toString(arr));
        System.out.println("Time taken by Insertion Sort: " + (endTime - startTime) + " ns");

        scanner.close();
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}


//Q.2) Write a program to implement DFS and BFS. Compare the time complexity

import java.util.*;

public class GraphTraversalComparison {

    static class Graph {
        int V;
        LinkedList<Integer>[] adjList;

        Graph(int V) {
            this.V = V;
            adjList = new LinkedList[V];
            for (int i = 0; i < V; ++i) {
                adjList[i] = new LinkedList();
            }
        }

        void addEdge(int v, int w) {
            adjList[v].add(w);
        }

        // Depth First Search
        void DFSUtil(int v, boolean[] visited) {
            visited[v] = true;
            System.out.print(v + " ");

            Iterator<Integer> iterator = adjList[v].listIterator();
            while (iterator.hasNext()) {
                int n = iterator.next();
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            }
        }

        void DFS(int v) {
            boolean[] visited = new boolean[V];
            DFSUtil(v, visited);
        }

        // Breadth First Search
        void BFS(int s) {
            boolean[] visited = new boolean[V];
            LinkedList<Integer> queue = new LinkedList<>();

            visited[s] = true;
            queue.add(s);

            while (queue.size() != 0) {
                s = queue.poll();
                System.out.print(s + " ");

                Iterator<Integer> iterator = adjList[s].listIterator();
                while (iterator.hasNext()) {
                    int n = iterator.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Depth First Traversal:");
        long startTimeDFS = System.nanoTime();
        g.DFS(2); // Starting from vertex 2
        long endTimeDFS = System.nanoTime();
        System.out.println("\nTime taken by DFS: " + (endTimeDFS - startTimeDFS) + " ns");

        System.out.println("\nBreadth First Traversal:");
        long startTimeBFS = System.nanoTime();
        g.BFS(2); // Starting from vertex 2
        long endTimeBFS = System.nanoTime();
        System.out.println("\nTime taken by BFS: " + (endTimeBFS - startTimeBFS) + " ns");
    }
}
