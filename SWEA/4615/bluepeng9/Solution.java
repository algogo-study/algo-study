import java.io.*;
import java.util.*;

public class Solution
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n, m;

    public static void main(String[] args) throws Exception {
        int tc = Integer.parseInt(br.readLine());

        for (int line = 1; line < tc + 1; line++) {
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            m = Integer.parseInt(s[1]);
            board = new int[n][n];

            board[(n / 2) - 1][(n / 2) - 1] = 2;
            board[(n / 2) - 1][(n / 2) - 1 + 1] = 1;
            board[(n / 2) - 1 + 1][(n / 2) - 1] = 1;
            board[(n / 2) - 1 + 1][(n / 2) - 1 + 1] = 2;

            for (int i = 0; i < m; i++) {
                s = br.readLine().split(" ");
                int x = Integer.parseInt(s[0]) - 1;
                int y = Integer.parseInt(s[1]) - 1;
                int color = Integer.parseInt(s[2]);
                board[x][y] = color;
                check(x, y, color);
            }

            int white = 0;
            int black = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 1) {
                        black += 1;
                        continue;
                    } else if (board[i][j] == 2) {
                        white += 1;
                        continue;
                    }
                }
            }

            System.out.printf("#%d %d %d\n", line, black, white);

        }
    }

    static void check(int a, int b, int color) {
        for (int i = 0; i < 8; i++) {
            check2(a, b, color, i);
        }

    }

    static void check2(int a, int b, int color, int dir) {
        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

        ArrayDeque<Point> q = new ArrayDeque<>();
        ArrayDeque<Point> stack = new ArrayDeque<>();
        q.add(new Point(a, b));

        while (true) {
            Point p = q.pop();
            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];
            if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                return;
            }
            if (board[nx][ny] == color) {
                while (stack.size() > 0) {
                    Point p2 = stack.pop();
                    board[p2.x][p2.y] = color;
                }
                return;
            }
            if (board[nx][ny] == 0) {
                return;
            }
            q.add(new Point(nx, ny));
            stack.add(new Point(nx, ny));
        }

    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}