// Slip 13 
// Q.1) Write a program to find minimum number of multiplications in Matrix Chain Multiplication. 

import java.util.Scanner;

public class MatrixChainMultiplication_11 {
    public static int minMultiplications(int[] dimensions) {
        int n = dimensions.length - 1;
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of matrices: ");
        int n = scanner.nextInt();

        int[] dimensions = new int[n + 1];
        System.out.println("Enter the dimensions of the matrices:");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the number of rows for matrix " + (i + 1) + ": ");
            int rows = scanner.nextInt();
            System.out.print("Enter the number of columns for matrix " + (i + 1) + ": ");
            int cols = scanner.nextInt();
            dimensions[i] = rows;
            if (i == n - 1) {
                dimensions[i + 1] = cols;
            }
        }

        int minOps = minMultiplications(dimensions);
        System.out.println("Minimum number of multiplications: " + minOps);

        scanner.close();
    }
}

/*Enter the number of matrices: 4
Enter the dimensions of the matrices:
Enter the number of rows for matrix 1: 10
Enter the number of columns for matrix 1: 20
Enter the number of rows for matrix 2: 20
Enter the number of columns for matrix 2: 30
Enter the number of rows for matrix 3: 30
Enter the number of columns for matrix 3: 40
Enter the number of rows for matrix 4: 40
Enter the number of columns for matrix 4: 50
Minimum number of multiplications: 38000*/




// Q.2) Write a program to implement an optimal binary search tree and also calculate the best case and worst-case complexity. 

import java.util.Scanner;

public class OptimalBST_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of keys: ");
        int n = scanner.nextInt();
        double[] probabilities = new double[n];
        String[] keys = new String[n];

        System.out.println("Enter the keys:");
        for (int i = 0; i < n; i++) {
            keys[i] = scanner.next();
        }

        System.out.println("Enter the probabilities of each key:");
        for (int i = 0; i < n; i++) {
            probabilities[i] = scanner.nextDouble();
        }

        double[][] cost = new double[n][n];
        double[][] weight = new double[n][n];

        for (int i = 0; i < n; i++) {
            cost[i][i] = probabilities[i];
            weight[i][i] = probabilities[i];
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                cost[i][j] = Double.MAX_VALUE;
                weight[i][j] = weight[i][j - 1] + probabilities[j];
                for (int r = i; r <= j; r++) {
                    double c = (r > i ? cost[i][r - 1] : 0) + (r < j ? cost[r + 1][j] : 0) + weight[i][j];
                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                    }
                }
            }
        }

        System.out.println("Optimal Binary Search Tree Cost: " + cost[0][n - 1]);

        scanner.close();
    }
}


// Enter the number of keys: 4
// Enter the keys and their frequencies:
// Key 1: 10
// Key 2: 20
// Key 3: 30
// Key 4: 40
