import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n, l, r;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        l = Integer.parseInt(split[1]);
        r = Integer.parseInt(split[2]);
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] split2 = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(split2[j]);
            }
        }
        check();
    }

    static boolean bfs(int ix, int iy, int[][] vis) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(ix, iy));

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        ArrayList<Point> points = new ArrayList<>();
        vis[ix][iy] = 1;
        boolean flag = false;

        while (q.size() > 0) {
            Point p = q.pop();
            int x = p.x;
            int y = p.y;

            points.add(p);

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    continue;
                }
                if (vis[nx][ny] == 1) {
                    continue;
                }

                int diff = Math.abs(board[x][y] - board[nx][ny]);

                if (!(l <= diff && diff <= r)) {
                    continue;
                }
                q.add(new Point(nx, ny));
                flag = true;
                vis[nx][ny] = 1;
            }
        }

        int sum = 0;
        for (Point point : points) {
            int x = point.x;
            int y = point.y;
            sum += board[x][y];
        }

        for (Point point : points) {
            int x = point.x;
            int y = point.y;
            board[x][y] = sum / points.size();
        }

        return flag;
    }

    static void check() {
        int day = 0;
        while (true) {
            int[][] vis = new int[n][n];
            boolean flag = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (vis[i][j] == 1) {
                        continue;
                    }
                    flag = flag | bfs(i, j, vis);
                }
            }
            if (!flag) {
                break;
            }
            day += 1;
        }
        System.out.println(day);
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
