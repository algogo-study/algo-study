package BOJ.P2468.bluepeng9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        int ans = 1;
        for (int i = 1; i < 100; i++) {
            int v = rain(i);
            ans = Math.max(ans, v);
        }
        System.out.println(ans);
    }

    private static int rain(int amount) {


        int[][] vis = new int[n][n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == 0 && amount < board[i][j]) {
                    count += 1;
                    check(i, j, vis, amount);
                }
            }
        }
        return count;
    }

    private static void check(int ix, int iy, int[][] vis, int amount) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(ix, iy));

        vis[ix][iy] = 1;
        int[] dx = {1, -1, 0, 0,};
        int[] dy = {0, 0, 1, -1};

        while (q.size() > 0) {
            Point pop = q.pop();
            int x = pop.x;
            int y = pop.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    continue;
                }
                if (vis[nx][ny] != 0) {
                    continue;
                }
                if (board[nx][ny] <= amount) {
                    continue;
                }
                vis[nx][ny] = 1;
                q.add(new Point(nx, ny));
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
