import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int max;
    static int[][] manScore;
    static int[] used;
    static int[] history;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        manScore = new int[n][9];
        used = new int[9];
        used[0] = 1;
        history = new int[9];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int v = Integer.parseInt(st.nextToken());
                manScore[i][j] = v;
            }
        }
        rec(0);
        System.out.println(max);
    }

    static void simul() {
        int score = 0;
        int curMan = 0;
        for (int game = 0; game < n; game++) {
            int[] target = new int[5];
            int outCount = 0;
            while (outCount < 3) {
                int man = history[curMan];
                int sc = manScore[game][man];
                if (sc == 0) {
                    outCount += 1;
                } else {
                    target[0] = 1;
                    for (int i = 3; i >= 0; i--) {
                        int max = i + sc;
                        if (max > 4) {
                            max = 4;
                        }
                        target[max] += target[i];
                        target[i] = 0;
                    }
                }
                score += target[4];
                target[4] = 0;
                curMan = (curMan + 1) % 9;
            }
        }
        max = Math.max(max, score);
    }


    static void rec(int depth) {
        if (depth == 9) {
            simul();
            return;
        }
        if (depth == 3) {
            history[3] = 0;
            rec(depth + 1);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (used[i] == 1) {
                continue;
            }
            used[i] = 1;
            history[depth] = i;
            rec(depth + 1);
            used[i] = 0;
        }
    }
}
