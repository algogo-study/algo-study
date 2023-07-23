import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static String[] split;
    static long ans = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        split = ("+" + br.readLine() + "+0").split("");

        rec(1, 0);
        System.out.println(ans);

    }

    static void rec(int k, long res) {

        if (k > n) {
            ans = Math.max(ans, res);
            return;
        }

        String oper = split[k - 1];
        String oper2 = split[k + 1];
        int cur = Integer.parseInt(split[k]);
        int next = Integer.parseInt(split[k + 2]);
        long temp = calc(oper2, cur, next);
        rec(k + 2, calc(oper, res, cur));
        rec(k + 4, calc(oper, res, temp));
    }

    static long calc(String oper, long a, long b) {
        if (oper.equals("+")) {
            return a + b;
        }
        if (oper.equals("-")) {
            return a - b;
        }
        return a * b;
    }


}

