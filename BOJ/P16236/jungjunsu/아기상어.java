import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, target[], map[][];
    static boolean connected = true, history[][];
    static Shark babyShark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        target = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    babyShark = new Shark(j, i, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        int test =0;
        int time = 0;
        // 만약 먹이가 있다면 계속 진행(connected 변수로 먹이 존재 여부 체크)
        do {
            connected = false;
            history = new boolean[N][N];
            // map에 가장 가까운 먹이를 bfs로 탐색
            bfs(babyShark.y, babyShark.x, 0);

            // 먹이가 있다면 먹이 좌표 target 0, 1, 좌표까지의 거리 target[2] 를 통해 상어 정보를 바꿈
            if (connected) {
                time += target[2];
                babyShark.y = target[0];
                babyShark.x = target[1];
                babyShark.stack++;
                if (babyShark.stack == babyShark.size) {
                    babyShark.size++;
                    babyShark.stack = 0;
                }
            }

        } while (connected);

        System.out.println(time);
    }

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    // 상 좌 우 하 순서로 bfs 를 진행
    // 만약 먹이를 찾는다면 해당 먹이의 차수까지 모두 보고 가까운 먹이의 정보를 target배열에 저장
    private static void bfs(int row, int col, int depth) {

        Queue<Point> points = new LinkedList<Point>();
        Queue<Point> ANS = new LinkedList<Point>();

        points.add(new Point(col, row, depth));
        history[row][col] = true;
        int maxDepth = Integer.MAX_VALUE;
        while (!points.isEmpty()) {
            Point point = points.poll();

            if (maxDepth <= point.depth) break;

            for (int v = 0; v < 4; v++) {
                int nr = point.y + dr[v];
                int nc = point.x + dc[v];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] > babyShark.size) continue;
                if (history[nr][nc]) continue;
                if (map[nr][nc] != 0 && map[nr][nc] < babyShark.size) {
                    ANS.add(new Point(nc, nr, point.depth + 1));
                    maxDepth = point.depth + 1;
                    connected = true;
                }
                history[nr][nc] = true;
                points.add(new Point(nc, nr, point.depth + 1));
            }
        }
        if (connected) {
            Point p = ANS.poll();
            target[0] = p.y;
            target[1] = p.x;
            target[2] = p.depth;
            int r = p.y;
            int c = p.x;
            while (!ANS.isEmpty()) {
                p = ANS.poll();
                if (p.y < r) {
                    target[0] = p.y;
                    target[1] = p.x;
                    r = p.y;
                    c = p.x;
                }
                if (p.y == r && p.x < c) {
                    target[1] = p.x;
                    c = p.x;
                }
            }
            map[target[0]][target[1]] = 0;
        }
    }

    // 먹이의 col, row, 거리 정보
    static class Point {
        int x, y, depth;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        Point(int x, int y, int depth) {
            this(x, y);
            this.depth = depth;
        }
    }
    // 상어의 좌표, 사이즈, 먹은 먹이의 횟수 정보
    static class Shark extends Point{
        int size, stack;
        Shark(int x, int y, int size, int stack) {
            super(x, y);
            this.size = size;
            this.stack = stack;
        }
    }

}
