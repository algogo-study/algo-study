import java.io.*;
import java.util.ArrayList;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int k;
    static int h;
    static char[] comm;
    static ArrayList<Point> arr;

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        comm = new char[k * 2];
        String[] split = br.readLine().split(" ");
        for (int i = 0; i < split.length; i++) {
            comm[i] = split[i].charAt(0);
        }
        h = Integer.parseInt(br.readLine());

        arr.add(new Point(0, 0, h));
        rec(0, 0, 0, 0, 0);
        bw.flush();
    }

    static void rec(int depth, int lx, int ly, int rx, int ry) throws IOException {
        if (depth == 2 * k) {
            int len = (int) Math.sqrt(arr.size());
            int[][] board = new int[len][len];

            lx = Math.abs(lx);
            ly = Math.abs(ly);
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    Point point = arr.get(i * len + j);
                    board[point.x + lx][point.y + ly] = point.d;
                }
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    bw.write(board[i][j] + " ");
                }
                bw.write("\n");
            }
            return;
        }
        int minx = lx;
        int miny = ly;
        int maxx = rx;
        int maxy = ry;
        char curComm = comm[2 * k - depth - 1];
        int len = arr.size();
        for (int i = 0; i < len; i++) {
            Point point1 = get(curComm, arr.get(i), new Point(lx, ly, 0), new Point(rx, ry, 0));
            minx = Math.min(minx, point1.x);
            miny = Math.min(miny, point1.y);
            maxx = Math.max(maxx, point1.x);
            maxy = Math.max(maxy, point1.y);
            arr.add(point1);
        }
        rec(depth + 1, minx, miny, maxx, maxy);
    }

    private static Point get(char curComm, Point p, Point l, Point r) {
        if (curComm == 'R' || curComm == 'L') {
            int v = 0;
            if (p.d == 1 || p.d == 0) {
                v = p.d == 1 ? 0 : 1;
            } else {
                v = p.d == 2 ? 3 : 2;
            }

            if (curComm == 'R') {
                int diff = Math.abs(l.y - p.y) + 1;
                return new Point(p.x, l.y - diff, v);
            }
            int diff = Math.abs(r.y - p.y) + 1;
            return new Point(p.x, r.y + diff, v);
        }
        int v = 0;
        if (p.d == 1 || p.d == 3) {
            v = p.d == 1 ? 3 : 1;
        } else {
            v = p.d == 2 ? 0 : 2;
        }
        if (curComm == 'U') {
            int diff = Math.abs(r.x - p.x) + 1;
            return new Point(r.x + diff, p.y, v);
        }
        int diff = Math.abs(l.x - p.x) + 1;
        return new Point(l.x - diff, p.y, v);
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
            return "Point{" + "x=" + x + ", y=" + y + ", d=" + d + '}';
        }
    }
}
