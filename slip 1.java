//slip 1
//Q1 Write a program to sort a list of n numbers in ascending order using selection sort and determine the time required to sort the elements
import java.util.Arrays;

public class SelectionSort_1 {

    public static void main(String[] args) {
        int[] arr = {74, 25, 42, 25, 61};

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









//Q.2) Write a program to sort a given set of elements using the Quick sort method and determine
//the time required to sort the elements. Repeat the experiment for different values of n, the
//number of elements in the list to be sorted. The elements can be read from a file or can be
//generated using the random number generator.

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