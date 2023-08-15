package SWEA.P1220.bluepeng9;

import java.io.*;
import java.util.StringTokenizer;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n;

    public static void main(String[] args) throws IOException {

        for (int it = 1; it < 11; it++) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = 0;

            for (int col = 0; col < n; col++) {
                boolean metRed = false;
                for (int row = 0; row < n; row++) {
                    int cur = board[row][col];
                    if (cur == 2 && metRed) {
                        ans += 1;
                        metRed = false;
                    }
                    if (cur == 1) {
                        metRed = true;
                    }
                }
            }

            System.out.printf("#%d %d\n", it, ans);
        }
    }

}