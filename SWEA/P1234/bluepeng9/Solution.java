package SWEA.P1234.bluepeng9;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
import java.io.*;

class Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc;

    public static void main(String[] args) throws Exception {
        tc = 10;

        while (tc > 0) {
            String[] s = br.readLine().split(" ");
            String[] pass = s[1].split("");

            while (true) {
                boolean flag = true;
                for (int i = 0; i < pass.length - 1; i++) {
                    if (pass[i].equals(pass[i + 1])) {
                        pass[i] = "-1";
                        pass[i + 1] = "-1";
                        flag = false;
                    }
                }
                ArrayList<String> ar = new ArrayList<>();
                for (int i = 0; i < pass.length; i++) {
                    if (pass[i].equals("-1")) {
                        continue;
                    }
                    ar.add(pass[i]);
                }
                pass = ar.toArray(new String[0]);
                if (flag) {
                    System.out.printf("#%d ", -tc + 11);
                    for (int i = 0; i < pass.length; i++) {
                        System.out.printf("%s", pass[i]);
                    }
                    System.out.printf("\n");
                    break;
                }
            }

            tc--;
        }
    }

}