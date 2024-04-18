//slip 17
//Q.1) Write a program to implement solve ‘N’ Queens Problem using Backtracking.

import java.util.Scanner;

public class NQueens {
    static int[] board;
    static int N;

    public static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(i - row) == Math.abs(board[i] - col)) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
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

    public static void solveNQueens(int row) {
        if (row == N) {
            printBoard();
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                solveNQueens(row + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of queens (N): ");
        N = scanner.nextInt();
        board = new int[N];

        solveNQueens(0);

        scanner.close();
    }
}

// Enter the number of queens (N): 4

//Q.2) Write a program to find out solution for 0/1 knapsack problem. 

import java.util.*;

public class KnapsackProblem_25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        int[] weights = new int[numItems];
        int[] values = new int[numItems];

        System.out.println("Enter the weight and value of each item:");
        for (int i = 0; i < numItems; i++) {
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        int maxValue = knapsack(weights, values, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);

        scanner.close();
    }

    public static int knapsack(int[] weights, int[] values, int capacity) {
        int numItems = weights.length;
        int[][] dp = new int[numItems + 1][capacity + 1];

        for (int i = 1; i <= numItems; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[numItems][capacity];
    }
}


// Enter the number of items: 3
// Enter the weight and value of each item:
// 5 10
// 3 7
// 4 8
// Enter the capacity of the knapsack: 7

