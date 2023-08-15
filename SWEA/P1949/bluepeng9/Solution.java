package SWEA.P1949.bluepeng9;

import java.io.*;
import java.util.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k, maxValue;

    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int ans;

    static int[][] vis;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        for (int it = 1; it < tc + 1; it++) {
            String[] split = br.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            k = Integer.parseInt(split[1]);
            board = new int[n][n];
            vis = new int[n][n];
            ans = 0;
            maxValue = 0;

            for (int i = 0; i < n; i++) {
                String[] split1 = br.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    int v = Integer.parseInt(split1[j]);
                    board[i][j] = v;
                    maxValue = Math.max(v, maxValue);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (maxValue != board[i][j]) {
                        continue;
                    }
                    vis[i][j] = 1;
                    dfs(i, j, 1, false);
                    vis[i][j] = 0;
                }
            }
            System.out.printf("#%d %d\n", it, ans);
        }
    }

    private static void dfs(int x, int y, int dist, boolean use) {
        int curV = board[x][y];
        ans = Integer.max(ans, dist);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                continue;
            }
            if (vis[nx][ny] == 1) {
                continue;
            }
            int nv = board[nx][ny];

            if (curV <= nv) {
                if (use) {
                    continue;
                }
                int diff = nv - curV;
                if (diff + 1 <= k) {
                    int temp = board[nx][ny];
                    board[nx][ny] = curV - 1;
                    vis[nx][ny] = 1;
                    dfs(nx, ny, dist + 1, true);
                    vis[nx][ny] = 0;
                    board[nx][ny] = temp;
                }
                continue;
            }
            vis[nx][ny] = 1;
            dfs(nx, ny, dist + 1, use);
            vis[nx][ny] = 0;
        }
    }
}
