package SWEA.P5432.bluepeng9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int tc = Integer.parseInt(br.readLine());


        for (int line = 1; line < tc + 1; line++) {
            String[] split = br.readLine().split("");
            int count = 0;
            int ans = 0;
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (s.equals(")")) {
                    count -= 1;
                    if (i != 0 && split[i - 1].equals("(")) {
                        ans += count;
                        continue;
                    }
                    ans += 1;
                } else if (s.equals("(")) {
                    count += 1;
                }
            }
            System.out.printf("#%d %d\n", line, ans);
        }
    }


}

