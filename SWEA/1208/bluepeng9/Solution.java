package bluepeng9;

import javax.imageio.spi.ImageReaderWriterSpi;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] board;
    static int n, m;

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            int dump = Integer.parseInt(br.readLine());

            int[] boxes = new int[102];
            String[] s = br.readLine().split(" ");
            int L = 1000;
            int R = -1;

            for (String value : s) {
                int v = Integer.parseInt(value);
                L = Math.min(L, v);
                R = Math.max(R, v);
                boxes[v] += 1;
            }


            while (dump > 0) {
                if (boxes[L] == 0) {
                    L += 1;
                    continue;
                }
                if (boxes[R] == 0) {
                    R -= 1;
                    continue;
                }
                if (L >= R) {
                    break;
                }
                boxes[R] -= 1;
                boxes[R - 1] += 1;
                boxes[L] -= 1;
                boxes[L + 1] += 1;
                dump--;
            }

            for (int j = 0; j < 101; j++) {
                if (boxes[j] != 0) {
                    L = j;
                    break;
                }
            }
            for (int j = 100; j > 0; j--) {
                if (boxes[j] != 0) {
                    R = j;
                    break;
                }
            }

            System.out.printf("#%d %d\n", i + 1, R - L);
        }
    }


}