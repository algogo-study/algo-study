package BOJ.P11562.bluepeng9;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int n, m;
    static int[][] graph;
    static int[][] canGo;

    public static void main(String[] args) throws IOException {
        String[] split1 = br.readLine().split(" ");
        n = Integer.parseInt(split1[0]);

        graph = new int[n + 1][n + 1];
        canGo = new int[n + 1][n + 1];
        m = Integer.parseInt(split1[1]);

        for (int i = 0; i < m; i++) {
            String[] split2 = br.readLine().split(" ");
            int u = Integer.parseInt(split2[0]);
            int v = Integer.parseInt(split2[1]);
            int b = Integer.parseInt(split2[2]);
            graph[u][v] = 1;
            if (b == 1) {
                graph[v][u] = 1;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            prio(i);
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(String.valueOf(canGo[a][b]));
            bw.write("\n");
        }
        bw.flush();
    }

    static void prio(int cur) {
        ArrayDeque<State> q = new ArrayDeque<>();
        q.add(new State(cur, 0));

        int[] vis = new int[n + 1];
        Arrays.fill(vis, Integer.MAX_VALUE);


        while (!q.isEmpty()) {
            State curState = q.pop();
            int x = curState.x;
            int count = curState.count;

            if (vis[x] <= count) {
                continue;
            }

            vis[x] = count;

            for (int nx = 1; nx < n + 1; nx++) {
                int nCount = count;
                if (graph[x][nx] == 0) {
                    if (graph[nx][x] == 0) {
                        continue;
                    }
                    nCount += 1;
                }
                if (vis[nx] <= nCount) {
                    continue;
                }
                q.add(new State(nx, nCount));
            }

        }
        for (int i = 1; i < n + 1; i++) {
            canGo[cur][i] = vis[i];
        }

    }

    static class State {
        int x;
        int count;

        public State(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }
}
