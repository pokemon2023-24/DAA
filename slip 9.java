//slip 9
//Q.1) Write a program to implement optimal binary search tree and also calculate the best-case
//complexity. 
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

        double[][] cost = new double[n + 2][n + 1];
        double[][] weight = new double[n + 2][n + 1];

        for (int i = 1; i <= n + 1; i++) {
            cost[i][i - 1] = 0;
            weight[i][i - 1] = 0;
        }

        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                cost[i][j] = Double.MAX_VALUE;
                weight[i][j] = weight[i][j - 1] + probabilities[j - 1];
                for (int r = i; r <= j; r++) {
                    double c = cost[i][r - 1] + cost[r + 1][j] + weight[i][j];
                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                    }
                }
            }
        }

        // Calculate best case and worst case
        double bestCase = 0;
        double worstCase = 0;
        for (int i = 0; i < n; i++) {
            bestCase += probabilities[i] * (i + 1);
            worstCase += probabilities[i] * (n - i);
        }

        System.out.println("Optimal Binary Search Tree Cost: " + cost[1][n]);
        System.out.println("Best Case Cost: " + bestCase);
        System.out.println("Worst Case Cost: " + worstCase);

        scanner.close();
    }
}

/*Enter the number of keys: 4
Enter the keys:
1
2
3
4
Enter the probabilities of each key:
10
20
30
40
Optimal Binary Search Tree Cost: 180.0
Best Case Cost: 300.0
Worst Case Cost: 200.0 */


//Q.2) Write a program to implement Sum of Subset by Backtracking

import java.util.*;

public class SubsetSum_18 {
    static boolean[] include;
    static int[] weights;
    static int targetSum;

    public static void findSubsets(int i, int currentSum, int remainingSum) {
        if (currentSum == targetSum) {
            System.out.print("Subset with sum " + targetSum + " found: {");
            for (int j = 0; j < include.length; j++) {
                if (include[j]) {
                    System.out.print(weights[j] + " ");
                }
            }
            System.out.println("}");
            return;
        } else if (i >= weights.length || currentSum + remainingSum < targetSum) {
            return;
        } else {
            include[i] = true;
            findSubsets(i + 1, currentSum + weights[i], remainingSum - weights[i]);
            include[i] = false;
            findSubsets(i + 1, currentSum, remainingSum - weights[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        weights = new int[n];
        include = new boolean[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter the target sum: ");
        targetSum = scanner.nextInt();

        int totalSum = Arrays.stream(weights).sum();

        if (totalSum < targetSum) {
            System.out.println("No subset possible");
        } else {
            int remainingSum = totalSum;
            findSubsets(0, 0, remainingSum);
        }

        scanner.close();
    }
}

// Enter the number of elements: 5
// Enter the elements:
// 2
// 4
// 6
// 8
// 10
// Enter the target sum: 14
