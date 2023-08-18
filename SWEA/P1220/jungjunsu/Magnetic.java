import java.io.*;
import java.util.StringTokenizer;

public class Magnetic {
    static int[][] map;
   // N극은 위로, S극은 아래 방향
    static int dN = +1;
    static int dS = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = 10;
        map = new int[100][100];
        // 1: N 2 : S ---- upside : N downside : S
        for (int tc = 1; tc <= 1; tc++) {
            int N = Integer.parseInt(br.readLine());

            for (int row = 0; row < 100; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < 100; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // N극은 99행, S극은 0행까지 검사
            for (int col = 0; col < 100; col++) {
                for (int row = 0; row < 100; row++) {
                    if (map[row][col] == 0) continue;
                    int dr = 0;
                    int bound = 0;
                    if (map[row][col] == 1) {
                        dr = dN;
                        bound = 100;
                    }
                    if (map[row][col] == 2) {
                        dr = dS;
                        bound = -1;
                    }
                    int tmp = map[row][col];
                    map[row][col] = 0;
                    for (int move = row + dr; move != bound; move += dr) {
                        if (map[move][col] == 0) continue;
                        if (map[move][col] == tmp) continue;
                        map[move - dr][col] = tmp;
                        break;
                    }
                }
            }
            
            int count = 0;
            for (int col = 0; col < 100; col++) {
                for (int row = 0; row < 100; row++) {
                    if (map[row][col] == 1) count++;
                }
            }
            System.out.println("#"+tc+" "+count);
        }

    }

    static void print(int[][] map) {
        for (int[] y : map) {
            for (int x : y) {
                System.out.print(x + " ");
            } System.out.println();
        }
    }

}
