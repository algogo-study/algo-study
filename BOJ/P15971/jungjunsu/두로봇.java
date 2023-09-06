import java.io.*;
import java.util.*;

public class Main {
    static class Edge{
        int s,d,w;
        public Edge(int s,int d, int w){
            this.s=s;
            this.d=d;
            this.w=w;
        }
        public String toString() {
            return "Edge [s=" + s + ", d=" + d + ", w=" + w + "]";
        }

    }
    static int N, A, B;
    static ArrayList<Integer[]>[] adjList;
    static int[] road;
    static boolean[] v;
    static boolean endCheck;
    static int num = -1;
    static int min = Integer.MAX_VALUE;
    static int[] arr;
    static int SUM, MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N=Integer.parseInt(st.nextToken());
        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        // 인접리스트
        adjList = new ArrayList[N+1];
        road = new int[N+1];
        v = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            adjList[i]=new ArrayList();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            // 인접리스트
			adjList[s].add(new Integer[] {d,w});
			adjList[d].add(new Integer[] {s,w});
        }

        recursive(A, 0, 0);
        arr = new int[num+1];
        System.arraycopy(road, 0, arr, 0, num+1);
        min = SUM - MAX;
        System.out.println(min);
    }

    private static void combination(int idx, int k, int sum) {
        if (k == num) {
            min = Math.min(min, sum);
            return;
        } if (idx == num + 1) return;
        for (int i = idx; i <= num; i++) {
            combination(i+1, k + 1, sum + arr[i]);
        }
    }

    private static void recursive(int idx, int max, int sum) {
        if (endCheck) return;
        v[idx] = true;
        for (Integer[] e: adjList[idx]) {
            if (v[e[0]]) continue;
            road[++num] = e[1];
            if (e[0] == B) {endCheck = true; SUM = sum + e[1]; MAX = Math.max(max, e[1]); return;}
            recursive(e[0], Math.max(max, e[1]), sum + e[1]);
            if (endCheck) return;
            road[num--] = 0;
        }
        v[idx] = false;
    }


}
