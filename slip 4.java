//slip 4
//Q.1) Write a program to implement a Merge Sort algorithm to sort a given set of elements and determine the time required to sort the elements 
import java.util.Random;

public class Ass1_3 { //MergeSortExample 

    public static void main(String[] args) {
        // Experiment with different values of n
        int[] nValues = {100, 500, 1000, 5000, 10000};
        
        for (int n : nValues) {
            int[] arr = generateRandomArray(n);
            
            // Measure the time required for sorting
            long startTime = System.nanoTime();
            mergeSort(arr);
            long endTime = System.nanoTime();
            
            long elapsedTime = endTime - startTime;
            
            System.out.println("For n=" + n + ", Time taken: " + elapsedTime + " nanoseconds");
        }
    }
     // Merge Sort Algorithm
     public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            
            int[] left = new int[mid];
            System.arraycopy(arr, 0, left, 0, mid);
            
            int[] right = new int[arr.length - mid];
            System.arraycopy(arr, mid, right, 0, arr.length - mid);
            
            mergeSort(left);
            mergeSort(right);
            
            merge(arr, left, right);
        }
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        
        while (j < right.length) {
            arr[k++] = right[j++];
        }
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




// Q.2) Write a program to implement Knapsack problems using Greedy method 

import java.util.*;

public class KnapsackGreedy_8 {
    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static double knapsackGreedy(int capacity, List<Item> items) {
        Collections.sort(items, (a, b) -> Double.compare((double) b.value / b.weight, (double) a.value / a.weight));

        double totalValue = 0;
        int remainingCapacity = capacity;

        for (Item item : items) {
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            } else {
                totalValue += (double) item.value / item.weight * remainingCapacity;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        List<Item> items = new ArrayList<>();
        System.out.println("Enter weights and values of items:");
        for (int i = 0; i < numItems; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items.add(new Item(weight, value));
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        double maxValue = knapsackGreedy(capacity, items);
        System.out.println("Maximum value that can be achieved: " + maxValue);

        scanner.close();
    }
}


// Enter the capacity of the knapsack: 10
// Enter the number of items: 3
// Enter the weight and value of each item:
// Item 1: 2 6
// Item 2: 5 8
// Item 3: 3 7
