package SWEA.P2805.chanhong9764;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Solution
{
    static int[][] farm;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            farm = new int[N][N];
            int blank = N / 2;
            int answer = 0;
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                char[] split = st.nextToken().toCharArray();
                for (int j = 0; j < N; j++) {
                    farm[i][j] = split[j] - '0';
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = blank; j < N - blank; j++) {
                    answer += farm[i][j];
                }
                if ( i < (N / 2)) {
                    blank--;
                } else {
                    blank++;
                }
            }
            System.out.println("#" + tc + " " + answer);
        }
    }
}