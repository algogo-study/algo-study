import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, unionNum, map[][], union[][];
    static boolean history[][];
    static Queue<Point> Q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        union = new int[N][N];
        history = new boolean[N][N];
        Q = new LinkedList<Point>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (history[row][col]) continue;
                if (map[row][col] == 0) continue;
                unionNum++;
                union[row][col] = unionNum;
                history[row][col] = true;
                dfs(row, col);
            }
        }

        int min = Integer.MAX_VALUE;
        // 영역들의 테두리를 모두 꺼내서 다른 영역과의 거리가 가장 짧은 값을 min에 저장
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (map[row][col] == 0) continue;
                    if (union[row][col] == p.union) continue;
                    int dist = Math.abs(row - p.y) + Math.abs(col - p.x) - 1;
                    min = Math.min(min, dist);
                }
            }
        }
        System.out.println(min);

    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    // 각 영역을 union 2차원 배열에 구분하여 저장하고, 그 영역의 테두리 부분을 큐에 저장
    private static void dfs(int row, int col) {

        boolean leafCheck = false;
        for (int v = 0; v < 4; v++) {
            int nr = row + dr[v];
            int nc = col + dc[v];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (map[nr][nc] == 0) {leafCheck = true; continue;}
            if (history[nr][nc]) continue;
            history[nr][nc] = true;
            union[nr][nc] = unionNum;
            dfs(nr, nc);
        }
        if (leafCheck) {
            Q.add(new Point(col, row, unionNum));
        }
    }

    static void print(int[][] map) {
        for (int[] y : map) {
            for (int x : y) {
                System.out.print(x + "\t");
            } System.out.println();
        }
    }

    static class Point {
        int x, y, union;
        Point(int x, int y, int union) {
            this.x = x;
            this.y = y;
            this.union = union;
        }
    }

}
