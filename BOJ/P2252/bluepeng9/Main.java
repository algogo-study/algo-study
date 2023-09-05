import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] ind;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {

        String[] split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);

        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        ind = new int[n + 1];
        for (int i = 0; i < m; i++) {
            String[] split1 = br.readLine().split(" ");
            int a = Integer.parseInt(split1[0]);
            int b = Integer.parseInt(split1[1]);
            ind[b] += 1;
            graph.get(a).add(b);
        }
        check();
        bw.flush();
    }

    static void check() throws IOException {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n + 1; i++) {
            if (ind[i] == 0) {
                q.add(i);
            }
        }

        while (q.size() > 0) {
            Integer x = q.pop();

            bw.write(x + " ");

            ArrayList<Integer> arr = graph.get(x);
            for (Integer nx : arr) {
                ind[nx]--;
                if (ind[nx] == 0) {
                    q.add(nx);
                }
            }
        }
    }
}
