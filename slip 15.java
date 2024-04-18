//slip 15
//Q.1) Write a program to implement to find out solution for 0/1 knapsack problem using LCBB (Least Cost Branch and Bound).
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Item {
    int weight;
    int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

class Node {
    int level;
    int weight;
    int value;
    double bound;

    public Node(int level, int weight, int value) {
        this.level = level;
        this.weight = weight;
        this.value = value;
        this.bound = 0.0;
    }
}

public class KnapsackLCBB {
    static Comparator<Item> comparator = Comparator.comparingDouble((Item a) -> (double) a.value / a.weight).reversed();

    public static double bound(Node u, int n, int W, Item[] arr) {
        if (u.weight >= W) {
            return 0;
        }

        double profitBound = u.value;
        int j = u.level + 1;
        int totalWeight = u.weight;

        while (j < n && totalWeight + arr[j].weight <= W) {
            totalWeight += arr[j].weight;
            profitBound += arr[j].value;
            j++;
        }

        if (j < n) {
            profitBound += (W - totalWeight) * ((double) arr[j].value / arr[j].weight);
        }

        return profitBound;
    }

    public static int knapsack(int W, Item[] arr, int n) {
        Arrays.sort(arr, comparator);

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingDouble((Node a) -> a.bound).reversed());

        Node u = new Node(-1, 0, 0);
        Node v;
        int maxProfit = 0;

        u.bound = bound(u, n, W, arr);
        queue.add(u);

        while (!queue.isEmpty()) {
            u = queue.poll();

            if (u.bound > maxProfit) {
                v = new Node(0, 0, 0);

                if (u.level == -1) {
                    v.level = 0;
                } else if (u.level != n - 1) {
                    v.level = u.level + 1;
                }

                v.weight = u.weight + arr[v.level].weight;
                v.value = u.value + arr[v.level].value;

                if (v.weight <= W && v.value > maxProfit) {
                    maxProfit = v.value;
                }

                v.bound = bound(v, n, W, arr);

                if (v.bound > maxProfit) {
                    queue.add(v);
                }

                v = new Node(0, 0, 0);
                v.level = u.level + 1;
                v.weight = u.weight;
                v.value = u.value;
                v.bound = bound(v, n, W, arr);

                if (v.bound > maxProfit) {
                    queue.add(v);
                }
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int W = 50;
        Item[] arr = {new Item(10, 60), new Item(20, 100), new Item(30, 120)};
        int n = arr.length;
        int maxProfit = knapsack(W, arr, n);
        System.out.println("Maximum profit: " + maxProfit);
    }
}



//Q.2) Write a program to implement Graph Coloring Algorithm

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