import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("./input/무선 충전"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(st.nextToken()) + 1;
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][n];

        for (int init = 0; init < m; init++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (b == 0) {map[u][v] = 1; continue;}

            map[u][v] = 2;
        }

        int question = Integer.parseInt(br.readLine());
        for (int quest = 0; quest < question; quest++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int count = 0;

            if (map[startNode][endNode] > 0 || startNode == endNode
                    || map[endNode][startNode] == 2 || map[endNode][startNode] == 1) {
                if (map[endNode][startNode] == 1) count++;
                bw.write(count+"\n");
                continue;
            }


            int connectCheck = startNode;
            boolean[] v = new boolean[n+1];
            while (connectCheck != endNode) {

                for (int i = 1; i < n; i++) {
                    if (map[i][connectCheck] > 0) {
                        if (map[i][connectCheck] != 2) {
                            count++;
                        }
                        connectCheck = i;
                        break;
                    }
                }
                if (connectCheck == startNode) {
                    break;
                }
            }
            bw.write(count+"\n");
        }
        bw.flush();
    }

    static void print(int[][] map) {
        for (int[] y : map) {
            for (int x : y) {
                System.out.print(x + " ");
            } System.out.println();
        }
    }

}
