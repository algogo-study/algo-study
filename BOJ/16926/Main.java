import java.io.*;
import java.util.ArrayDeque;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, r;

    static boolean[][] vis;
    static int[][] board;

    public static void main(String[] args) throws IOException {

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        r = Integer.parseInt(split[2]);

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(split[j]);
            }
        }



        for (int i = 0; i < r; i++) {
            vis = new boolean[n + 2][m + 2];
            rec(0, 0, n - 1, m - 1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(board[i][j] + " ");
            }
            bw.write("\n");

        }
        bw.flush();


    }

    public static void rec(int ltx, int lty, int rbx, int rby) {
        if (rbx - ltx < 1 || rby - lty < 1) {
            return;
        }
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(ltx, lty));
        vis[ltx][lty] = true;
        int dir = 0;

        int bef = board[ltx][lty + 1];
        while (q.size() > 0) {
            Point p = q.pop();
            int nx = p.x + dx[dir];
            int ny = p.y + dy[dir];
            int cur = board[p.x][p.y];
            board[p.x][p.y] = bef;

            if (!(ltx <= nx && nx <= rbx && lty <= ny && ny <= rby)) {
                dir += 1;
                nx = p.x + dx[dir];
                ny = p.y + dy[dir];
            }
            if (vis[nx][ny]) {
                break;
            }
            bef = cur;


            q.add(new Point(nx, ny));
            vis[nx][ny] = true;
        }

        rec(ltx + 1, lty + 1, rbx - 1, rby - 1);

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