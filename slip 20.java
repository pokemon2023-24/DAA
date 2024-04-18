//slip 20
//Q.1) Write a program to implement for finding Topological sorting and determine the time
//complexity for the same. 


import java.util.*;

public class TopologicalSorting {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public TopologicalSorting(int vertices) {
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

        TopologicalSorting graph = new TopologicalSorting(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        long startTime = System.nanoTime(); // Start measuring time

        graph.topologicalSort(); // Perform topological sorting

        long endTime = System.nanoTime(); // End measuring time
        long duration = (endTime - startTime) / 1000000; // Convert nanoseconds to milliseconds

        System.out.println("\nTime taken for topological sorting: " + duration + " milliseconds");

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


//Q.2) Write a program to solve N Queens Problem using Backtracking.
public class NQueens {
    public static void main(String[] args) {
        int n = 4;
        int[] board = new int[n];
        solveNQueens(n, board, 0);
    }

    public static void solveNQueens(int n, int[] board, int row) {
        if (row == n) {
            printBoard(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                solveNQueens(n, board, row + 1);
                // Backtrack if the solution is not feasible
                board[row] = -1;
            }
        }
    }

    public static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(row - i) == Math.abs(col - board[i])) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

//Enter the number of queens (N): 4