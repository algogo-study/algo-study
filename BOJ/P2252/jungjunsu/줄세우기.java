import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        degree = new int[N+1];
        Arrays.fill(degree, 0);

        for (int i = 1; i <= N; i++) {
            adjList[i]=new ArrayList<>();
        }
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(in.readLine());
            // f <- b
            int f = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[b].add(f);
            if (degree[b] == -1) degree[b] = 0;
            if (degree[f] == -1) degree[f] = 0;
            degree[f] += 1;
        }

        Deque<Integer> dQ = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) dQ.add(i);
        }

        Stack<Integer> stack = new Stack<>();

        while(!dQ.isEmpty()) {
            Integer node = dQ.poll();
            v[node] = true;
            stack.add(node);
            for (Integer e :
                    adjList[node]) {
                degree[e] -= 1;
                if (degree[e] == 0 && !v[e]) dQ.addLast(e);
            }

        }
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            bw.write(stack.pop()+" ");
        } bw.write("\n");
        bw.flush();
    }

}
