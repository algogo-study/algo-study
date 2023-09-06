import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Monkey {
        int x, y, k, cnt;
        Monkey(int row, int col, int k, int cnt) {
            this.y = row;
            this.x = col;
            this.k = k;
            this.cnt = cnt;
        }
    }

    static int K, W, H, map[][];
    static long Ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        K=Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        W=Integer.parseInt(st.nextToken());
        H=Integer.parseInt(st.nextToken());
        map = new int[H][W];
        boolean[][][] v = new boolean[K+1][H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dm = {{1,0}, {-1,0}, {0,1}, {0,-1}
                   , {-1,-2}, {-1,2}, {-2,-1}, {-2,1}, {1,-2}, {1,2}, {2,-1}, {2,1}};
        Deque<Monkey> Q = new ArrayDeque<>();
        Q.add(new Monkey(0, 0, K, 0));
        v[K][0][0]=true;
        
        while(!Q.isEmpty()) {
            Monkey monkey = Q.poll();
            if(monkey.y == H-1 && monkey.x == W-1) {Ans = Math.min(monkey.cnt, Ans); break;}
            
            for (int i = 0; i < 12; i++) {
                int nr = monkey.y + dm[i][0];
                int nc = monkey.x + dm[i][1];

                if (nr < 0 || nc < 0 || nr >= H || nc >= W) continue;
                if (map[nr][nc] == 1) continue;
                if (i <= 3 && !v[monkey.k][nr][nc]) {v[monkey.k][nr][nc] = true; Q.add(new Monkey(nr, nc, monkey.k, monkey.cnt+1));}
                if (i > 3 && monkey.k > 0 && monkey.k-1 >= 0 && !v[monkey.k-1][nr][nc]) {v[monkey.k-1][nr][nc] = true;Q.add(new Monkey(nr, nc, monkey.k - 1, monkey.cnt+1));}
            }
        }
        
        if (Ans == Integer.MAX_VALUE) Ans = -1;
        System.out.println(Ans);
    }


}
