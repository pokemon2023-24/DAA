//slip 19
//Q.1) Write a program to determine if a given graph is a Hamiltonian cycle or
//Not.

import java.util.*;

public class HamiltonianCycle_15 {
    private int vertices;
    private LinkedList<Integer>[] adjList;

    public HamiltonianCycle_15(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination) {
        adjList[source].add(destination);
        adjList[destination].add(source);
    }

    public boolean isHamiltonianCycle() {
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        // Check if every vertex is reachable from the first vertex
        if (dfs(0, visited, 1)) {
            // Check if all vertices are visited
            for (boolean v : visited) {
                if (!v) {
                    return false;
                }
            }
            // Check if the last vertex has an edge to the first vertex
            return adjList[0].contains(vertices - 1);
        }
        return false;
    }

    private boolean dfs(int vertex, boolean[] visited, int count) {
        visited[vertex] = true;

        if (count == vertices) {
            return true;
        }

        for (int nextVertex : adjList[vertex]) {
            if (!visited[nextVertex] && dfs(nextVertex, visited, count + 1)) {
                return true;
            }
        }

        visited[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        HamiltonianCycle_15 graph = new HamiltonianCycle_15(vertices);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        if (graph.isHamiltonianCycle()) {
            System.out.println("The given graph is a Hamiltonian cycle.");
        } else {
            System.out.println("The given graph is not a Hamiltonian cycle.");
        }

        scanner.close();
    }
}


// Enter the number of vertices: 5
// Enter the number of edges: 7
// Enter the edges (source destination):
// 0 1
// 1 2
// 2 3
// 3 4
// 4 0
// 0 2
// 1 3


//Q.2) Write a program to show board configuration of 4 queens’ problem.

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