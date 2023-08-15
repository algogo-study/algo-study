package BOJ.P16236;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int curX, curY, curSize = 2, left = 2;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int[][] board;
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int size = Integer.parseInt(st.nextToken());
                if (size == 9) {
                    curX = i;
                    curY = j;
                    continue;
                }
                board[i][j] = size;
            }
        }

        while (true) {
            Point point = find();
            if (point == null) {
                break;
            }
            curX = point.x;
            curY = point.y;
            board[curX][curY] = 0;
            left -= 1;
            if (left == 0) {
                curSize += 1;
                left = curSize;
            }
        }
        System.out.println(ans);
    }

    private static Point find() {
        ArrayDeque<Point> q = new ArrayDeque<>();
        int[][] vis = new int[n][n];
        vis[curX][curY] = 1;
        Point initPoint = new Point(curX, curY);
        q.add(initPoint);
        ArrayList<Point> candi = new ArrayList<>();

        while (q.size() > 0) {
            Point p = q.pop();
            int x = p.x;
            int y = p.y;
            if (board[x][y] < curSize && board[x][y] != 0) {
                candi.add(new Point(x, y));
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    continue;
                }
                if (vis[nx][ny] > 0) {
                    continue;
                }
                if (board[nx][ny] > curSize) {
                    continue;
                }
                vis[nx][ny] = vis[x][y] + 1;
                q.add(new Point(nx, ny));
            }
        }
        if (candi.size() == 0) {
            return null;
        }
        candi.sort((o1, o2) -> {
            int dist1 = vis[o1.x][o1.y];
            int dist2 = vis[o2.x][o2.y];
            if (dist1 != dist2) {
                return Integer.compare(dist1, dist2);
            }
            if (o1.x == o2.x) {
                return Integer.compare(o1.y, o2.y);
            }
            return Integer.compare(o1.x, o2.x);
        });
        Point point = candi.get(0);
        ans += vis[point.x][point.y] - 1;
        return point;
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
