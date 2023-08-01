import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        String[] split = br.readLine().split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);

        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            arr.add(new ArrayList<>());
        }
        int count = Integer.parseInt(br.readLine());

        for (int i = 0; i < count; i++) {
            String[] split1 = br.readLine().split(" ");
            int x = Integer.parseInt(split1[0]);
            int y = Integer.parseInt(split1[1]);
            arr.get(x).add(y);
            arr.get(y).add(x);
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(a);
        int[] vis = new int[n + 1];
        Arrays.fill(vis, -1);
        vis[a] = 0;

        while (q.size() > 0) {
            int pop = q.pop();

            for (int i = 0; i < arr.get(pop).size(); i++) {
                int nx = arr.get(pop).get(i);
                if (vis[nx] != -1) {
                    continue;
                }
                vis[nx] = vis[pop] + 1;
                q.add(nx);
            }
        }

        System.out.println(vis[b]);
    }

}
