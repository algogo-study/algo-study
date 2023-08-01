
import java.io.*;
import java.util.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    public static void main(String[] args) throws IOException {
        int tc = Integer.parseInt(br.readLine());
 
        for (int i = 1; i < tc + 1; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] week = new int[7];
            String[] split = br.readLine().split(" ");
            int sumv = 0;
            for (int j = 0; j < 7; j++) {
                int v = Integer.parseInt(split[j]);
                week[j] = v;
                sumv += v;
            }
            int ans = 0;
            ans += (n - 1) / sumv * 7;
            int left = (n - 1) % sumv + 1;
            int minV = 10;
 
            for (int j = 0; j < 7; j++) {
                int count = 0;
                for (int k = 0; k < 7; k++) {
                    int idx = (j + k) % 7;
                    if (week[idx] == 1) {
                        count += 1;
                        if (left == count) {
                            minV = Math.min(minV, k + 1);
                            break;
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n", i, ans + minV);
        }
    }
}