import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, x, y;
    static int ans;
    static ArrayList<ArrayList<Node>> graph;
    static int[] xToOther;
    static int[] yToOther;


    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken()) - 1;
        y = Integer.parseInt(st.nextToken()) - 1;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        if (x == y) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }
        xToOther = new int[n];
        yToOther = new int[n];
        ans = Integer.MAX_VALUE;

        Arrays.fill(xToOther, Integer.MAX_VALUE);
        Arrays.fill(yToOther, Integer.MAX_VALUE);
        dijk(x, xToOther);
        dijk(y, yToOther);

        for (int i = 0; i < n; i++) {
            getAd(i);
        }
        System.out.println(ans);
    }

    static void dijk(int i, int[] vis) {
        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        q.add(new Node(i, 0));
        vis[i] = 0;

        while (!q.isEmpty()) {
            Node p = q.poll();
            int x = p.x;
            int cost = p.cost;
            if (vis[x] != cost) {
                continue;
            }

            for (Node node : graph.get(x)) {
                int nx = node.x;
                int nCost = node.cost + cost;
                if (vis[nx] < nCost) {
                    continue;
                }
                vis[nx] = nCost;
                q.add(new Node(nx, nCost));
            }
        }

    }

    static void getAd(int x) {

        for (Node node : graph.get(x)) {
            int nx = node.x;
            int a = xToOther[x] + yToOther[nx];
            int b = xToOther[nx] + yToOther[x];
            int c = Math.min(a, b);
            ans = Math.min(c, ans);
        }
    }

    static class Node {
        int x;
        int cost;

        public Node(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", cost=" + cost +
                    '}';
        }
    }
}
