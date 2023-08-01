import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int ans = 4;
    static int[] vis = new int[50010];

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        rec(0, n);
        System.out.println(ans);
    }

    static void rec(int k, int n) {
        if (n == 0) {
            ans = Math.min(ans, k);
            return;
        }
        if (n < 0) {
            return;
        }
        if (k == 4) {
            return;
        }
        if (vis[n] == 1) {
            return;
        }
        vis[n] = 1;

        for (int i = (int) Math.sqrt(n); i > 0; i--) {
            rec(k + 1, n - i * i);
        }
    }
}
