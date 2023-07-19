import java.io.*;

public class Solution
{

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc, n;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        tc = Integer.parseInt(br.readLine());

        while(tc > 0) {
            n = Integer.parseInt(br.readLine());
            board = new int[n][];
            for (int i = 0; i < n; i++) {
                int[] arr = new int[n];
                String[] s = br.readLine().split("");
                for (int j = 0; j < n; j++) {
                    arr[j] = Integer.parseInt(s[j]);
                }
                board[i] = arr;
            }
            check();
            tc--;
        }
    }

    static void check() {
        int[] dx = { 1, 0 };
        int[] dy = { 0, -1 };
        int di = 0;

        int sx = 0;
        int sy = n / 2;
        int sum = 0;
        int count = n / 2;
        for (int i = 0; i < n; i++) {
            int nx = sx;
            int ny = sy;

            count = count == n / 2 + 1 ? n / 2 : n / 2 + 1;

            for (int j = 0; j < count; j++) {
                sum += board[nx][ny];
                nx += 1;
                ny += 1;
            }
            sx += dx[di];
            sy += dy[di];
            di = (di + 1) % 2;
        }
        System.out.printf("#%d %d\n", -tc + 51, sum);
    }
}