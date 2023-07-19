import java.util.*;
import java.io.*;

public class Main
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] cards;

    static int ans = 0;

    static int temp = 0;
    static int m;

    public static void main(String[] args) throws Exception {
        String[] in = br.readLine().split(" ");
        int n = Integer.parseInt(in[0]);
        m = Integer.parseInt(in[1]);

        String[] in2 = br.readLine().split(" ");
        cards = new int[in2.length];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = Integer.parseInt(in2[i]);
        }
        rec(0, 0);
        System.out.println(ans);
    }

    static void rec(int k, int q) {

        if (k == 3) {
            if (m < temp) {
                return;
            }

            if (m - ans > m - temp) {
                ans = temp;
            }
            return;
        }

        for (int i = q; i < cards.length; i++) {
            temp += cards[i];
            rec(k + 1, i + 1);
            temp -= cards[i];
        }
    }

}
