import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int[][] left;
    static int[][] right;

    static int[] lHis;
    static int[] rHis;

    static int n;
    static int ans;

    static Point[] points;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        left = new int[n][n];
        right = new int[n][n];
        points = new Point[n * 2 - 1];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int v = Integer.parseInt(st.nextToken());
                board[i][j] = v;
            }
        }
        for (int i = 0; i < n; i++) {
            points[i] = new Point(0, i);
        }
        for (int i = 1; i < n; i++) {
            points[i + n - 1] = new Point(i, n - 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                left[n - i - 1 + j][j] = i + 1;
            }
        }

        for (int col = 1; col < n; col++) {
            for (int i = 0; i < n - col; i++) {
                left[i][col + i] = col + n;
            }
        }
        for (int col = 0; col < n; col++) {
            int x = 0;
            int y = col;
            while (true) {
                if (!(-1 < x && x < n && -1 < y && y < n)) {
                    break;
                }
                right[x][y] = col + 1;
                x = x + 1;
                y = y - 1;
            }
        }

        for (int row = 1; row < n; row++) {
            int x = row;
            int y = n - 1;
            while (true) {
                if (!(-1 < x && x < n && -1 < y && y < n)) {
                    break;
                }
                right[x][y] = row + n;
                x = x + 1;
                y = y - 1;
            }
        }
        lHis = new int[n * 2];
        rHis = new int[n * 2];

        rec(0, 0);
        System.out.println(ans);
    }

    static void rec(int depth, int count) {
        if (depth == n * 2 - 1) {
            ans = Math.max(ans, count);
            return;
        }
        Point point = points[depth];
        int x = point.x - 1;
        int y = point.y + 1;
        boolean flag = true;
        while (true) {
            x += 1;
            y -= 1;
            if (!(-1 < x && x < n && -1 < y && y < n)) {
                break;
            }
            int v = left[x][y];
            int v2 = right[x][y];
            if (lHis[v] == 1 || rHis[v2] == 1) {
                continue;
            }
            if (board[x][y] == 0) {
                continue;
            }
            flag = false;
            lHis[v] = 1;
            rHis[v2] = 1;
            rec(depth + 1, count + 1);
            lHis[v] = 0;
            rHis[v2] = 0;
        }
        if (flag) {
            rec(depth + 1, count);
        }

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
