import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int k, w, h;
    static int[][] board;
    static int[][][] vis;

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        vis = new int[h][w][k + 1];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                for (int l = 0; l < k + 1; l++) {
                    vis[i][j][l] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < h; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st1.nextToken());
            }
        }

        bfs();
        int ans = Integer.MAX_VALUE;
        for (int i : vis[h - 1][w - 1]) {
            ans = Math.min(ans, i);
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans - 1);
        }
    }

    static void bfs() {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(0, 0, 0));
        vis[0][0][0] = 1;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int[] dx2 = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy2 = {-2, 2, -2, 2, -1, 1, -1, 1};

        while (!q.isEmpty()) {
            Point p = q.pop();
            int x = p.x;
            int y = p.y;
            int jump = p.jump;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!canGo(nx, ny)) {
                    continue;
                }
                if (vis[nx][ny][jump] <= vis[x][y][jump] + 1) {
                    continue;
                }
                vis[nx][ny][jump] = vis[x][y][jump] + 1;
                q.add(new Point(nx, ny, jump));
            }
            if (jump >= k) {
                continue;
            }
            for (int i = 0; i < 8; i++) {
                int nx = x + dx2[i];
                int ny = y + dy2[i];
                int nJump = jump + 1;

                if (!canGo(nx, ny)) {
                    continue;
                }
                if (vis[nx][ny][nJump] <= vis[x][y][jump] + 1) {
                    continue;
                }
                vis[nx][ny][nJump] = vis[x][y][jump] + 1;
                q.add(new Point(nx, ny, nJump));
            }
        }
    }

    static boolean canGo(int x, int y) {
        if (!(-1 < x && x < h && -1 < y && y < w)) {
            return false;
        }
        return board[x][y] != 1;
    }


    static class Point {
        int x;
        int y;
        int jump;

        public Point(int x, int y, int jump) {
            this.x = x;
            this.y = y;
            this.jump = jump;
        }
    }

}
