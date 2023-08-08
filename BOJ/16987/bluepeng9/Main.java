import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int ans = 0;
    static int[] health;
    static int[] weight;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        health = new int[n];
        weight = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            health[i] = h;
            weight[i] = w;
        }
        rec(0);
        System.out.println(ans);
    }

    static void rec(int depth) {
        if (depth == n) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (health[i] <= 0) {
                    count += 1;
                }
            }
            ans = Math.max(ans, count);
            return;
        }
        if (health[depth] <= 0) {
            rec(depth + 1);
            return;
        }
        boolean hit = false;
        for (int i = 0; i < n; i++) {
            if (health[i] <= 0) {
                continue;
            }
            if (i == depth) {
                continue;
            }
            hit = true;
            health[depth] -= weight[i];
            health[i] -= weight[depth];
            rec(depth + 1);
            health[depth] += weight[i];
            health[i] += weight[depth];
        }
        if (!hit) {
            rec(depth + 1);
        }
    }

}
