import java.io.*;
import java.util.*;

public class Main {

    static int n, k;
    static int[][] board;
    static boolean end;
    static Unit[] units;
    static HashMap<Point, ArrayDeque<Integer>> map;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] rdxdy = {1, 0, 3, 2};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        units = new Unit[k];
        map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int v = Integer.parseInt(st1.nextToken());
                board[i][j] = v;
            }
        }

        for (int i = 0; i < k; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken()) - 1;
            int y = Integer.parseInt(st1.nextToken()) - 1;
            int d = Integer.parseInt(st1.nextToken()) - 1;
            Unit unit = new Unit(x, y, d);
            units[i] = unit;
            Point key = new Point(x, y);
            ArrayDeque<Integer> q = map.getOrDefault(key, new ArrayDeque<>());
            q.add(i);
            map.put(key, q);
        }

        int count = 0;
        while (true) {
            count += 1;
            if (count > 1000) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < k; i++) {
                Unit curUnit = units[i];
                Point curKey = new Point(curUnit.x, curUnit.y);

                int nx = curUnit.x + dx[curUnit.d];
                int ny = curUnit.y + dy[curUnit.d];

                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) {
                    int tx = curUnit.x + dx[rdxdy[curUnit.d]];
                    int ty = curUnit.y + dy[rdxdy[curUnit.d]];

                    curUnit.d = rdxdy[curUnit.d];
                    if (board[curUnit.x + dx[curUnit.d]][curUnit.y + dy[curUnit.d]] == 2) {
                        continue;
                    }
                    if (board[curUnit.x + dx[curUnit.d]][curUnit.y + dy[curUnit.d]] == 1) {
                        move(i, curUnit, curKey, tx, ty, false);
                    } else {
                        move(i, curUnit, curKey, tx, ty, true);
                    }



                } else if (board[nx][ny] == 2) {
                    int tx = curUnit.x + dx[rdxdy[curUnit.d]];
                    int ty = curUnit.y + dy[rdxdy[curUnit.d]];
                    curUnit.d = rdxdy[curUnit.d];
                    if (!(-1 < tx && tx < n && -1 < ty && ty < n)) {
                        continue;
                    }
                    if (board[tx][ty] == 2) {
                        continue;
                    }
                    if (board[tx][ty] == 1) {
                        move(i, curUnit, curKey, tx, ty, false);
                    } else {
                        move(i, curUnit, curKey, tx, ty, true);
                    }
                } else if (board[nx][ny] == 1) {
                    move(i, curUnit, curKey, nx, ny, false);
                } else {
                    move(i, curUnit, curKey, nx, ny, true);
                }
            }
            if (end) {
                System.out.println(count);
                return;
            }
        }

    }

    private static void move(int i, Unit curUnit, Point curKey, int nx, int ny, boolean reverse) {
        ArrayDeque<Integer> q = map.getOrDefault(curKey, new ArrayDeque<>());
        ArrayDeque<Integer> ready = new ArrayDeque<>();
        while (q.size() > 0) {
            Integer p = q.removeLast();
            ready.add(p);
            if (p == i) {
                break;
            }
        }
        map.put(curKey, q);


        Point nKey = new Point(curUnit.x + dx[curUnit.d], curUnit.y + dy[curUnit.d]);
        ArrayDeque<Integer> nq = map.getOrDefault(nKey, new ArrayDeque<>());

        if (nq.size() + ready.size() >= 4) {
            end = true;
        }
        if (reverse) {
            while (ready.size() > 0) {
                Integer e = ready.removeLast();
                units[e].x = nx;
                units[e].y = ny;
                nq.add(e);
            }
            map.put(nKey, nq);
            return;
        }
        for (Integer integer : ready) {
            units[integer].x = nx;
            units[integer].y = ny;
            nq.add(integer);
        }
        map.put(nKey, nq);
    }

    static class Unit {
        int x;
        int y;
        int d;

        public Unit(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Unit{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }

    static class Point {
        int x;
        int y;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
