package BOJ.P2146.bluepeng9;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;

    static int[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }

        int[][] vis = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 || vis[i][j] == 1) {
                    continue;
                }
                fill(i, j, count, vis);
                count += 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0 || vis[i][j] == 1) {
                    continue;
                }
                fill(i, j, count, vis);
                count += 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    continue;
                }
                go(i, j);
            }
        }
        System.out.println(ans);
    }

    private static void go(int ix, int iy) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(ix, iy));
        int[][] vis = new int[n][n];

        while (q.size() > 0) {
            Point cur = q.pop();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    continue;
                }
                if (vis[nx][ny] != 0) {
                    continue;
                }
                if (board[nx][ny] != 0) {
                    if(board[nx][ny] != board[ix][iy]) {
                        ans = Integer.min(vis[x][y], ans);
                        return;
                    }
                    continue;
                }
                vis[nx][ny] = vis[x][y] + 1;
                q.add(new Point(nx, ny));
            }
        }
    }
    private static void fill(int ix, int iy, int value, int[][] vis) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(ix, iy));
        vis[ix][iy] = 1;
        board[ix][iy] = value;

        while (q.size() > 0) {
            Point cur = q.pop();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    continue;
                }
                if (vis[nx][ny] == 1) {
                    continue;
                }
                if (board[nx][ny] == 0) {
                    continue;
                }
                vis[nx][ny] = 1;
                board[nx][ny] = value;
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
