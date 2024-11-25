package comp359;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int MAX = 1001; 
    static ArrayList<Integer>[] adjList = new ArrayList[MAX];
    static int[] match = new int[MAX]; 
    static boolean[] visited;
    static int n; // Number of nodes in the left set
    static int m; // Number of nodes in the right set
    static int linkCount; // Number of links

    public static void main(String[] args) {
        // initialize adjacency list
        for (int i = 0; i < MAX; i++) {
            adjList[i] = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        // take user input 
        System.out.print("Enter the number of nodes in the left set: ");
        n = scanner.nextInt();

        System.out.print("Enter the number of nodes in the right set: ");
        m = scanner.nextInt();

        System.out.print("Enter the number of links: ");
        linkCount = scanner.nextInt();

        System.out.print("Do you want to input links manually? (yes/no): ");
        String choice = scanner.next().toLowerCase();

        if (choice.equals("yes")) {
            System.out.println("Enter the links (left_node right_node):");
            for (int i = 0; i < linkCount; i++) {
                int leftNode = scanner.nextInt();
                int rightNode = scanner.nextInt();
                adjList[leftNode].add(rightNode);
            }
        } else {
            generateRandomGraph();
        }
        
        // start timer 
        long startTime = System.nanoTime(); 

        int successfulMatches = 0;

        for (int i = 1; i <= n; i++) {
            visited = new boolean[MAX];
            if (dfs(i)) {
                successfulMatches++;
            }
        }

        // end timer 
        long endTime = System.nanoTime(); 
        long duration = endTime - startTime;

        System.out.println(successfulMatches + " matches successful");
        System.out.println("Runtime: " + duration / 1e6 + " ms");

        // output matched pairs
        for (int i = 1; i <= m; i++) { 
            if (match[i] != 0) {
                System.out.println(match[i] + " => " + i);
            }
        }

        scanner.close();
    }

    // DFS function to find an augmenting path
    public static boolean dfs(int u) {
        for (int v : adjList[u]) {
            if (visited[v]) continue;
            visited[v] = true;
           
            if (match[v] == 0 || dfs(match[v])) {
                match[v] = u;
                return true;
            }
        }
        return false;
    }

    // generate a random bipartite graph
    public static void generateRandomGraph() {
        Random random = new Random();

        for (int i = 0; i < linkCount; i++) {
            int leftNode = random.nextInt(n) + 1; // random node from left set (1 to n)
            int rightNode = random.nextInt(m) + 1; // random node from right set (1 to m)

            // add link, if it isnt already there
            if (!adjList[leftNode].contains(rightNode)) {
                adjList[leftNode].add(rightNode);
            }
        }

        System.out.println("Generated a random bipartite graph with " + n + " left nodes, " + m + " right nodes, and " + linkCount + " links.");
    }
}
