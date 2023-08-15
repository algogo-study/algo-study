package SWEA.P5644.bluepeng9;

import java.io.*;
import java.util.*;

public class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] aMove;
    static int[] bMove;

    static HashMap<Point, ArrayList<Integer>> board;

    static Point[] batteries;
    static int[] powers;
    static int[] covers;
    static int sum;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
        for (int i = 1; i < tc + 1; i++) {
            String[] split = br.readLine().split(" ");
            m = Integer.parseInt(split[0]);
            int a = Integer.parseInt(split[1]);
            String[] split1 = br.readLine().split(" ");

            aMove = new int[m];
            bMove = new int[m];
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(split1[j]);
                aMove[j] = v;
            }

            board = new HashMap<>();

            split1 = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(split1[j]);
                bMove[j] = v;
            }
            powers = new int[a];
            covers = new int[a];
            batteries = new Point[a];
            for (int j = 0; j < a; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                int x = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                batteries[j] = new Point(x, y);
                powers[j] = p;
                covers[j] = c;
            }
            move();
            System.out.printf("#%d %d\n", i, sum);
        }
    }

    private static void move() {
        Point aPoint = new Point(0, 0);
        Point bPoint = new Point(9, 9);

        sum = 0;
        int[] dx = {0, -1, 0, 1, 0};
        int[] dy = {0, 0, 1, 0, -1};

        for (int i = 0; i < m; i++) {

            sum = getSum(aPoint, bPoint, sum);
            aPoint = new Point(aPoint.x + dx[aMove[i]], aPoint.y + dy[aMove[i]]);
            bPoint = new Point(bPoint.x + dx[bMove[i]], bPoint.y + dy[bMove[i]]);
        }
        sum = getSum(aPoint, bPoint, sum);
    }

    private static int getSum(Point aPoint, Point bPoint, int sum) {
        ArrayList<Integer> aBatter = getBat(aPoint);

        aBatter.sort((o1, o2) -> -1 * Integer.compare(powers[o1], powers[o2]));

        ArrayList<Integer> bBatter = getBat(bPoint);
        bBatter.sort((o1, o2) -> -1 * Integer.compare(powers[o1], powers[o2]));

        // 0 0
        if (aBatter.size() == 0 && bBatter.size() == 0) {
            return sum;
        }
        // x , 0
        if (aBatter.size() >= 1 && bBatter.size() == 0) {
            return sum + powers[aBatter.get(0)];
        }
        // 0, x
        if (bBatter.size() >= 1 && aBatter.size() == 0) {
            return sum + powers[bBatter.get(0)];
        }
        // x x
        if (bBatter.size() > 1 && aBatter.size() > 1) {
            if (aBatter.get(0).equals(bBatter.get(0))) {
                int max = Math.max(powers[aBatter.get(1)], powers[bBatter.get(1)]);
                return sum + max + powers[aBatter.get(0)];
            }
            return sum + powers[aBatter.get(0)] + powers[bBatter.get(0)];
        }
        // x, 1
        if (aBatter.size() > 1 && bBatter.size() == 1) {
            if (aBatter.get(0).equals(bBatter.get(0))) {
                return sum + powers[aBatter.get(1)] + powers[bBatter.get(0)];
            }
            return sum + powers[aBatter.get(0)] + powers[bBatter.get(0)];
        }

        // 1, x
        if (bBatter.size() > 1 && aBatter.size() == 1) {
            if (bBatter.get(0).equals(aBatter.get(0))) {
                return sum + powers[bBatter.get(1)] + powers[aBatter.get(0)];
            }
            return sum + powers[aBatter.get(0)] + powers[bBatter.get(0)];
        }

        if (bBatter.get(0).equals(aBatter.get(0))) {
            return sum + powers[aBatter.get(0)];
        }
        return sum + powers[aBatter.get(0)] + powers[bBatter.get(0)];

    }

    static ArrayList<Integer> getBat(Point x) {
        ArrayList<Integer> points = new ArrayList<>();

        for (int i = 0; i < batteries.length; i++) {
            boolean result = canCharge(i, x);
            if (result) {
                points.add(i);
            }
        }

        return points;
    }

    static boolean canCharge(int battery, Point p) {
        Point bat = batteries[battery];
        int canCov = covers[battery];

        int dist = Math.abs(bat.x - p.x) + Math.abs(bat.y - p.y);
        return dist <= canCov;
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