//slip 10
//Q.1) Write a program to implement Huffman Code using greedy methods

import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency - o.frequency;
    }
}

public class HuffmanCoding_10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the string to encode: ");
        String inputString = scanner.nextLine();

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : inputString.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode mergedNode = new HuffmanNode('$', left.frequency + right.frequency);
            mergedNode.left = left;
            mergedNode.right = right;
            priorityQueue.add(mergedNode);
        }

        HuffmanNode root = priorityQueue.peek();
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        scanner.close();
    }

    private static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null)
            return;

        if (root.data != '$') {
            huffmanCodes.put(root.data, code);
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }
}


// Enter the string to encode:
// hello world

//Q-2) Write a program to solve 4 Queens Problem using Backtracking

import java.util.Scanner;

public class FourQueen_20 {
    static int[] board;

    public static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(i - row) == Math.abs(board[i] - col)) {
                return false;
            }
        }
        return true;
    }

    public static void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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

    public static void solveFourQueens(int row) {
        if (row == 4) {
            printBoard();
            return;
        }

        for (int col = 0; col < 4; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                solveFourQueens(row + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Solving 4 Queens Problem using Backtracking");
        board = new int[4];

        solveFourQueens(0);

        scanner.close();
    }
}
