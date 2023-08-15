package BOJ.P17135.bluepeng9;

import java.io.*;
import java.util.*;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int m;
    static int d;
    static int enemy;
    static int ans = Integer.MIN_VALUE;

    static int[] bow = new int[3];

    static int[][] board;
    static int[][] copied;

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        d = Integer.parseInt(split[2]);

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] split1 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(split1[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    enemy += 1;
                }
            }
        }

        rec(0, 0);
        System.out.println(ans);
    }


    static void rec(int d, int k) {
        if (d == 3) {
            copied = new int[n][m];
            for (int i = 0; i < n; i++) {
                copied[i] = Arrays.copyOf(board[i], m);
            }
            check();

            int sum = 0;
            for (int[] ints : copied) {
                for (int i = 0; i < m; i++) {
                    if (ints[i] == 1) {
                        sum +=1 ;
                    }
                }
            }
            ans = Math.max(enemy - sum, ans);

            return;
        }

        for (int i = k; i < m; i++) {
            bow[d] = i;
            rec(d + 1, i + 1);
        }
    }

    private static void check() {
        for (int i = n - 1; i >= 0; i--) {
            int v = go(i);
            ans += v;
        }
    }

    private static int go(int curRow) {
        int diff = n - curRow;
        int ans = 0;

        ArrayList<PriorityQueue<Point>> arr = new ArrayList<>();
        Comparator<Point> cp = ((o1, o2) -> {
            if (o1.d == o2.d) {
                return Integer.compare(o1.y, o2.y);
            }
            return Integer.compare(o1.d, o2.d);
        });
        arr.add(new PriorityQueue<>(cp));
        arr.add(new PriorityQueue<>(cp));
        arr.add(new PriorityQueue<>(cp));

        for (int i = 0; i <= curRow; i++) {
            for (int j = 0; j < m; j++) {
                if (copied[i][j] == 1) {
                    for (int k = 0; k < 3; k++) {
                        int dist = calc(new int[]{i, j}, new int[]{n, bow[k]});
                        if (dist < d + diff) {
                            arr.get(k).add(new Point(i, j, dist));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            Point peek = arr.get(i).peek();
            if (peek != null) {
                copied[peek.x][peek.y] = 0;
            }
        }
        return ans;
    }

    private static int calc(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    static class Point {
        int x;
        int y;
        int d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }

}
