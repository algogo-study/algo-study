package SWEA.P5653.bluepeng9;

import java.io.*;
import java.util.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, k;

    static HashSet<Point> set;

    static HashMap<Point, Info> map;

    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());

        for (int it = 1; it < tc + 1; it++) {

            String[] split = br.readLine().split(" ");
            n = Integer.parseInt(split[0]);
            m = Integer.parseInt(split[1]);
            k = Integer.parseInt(split[2]);

            map = new HashMap<>();
            set = new HashSet<>();

            for (int i = 0; i < n; i++) {
                String[] split1 = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    int v = Integer.parseInt(split1[j]);
                    if (v == 0) {
                        continue;
                    }
                    Point key = new Point(i, j);
                    map.put(key, new Info(v, 0));
                }
            }

            for (int i = 1; i < k + 1; i++) {
                check(i);
            }

            int count = 0;
            for (Map.Entry<Point, Info> entry : map.entrySet()) {
                Info info = entry.getValue();
                if (info.createdAt + info.life + info.life <= k) {
                    continue;
                }
                count += 1;
            }
            bw.write(String.format("#%d %d\n", it, count));
        }
        bw.flush();
    }

    private static void check(int time) {

        HashMap<Point, Info> newMap = new HashMap<Point, Info>();

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};


        for (Point point : map.keySet()) {
            int x = point.x;
            int y = point.y;
            Info curInfo = map.get(point);
            int life = curInfo.life;

            //아직 활성화 안 됨
            if (curInfo.createdAt + curInfo.life >= time) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                Point key = new Point(nx, ny);

                if (map.containsKey(key)) {
                    continue;
                }
                if (set.contains(key)) {
                    continue;
                }

                Info info = newMap.getOrDefault(key, new Info(life, time));
                if (info.life < life) {
                    info = new Info(life, time);
                }

                newMap.put(key, info);
            }
        }
        map.putAll(newMap);

        for (Object tp : map.keySet().toArray()) {
            Point point = (Point) tp;
            Info info = map.get(point);

            if (info.createdAt + info.life + info.life <= time) {
                map.remove(point);
                set.add(point);
            }
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

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static class Info {
        int life;
        int createdAt;

        public Info(int life, int createdAt) {
            this.life = life;
            this.createdAt = createdAt;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "life=" + life +
                    ", createdAt=" + createdAt +
                    '}';
        }
    }
}
