package work;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 비숍 {
    // 인접 행렬
    static int[][] graph;
    static int N, ANS;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        //System.setIn(Main2.class.getResourceAsStream("input/비숍"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        graph = new int[N*N][N*N];

        // graph : 인접행렬 map : 초기맵
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                // 1 : 놓을 수 있음, 0 : 놓을 수 없음
                map[row][col] = Integer.parseInt(st.nextToken());
                graph[row * N + col][row * N + col] = map[row][col];
            }
        }
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (map[row][col] == 0) continue;
                for (int r = 0; r < N; r++) {
                    if (r == row) continue;
                    int lC = col - Math.abs(row - r);
                    int rC = col + Math.abs(row - r);

                    if (lC >= 0 && map[r][lC] == 1) {
                        graph[row * N + col][r * N + lC] = 1;
                        graph[row * N + col][row * N + col] += 1;
                    }
                    if (rC < N && map[r][rC] == 1) {
                        graph[row * N + col][r * N + rC] = 1;
                        graph[row * N + col][row * N + col] += 1;
                    }
                }
            }
        }

        //print(graph);

        int delCount = 0;

        while (true) {
            int min = Integer.MAX_VALUE;
            int targetR = 0;
            int targetC = 0;
            // 탐색
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (graph[row * N + col][row * N + col] == 1) {
                        graph[row * N + col][row * N + col] = 0;
                        ANS++;
                        continue;
                    }
                    if (graph[row * N + col][row * N + col] == 0) continue;
                    if (min < graph[row * N + col][row * N + col]) continue;
                    if (min > graph[row * N + col][row * N + col]) {
                        targetR = row;
                        targetC = col;
                        min = graph[row * N + col][row * N + col];
                    }

                    if (min == graph[row * N + col][row * N + col]) {
                        if (row > targetR) {
                            targetR = row;
                            targetC = col;
                        }
                    }

                }
            }

            if (min == Integer.MAX_VALUE) break;
            // 제거
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (graph[targetR * N + targetC][row * N + col] == 0) continue;
                    graph[row * N + col][row * N + col] = 0;
                    graph[targetR * N + targetC][targetR * N + targetC] = 0;
                    for (int r = 0; r < N; r++) {
                        for (int c = 0; c < N; c++) {
                            if (graph[r * N + c][row * N + col] != 1) continue;
                            if (graph[r * N + c][r * N + c] == 0) continue;
                            graph[r * N + c][r * N + c] -= 1;
                            graph[r * N + c][row * N + col] = 0;
                        }
                    }
                }
            }

            ANS++;
        }
        if (N==1) ANS = 0;
        System.out.println(ANS);

    }


}