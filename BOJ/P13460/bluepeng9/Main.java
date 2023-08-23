import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static char[][] board;
    static int[] history;
    static Point red, blue, hole;

    static int min = 11;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        history = new int[10];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                if (c == 'R') {
                    red = new Point(i, j);
                    board[i][j] = '.';
                    continue;
                }
                if (c == 'B') {
                    blue = new Point(i, j);
                    board[i][j] = '.';
                    continue;
                }
                if (c == 'O') {
                    hole = new Point(i, j);
                    board[i][j] = '.';
                    continue;
                }
                board[i][j] = c;
            }
        }

        for (int i = 0; i < 11; i++) {
            rec(0, i);
        }
        if (min == 11) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }

    static void simul(int depth) {
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        boolean rGoal = false;
        for (int i = 0; i < depth; i++) {
            int rBlock = 0;
            int bBlock = 0;
            while (true) {
                if (rBlock > 1 && bBlock > 1) {
                    if (rGoal) {
                        min = Math.min(i + 1, min);
                        return;
                    }
                    break;
                }
                int nx = red.x + dx[history[i]];
                int ny = red.y + dy[history[i]];
                if (rGoal) {
                    rBlock += 1;
                } else if (board[nx][ny] == '#') {
                    rBlock += 1;
                } else if (nx == blue.x && ny == blue.y) {
                    rBlock += 1;
                } else if (nx == hole.x && ny == hole.y) {
                    rGoal = true;
                    red.x = -100;
                    red.y = -100;
                } else {
                    red.x = nx;
                    red.y = ny;
                }

                nx = blue.x + dx[history[i]];
                ny = blue.y + dy[history[i]];
                if (board[nx][ny] == '#') {
                    bBlock += 1;
                } else if (nx == red.x && ny == red.y) {
                    bBlock += 1;
                } else if (nx == hole.x && ny == hole.y) {
                    return;
                } else {
                    blue.x = nx;
                    blue.y = ny;
                }
            }
        }
    }

    static void rec(int depth, int k) {
        if (depth == k) {
            Point rCopy = new Point(red.x, red.y);
            Point bCopy = new Point(blue.x, blue.y);
            simul(depth);
            red = rCopy;
            blue = bCopy;
            return;
        }

        for (int i = 0; i < 4; i++) {
            history[depth] = i;
            rec(depth + 1, k);
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
