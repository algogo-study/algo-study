package BOJ.P2240.bluepeng9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] split = br.readLine().split(" ");
        int t = Integer.parseInt(split[0]);
        int w = Integer.parseInt(split[1]);

        int[] jadu = new int[t + 1];

        int[][] dp = new int[t + 1][w + 1];

        for (int i = 1; i < t + 1; i++) {
            int num = Integer.parseInt(br.readLine());
            jadu[i] = num;

        }

        for (int i = 1; i < t + 1; i++) {
            int num = jadu[i];
            dp[i][0] = dp[i - 1][0];
            if (num == 1) {
                dp[i][0] += 1;
            }
            for (int j = 1; j < w + 1; j++) {
                int v = 0;
                if (num == 1 && j % 2 == 0) {
                    v = 1;
                }
                if (num == 2 && j % 2 == 1) {
                    v = 1;
                }
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + v;
            }
        }
        int ans = 0;
        for (int i : dp[t]) {
            ans = Math.max(ans, i);
        }
        System.out.println(ans);
    }

}
