//slip 2
//Q.1) Write a program to sort n randomly generated elements using Heapsort method.
import java.util.Arrays;

public class HeapSort_1 {

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        System.out.println("Original Array: " + Arrays.toString(arr));

        long startTime = System.currentTimeMillis();
        heapSort(arr);
        long endTime = System.currentTimeMillis();

        System.out.println("Heap Sort: " + Arrays.toString(arr));
        System.out.println("Time Complexity: " + (endTime - startTime) + " milliseconds");
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}






// Q.2) Write a program to implement Strassen’s Matrix multiplication  

import java.util.Scanner;

public class StrassenMatrixMultiplication_4 {
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: if the matrices are 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        }

        // Divide matrices into submatrices
        int[][] A11 = new int[n / 2][n / 2];
        int[][] A12 = new int[n / 2][n / 2];
        int[][] A21 = new int[n / 2][n / 2];
        int[][] A22 = new int[n / 2][n / 2];

        int[][] B11 = new int[n / 2][n / 2];
        int[][] B12 = new int[n / 2][n / 2];
        int[][] B21 = new int[n / 2][n / 2];
        int[][] B22 = new int[n / 2][n / 2];

        // Split matrices into submatrices
        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, n / 2);
        splitMatrix(A, A21, n / 2, 0);
        splitMatrix(A, A22, n / 2, n / 2);

        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, n / 2);
        splitMatrix(B, B21, n / 2, 0);
        splitMatrix(B, B22, n / 2, n / 2);

        // Calculate Strassen's submatrices
        int[][] S1 = subtractMatrices(B12, B22);
        int[][] S2 = addMatrices(A11, A12);
        int[][] S3 = addMatrices(A21, A22);
        int[][] S4 = subtractMatrices(B21, B11);
        int[][] S5 = addMatrices(A11, A22);
        int[][] S6 = addMatrices(B11, B22);
        int[][] S7 = subtractMatrices(A12, A22);
        int[][] S8 = addMatrices(B21, B22);
        int[][] S9 = subtractMatrices(A11, A21);
        int[][] S10 = addMatrices(B11, B12);

        // Calculate products
        int[][] P1 = strassenMultiply(A11, S1);
        int[][] P2 = strassenMultiply(S2, B22);
        int[][] P3 = strassenMultiply(S3, B11);
        int[][] P4 = strassenMultiply(A22, S4);
        int[][] P5 = strassenMultiply(S5, S6);
        int[][] P6 = strassenMultiply(S7, S8);
        int[][] P7 = strassenMultiply(S9, S10);

        // Calculate result submatrices
        int[][] C11 = addMatrices(subtractMatrices(addMatrices(P5, P4), P2), P6);
        int[][] C12 = addMatrices(P1, P2);
        int[][] C21 = addMatrices(P3, P4);
        int[][] C22 = subtractMatrices(subtractMatrices(addMatrices(P5, P1), P3), P7);

        // Combine result submatrices into one matrix
        int[][] C = new int[n][n];
        joinMatrices(C11, C, 0, 0);
        joinMatrices(C12, C, 0, n / 2);
        joinMatrices(C21, C, n / 2, 0);
        joinMatrices(C22, C, n / 2, n / 2);

        return C;
    }

    public static void splitMatrix(int[][] matrix, int[][] submatrix, int startRow, int startCol) {
        for (int i = 0; i < submatrix.length; i++) {
            for (int j = 0; j < submatrix.length; j++) {
                submatrix[i][j] = matrix[i + startRow][j + startCol];
            }
        }
    }

    public static void joinMatrices(int[][] submatrix, int[][] matrix, int startRow, int startCol) {
        for (int i = 0; i < submatrix.length; i++) {
            for (int j = 0; j < submatrix.length; j++) {
                matrix[i + startRow][j + startCol] = submatrix[i][j];
            }
        }
    }

    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the dimension of the square matrix: ");
        int n = scanner.nextInt();
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];

        System.out.println("Enter elements of matrix A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter elements of matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);

        int[][] C = strassenMultiply(A, B);

        System.out.println("Resultant Matrix (A * B):");
        printMatrix(C);

        scanner.close();
    }
}



// Enter the dimension of the square matrix: 2
// Enter elements of matrix A:
// 1 2
// 3 4
// Enter elements of matrix B:
// 5 6
// 7 8