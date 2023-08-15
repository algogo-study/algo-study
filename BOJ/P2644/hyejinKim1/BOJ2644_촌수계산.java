package ssafy.algorithm.week5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class BOJ2644_촌수계산 {
    static int n, m, a, b;
    static int [][] arr;
    static boolean visited[];
    static int result[];

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
         Scanner sc = new Scanner(new FileInputStream("input2644.txt"));
         n = sc.nextInt();
         arr = new int[n+1][n+1];
         visited = new boolean[n+1];
         result = new int[n+1];

         a = sc.nextInt();
         b = sc.nextInt();
         m = sc.nextInt();

         for(int i=0; i<m; i++) {
             int u = sc.nextInt();
             int v = sc.nextInt();
             arr[u][v] = arr[v][u] = 1;
         }

         DFS(a);
         if(result[b] == 0) {
             System.out.println(-1);
         }else {
             System.out.println(result[b]);
         }
    }

    public static void DFS(int start) {
        visited[start] = true;
        if(start == b) {
            return;
        }

        for(int i=1; i<n+1; i++) {
            if(arr[start][i]==1 && visited[i] == false) {
                result[i] = result[start]+1;
                DFS(i);
            }
        }
    }
}